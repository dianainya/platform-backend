package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.Record;
import org.jooq.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.dto.dish.DishFilterParam;
import sadiva.mpi.platformbackend.dto.dish.Ingredient;
import sadiva.mpi.platformbackend.entity.DishEntity;
import sadiva.mpi.platformbackend.entity.IngredientEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.DISH_INGREDIENTS;
import static jooq.sadiva.mpi.platformbackend.Tables.PRODUCT;
import static jooq.sadiva.mpi.platformbackend.tables.Dish.DISH;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

@Repository
@RequiredArgsConstructor
public class DishRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;


    public UUID save(DishEntity dish, List<Ingredient> ingredients) {
        UUID dishId = dslContext.insertInto(DISH)
                .set(DISH.ID, UUID.randomUUID())
                .set(DISH.NAME, dish.name())
                .set(DISH.DESCRIPTION, dish.description())
                .set(DISH.RECEIPT, dish.receipt())
                .onConflict(DISH.NAME).doNothing()
                .returningResult(DISH.ID)
                .fetchOneInto(UUID.class);
        if (dishId == null) {
            return null;
        }
        insertIngredients(dishId, ingredients);
        return dishId;
    }

    public DishEntity getById(UUID id) {
        return getSelectStep().and(DISH.ID.eq(id)).fetchOne(this::mapDishEntity);
    }

    public PageEntity<DishEntity> getPaginated(DishFilterParam filterParam, Pageable pageable) {
        var select = getSelectStep();
        var count = dslContext.selectCount().from(DISH).where();

        select = applyFilter(select, filterParam);
        count = applyFilter(count, filterParam);

        List<DishEntity> dishEntities = applyPagination(select, pageable).fetch(this::mapDishEntity);
        Integer totalCount = count.fetchOneInto(Integer.class);
        return new PageEntity<>(dishEntities, totalCount);
    }

    public void deleteById(UUID id) {
        dslContext.deleteFrom(DISH)
                .where(DISH.ID.eq(id))
                .execute();
    }

    public UUID update(UUID id, DishEntity dish, List<Ingredient> ingredients) {
        var updatedDish = dslContext.update(DISH)
                .set(DISH.NAME, dish.name())
                .set(DISH.DESCRIPTION, dish.description())
                .set(DISH.RECEIPT, dish.receipt())
                .set(DISH.UPDATED_AT, LocalDateTime.now())
                .where(DISH.ID.eq(id))
                .returning()
                .fetchOneInto(Dish.class);
        if (updatedDish == null) {
            return null;
        }

        dslContext.deleteFrom(DISH_INGREDIENTS).where(DISH_INGREDIENTS.DISH_ID.eq(id)).execute();
        insertIngredients(id, ingredients);

        return updatedDish.getId();
    }

    public boolean isExist(UUID id) {
        return dslContext.selectCount().from(DISH).where(DISH.ID.eq(id)).execute() == 1;
    }

    private @NotNull SelectConditionStep<Record2<Dish, Result<Record2<Integer, Product>>>> getSelectStep() {
        return dslContext.select(
                DISH.mapping(Dish::new),
                multiset(
                        select(DISH_INGREDIENTS.AMOUNT, PRODUCT.mapping(Product::new))
                                .from(DISH_INGREDIENTS)
                                .leftJoin(PRODUCT).on(PRODUCT.ID.eq(DISH_INGREDIENTS.PRODUCT_ID))
                                .where(DISH_INGREDIENTS.DISH_ID.eq(DISH.ID))
                )).from(DISH).where();
    }

    private <T extends Record> SelectConditionStep<T> applyFilter(SelectConditionStep<T> res, DishFilterParam filterParam) {
        if (filterParam.name() != null) {
            res = res.and(DISH.NAME.likeIgnoreCase("%" + filterParam.name() + "%"));
        }
        return res;
    }

    private void insertIngredients(UUID dishId, List<Ingredient> ingredients) {
        var insertQuery = dslContext.insertInto(DISH_INGREDIENTS)
                .set(DISH_INGREDIENTS.PRODUCT_ID, (UUID) null)
                .set(DISH_INGREDIENTS.DISH_ID, (UUID) null)
                .set(DISH_INGREDIENTS.AMOUNT, (Integer) null);

        var batch = dslContext.batch(insertQuery);

        for (Ingredient ingredient : ingredients) {
            batch = batch.bind(
                    ingredient.productId(),
                    dishId,
                    ingredient.amount()
            );
        }
        batch.execute();
    }

    private DishEntity mapDishEntity(Record2<Dish, Result<Record2<Integer, Product>>> r) {
        return new DishEntity(
                r.component1().getId(),
                r.component1().getName(),
                r.component1().getDescription(),
                r.component1().getReceipt(),
                r.component2().stream().map(record -> new IngredientEntity(
                        record.component1(),
                        record.component2()
                )).toList());
    }
}
