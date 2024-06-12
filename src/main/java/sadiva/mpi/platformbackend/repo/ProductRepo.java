package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.entity.PageEntity;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PRODUCT;

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

    public Product getById(UUID id) {
        return dslContext.select()
                .from(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOneInto(Product.class);
    }

    public PageEntity<Product> getAllPaginated(Pageable pageable) {
        List<Product> res = applyPagination(dslContext.select().from(PRODUCT).where(), pageable).fetchInto(Product.class);
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

//    public Product changeAmount(UUID id, Integer amount) {
//        return dslContext.update(PRODUCT)
//                .set(PRODUCT.A, PRODUCT.AVAILABLE_WEIGHT.add(amount))
//                .where(PRODUCT.ID.eq(id))
//                .returning()
//                .fetchOneInto(Product.class);
//    }
}
