package test.sadiva.mpi.platformbackend.utils;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import jooq.sadiva.mpi.platformbackend.tables.records.DishRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.DISH;
import static jooq.sadiva.mpi.platformbackend.Tables.DISH_INGREDIENTS;
import static test.sadiva.mpi.platformbackend.utils.RandomUtils.randomString;

@RequiredArgsConstructor
public class DishUtils {
    private final DSLContext dslContext;

    public Dish createDish(List<Product> products) {
        Dish dish = new Dish();
        dish.setName(randomString());
        dish.setReceipt("Sample receipt: " + randomString());
        dish.setDescription("Sample description: " + randomString());

        Dish createdDish = dslContext.insertInto(DISH)
                .set(new DishRecord(dish))
                .returning()
                .fetchOneInto(Dish.class);

        if (createdDish != null && createdDish.getId() != null) {
            UUID dishId = createdDish.getId();

            products.forEach(product ->
                    dslContext.insertInto(DISH_INGREDIENTS)
                            .set(DISH_INGREDIENTS.DISH_ID, dishId)
                            .set(DISH_INGREDIENTS.PRODUCT_ID, product.getId())
                            .set(DISH_INGREDIENTS.AMOUNT, 1)
                            .execute()
            );
        }

        return createdDish;
    }
}
