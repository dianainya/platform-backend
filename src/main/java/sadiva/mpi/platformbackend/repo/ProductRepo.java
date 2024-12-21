package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PRODUCT;
import static jooq.sadiva.mpi.platformbackend.Tables.PRODUCT_WAREHOUSE;

@Repository
@RequiredArgsConstructor
public class ProductRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;

    public Product save(Product product) {
        return dslContext.insertInto(PRODUCT)
                .set(PRODUCT.NAME, product.getName())
                .set(PRODUCT.CALORIES, product.getCalories())
                .set(PRODUCT.PROTEINS, product.getProteins())
                .set(PRODUCT.FATS, product.getFats())
                .set(PRODUCT.CARBOHYDRATES, product.getCarbohydrates())
                .set(PRODUCT.WEIGHT, product.getWeight())
                .returning()
                .fetchOneInto(Product.class);
    }

    public ProductEntity getById(UUID id) {
        return getSelectOnConditionStep()
                .and(PRODUCT.ID.eq(id))
                .fetchOne(this::mapToEntity);
    }

    @NotNull
    private SelectConditionStep<Record> getSelectOnConditionStep() {
        return dslContext.select()
                .from(PRODUCT)
                .leftJoin(PRODUCT_WAREHOUSE).on(PRODUCT_WAREHOUSE.PRODUCT_ID.eq(PRODUCT.ID))
                .where();
    }

    public PageEntity<ProductEntity> getAllPaginated(Pageable pageable) {
        @NotNull List<ProductEntity> res = applyPagination(getSelectOnConditionStep(), pageable).fetch(this::mapToEntity);
        Integer totalCount = dslContext.selectCount().from(PRODUCT).fetchOne(0, Integer.class);
        return new PageEntity<>(res, totalCount);
    }

    public void deleteById(UUID id) {
        dslContext.deleteFrom(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .execute();
    }

    public Product update(UUID id, Product product) {
        return dslContext.update(PRODUCT)
                .set(PRODUCT.NAME, product.getName())
                .set(PRODUCT.CALORIES, product.getCalories())
                .set(PRODUCT.PROTEINS, product.getProteins())
                .set(PRODUCT.FATS, product.getFats())
                .set(PRODUCT.CARBOHYDRATES, product.getCarbohydrates())
                .set(PRODUCT.WEIGHT, product.getWeight())
                .where(PRODUCT.ID.eq(id))
                .returning()
                .fetchOneInto(Product.class);
    }

    private ProductEntity mapToEntity(Record r) {
        return new ProductEntity(
                r.get(PRODUCT.ID),
                r.get(PRODUCT.NAME),
                r.get(PRODUCT.CALORIES),
                r.get(PRODUCT.PROTEINS),
                r.get(PRODUCT.FATS),
                r.get(PRODUCT.CARBOHYDRATES),
                r.get(PRODUCT.WEIGHT),
                r.get(PRODUCT_WAREHOUSE.AMOUNT)
        );
    }
}
