package test.sadiva.mpi.platformbackend.utils;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static jooq.sadiva.mpi.platformbackend.Tables.*;

@RequiredArgsConstructor
public class BaseUtils {
    private final DSLContext dsl;
    private final DishUtils dishUtils;
    private final ProductUtils productUtils;
    private final PrisonerUtils prisonerUtils;

    public void clearData() {
        dsl.transaction(configuration -> {
            DSLContext ctx = DSL.using(configuration);
            ctx.truncate(PRISONER_RATING).cascade().execute();
            ctx.truncate(USER_ROLE).cascade().execute();
            ctx.truncate(DISH_INGREDIENTS).cascade().execute();
            ctx.truncate(PLATFORM_PRISONER).cascade().execute();
            ctx.truncate(PRISONER).cascade().execute();
            ctx.truncate(PLATFORM_USER).cascade().execute();
            ctx.truncate(PLATFORM_ROLE).cascade().execute();
            ctx.truncate(PRODUCT_WAREHOUSE).cascade().execute();
            ctx.truncate(PRODUCT).cascade().execute();
            ctx.truncate(DISH).cascade().execute();
            ctx.truncate(PLATFORM_HISTORY).cascade().execute();
            ctx.truncate(PLATFORM).cascade().execute();
        });
    }

    public Dish createDish() {
        Product product1 = productUtils.createProduct();
        Product product2 = productUtils.createProduct();
        return dishUtils.createDish(List.of(product1, product2));
    }

    public Prisoner createPrisoner() {
        Dish dish = createDish();
        return prisonerUtils.createPrisoner(dish.getId());
    }
}
