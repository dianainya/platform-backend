package sadiva.mpi.platformbackend.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.entity.DishCountEntity;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.CURRENT_MENU;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CurrentMenuRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;

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
}
