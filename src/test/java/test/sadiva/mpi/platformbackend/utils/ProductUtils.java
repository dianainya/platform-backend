package test.sadiva.mpi.platformbackend.utils;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import jooq.sadiva.mpi.platformbackend.tables.records.ProductRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static jooq.sadiva.mpi.platformbackend.Tables.PRODUCT;
import static test.sadiva.mpi.platformbackend.utils.RandomUtils.randomString;

@RequiredArgsConstructor
public class ProductUtils {
    private final DSLContext dslContext;

    public Product createProduct() {
        Product product = new Product();
        product.setName(randomString());
        product.setCalories(RandomUtils.randomInt(50, 500));
        product.setProteins(RandomUtils.randomInt(0, 50));
        product.setFats(RandomUtils.randomInt(0, 50));
        product.setCarbohydrates(RandomUtils.randomInt(0, 100));
        product.setWeight(RandomUtils.randomInt(100, 1000));

        return dslContext.insertInto(PRODUCT)
                .set(new ProductRecord(product))
                .returning()
                .fetchOneInto(Product.class);
    }
}
