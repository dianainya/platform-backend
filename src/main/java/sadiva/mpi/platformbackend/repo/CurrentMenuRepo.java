package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jooq.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.entity.CurrentMenuEntity;
import sadiva.mpi.platformbackend.entity.DishCountEntity;
import sadiva.mpi.platformbackend.entity.DishPickupEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.mapper.DishMapper;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.*;
import static jooq.sadiva.mpi.platformbackend.tables.Dish.DISH;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CurrentMenuRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;

    public PageEntity<CurrentMenuEntity> getAllPaginated(Pageable pageable) {
        var selectStep = getSelectStep();
        var count = dslContext.selectCount().from(CURRENT_MENU).where();

        List<CurrentMenuEntity> dishCountEntities = applyPagination(selectStep, pageable).fetch(this::mapCurrentMenuEntity);
        Integer totalCount = count.fetchOneInto(Integer.class);
        return new PageEntity<>(dishCountEntities, totalCount);
    }

    public void pickDishes(List<DishPickupEntity> dishPickupEntities) {
        int updatedRowsCount = 0;

        for (DishPickupEntity dishPickup : dishPickupEntities) {
            int rowsAffected = dslContext.update(CURRENT_MENU)
                    .set(CURRENT_MENU.AMOUNT, CURRENT_MENU.AMOUNT.minus(dishPickup.amount()))
                    .where(CURRENT_MENU.DISH_ID.eq(dishPickup.dishId()))
                    .and(CURRENT_MENU.AMOUNT.ge(dishPickup.amount()))
                    .execute();
            updatedRowsCount += rowsAffected;
        }

        log.info("Updated {} rows for dish pickups", updatedRowsCount);
    }


    public int truncate() {
        return dslContext.truncate(CURRENT_MENU).execute();
    }

    public int createCurrentMenu(List<DishCountEntity> dishCounts) {
        var insertQuery = dslContext.insertInto(CURRENT_MENU)
                .set(CURRENT_MENU.DISH_ID, (UUID) null)
                .set(CURRENT_MENU.AMOUNT, (Integer) null);

        var batch = dslContext.batch(insertQuery);
        for (DishCountEntity dishCount : dishCounts) {
            batch = batch.bind(dishCount.dishId(), dishCount.dishCount());
        }
        return batch.execute().length;
    }


    private @NotNull SelectConditionStep<Record3<Dish, Result<Record2<Integer, Product>>, Integer>> getSelectStep() {
        return dslContext.select(
                        DISH.mapping(Dish::new),
                        multiset(
                                select(DISH_INGREDIENTS.AMOUNT, PRODUCT.mapping(Product::new))
                                        .from(DISH_INGREDIENTS)
                                        .leftJoin(PRODUCT).on(PRODUCT.ID.eq(DISH_INGREDIENTS.PRODUCT_ID))
                                        .where(DISH_INGREDIENTS.DISH_ID.eq(DISH.ID))
                        ),
                        CURRENT_MENU.AMOUNT
                )
                .from(CURRENT_MENU)
                .leftJoin(DISH).on(DISH.ID.eq(CURRENT_MENU.DISH_ID))
                .where();
    }


    private CurrentMenuEntity mapCurrentMenuEntity(Record3<Dish, Result<Record2<Integer, Product>>, Integer> r) {
        return new CurrentMenuEntity(
                DishMapper.mapDishEntity(r.component1(), r.component2()),
                r.component3()
        );
    }
}
