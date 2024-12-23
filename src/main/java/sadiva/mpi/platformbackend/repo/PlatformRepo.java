package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.Prisoner;
import jooq.sadiva.mpi.platformbackend.tables.PrisonerRating;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PlatformEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.entity.PrisonerFioEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.*;
import static org.jooq.impl.DSL.max;

@Repository
@RequiredArgsConstructor
public class PlatformRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;
    private static final Prisoner FIRST_PRISONER = PRISONER.as("firstPrisoner");
    private static final PrisonerRating FIRST_PRISONER_RATING = PRISONER_RATING.as("firstPrisonerRating");
    private static final Prisoner SECOND_PRISONER = PRISONER.as("secondPrisoner");
    private static final PrisonerRating SECOND_PRISONER_RATING = PRISONER_RATING.as("secondPrisonerRating");

    public void truncate() {
        dslContext.truncate(PLATFORM_PRISONER).execute();
    }

    public void distributePrisoners(List<PrisonerEntity> prisonerList) {
        var insertQuery = dslContext.insertInto(PLATFORM_PRISONER)
                .set(PLATFORM_PRISONER.FLOOR, (Integer) null)
                .set(PLATFORM_PRISONER.FIRST_PRISONER, (UUID) null)
                .set(PLATFORM_PRISONER.SECOND_PRISONER, (UUID) null);

        var batch = dslContext.batch(insertQuery);
        Integer floor = 0;
        for (int i = 0; i < prisonerList.size(); i += 2) {
            ++floor;
            UUID firstPrisonerId = prisonerList.get(i).id();
            UUID secondPrisonerId = i + 1 < prisonerList.size() ? prisonerList.get(i + 1).id() : null;

            batch = batch.bind(floor, firstPrisonerId, secondPrisonerId);
        }
        batch.execute();
    }

    @Transactional
    public PageEntity<PlatformEntity> getPlatformStructure(Pageable pageable) {
        var select = getSelectStep();
        var count = dslContext.selectCount().from(PLATFORM_PRISONER);

        List<PlatformEntity> res = select
                .orderBy(PLATFORM_PRISONER.FLOOR.asc())
                .offset((pageable.getPageNumber() - 1) * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch(this::mapPlatformEntity);
        Integer totalCount = count.fetchOneInto(Integer.class);

        return new PageEntity<>(res, totalCount);
    }

    private @NotNull SelectConditionStep<Record> getSelectStep() {
        return dslContext.select().from(PLATFORM_PRISONER)
                .leftJoin(FIRST_PRISONER).on(PLATFORM_PRISONER.FIRST_PRISONER.eq(FIRST_PRISONER.ID))
                .leftJoin(FIRST_PRISONER_RATING).on(FIRST_PRISONER_RATING.PRISONER_ID.eq(FIRST_PRISONER.ID))
                .leftJoin(SECOND_PRISONER).on(PLATFORM_PRISONER.SECOND_PRISONER.eq(SECOND_PRISONER.ID))
                .leftJoin(SECOND_PRISONER_RATING).on(SECOND_PRISONER_RATING.PRISONER_ID.eq(SECOND_PRISONER.ID))
                .leftJoin(PLATFORM_ACTIVE_FLOOR).on(PLATFORM_PRISONER.FLOOR.eq(PLATFORM_ACTIVE_FLOOR.ACTIVE_FLOOR))
                .where();
    }

    private PlatformEntity mapPlatformEntity(Record record) {
        UUID secondPrisonerId = record.get(SECOND_PRISONER.ID);
        return new PlatformEntity(
                record.get(PLATFORM_PRISONER.FLOOR),
                new PrisonerFioEntity(
                        record.get(FIRST_PRISONER.ID),
                        record.get(FIRST_PRISONER.LAST_NAME),
                        record.get(FIRST_PRISONER.FIRST_NAME),
                        record.get(FIRST_PRISONER.PATRONYMIC),
                        record.get(FIRST_PRISONER_RATING.SCORE)
                ),
                secondPrisonerId == null ? null : new PrisonerFioEntity(
                        secondPrisonerId,
                        record.get(SECOND_PRISONER.LAST_NAME),
                        record.get(SECOND_PRISONER.FIRST_NAME),
                        record.get(SECOND_PRISONER.PATRONYMIC),
                        record.get(SECOND_PRISONER_RATING.SCORE)
                ),
                Objects.equals(record.get(PLATFORM_ACTIVE_FLOOR.ACTIVE_FLOOR), record.get(PLATFORM_PRISONER.FLOOR))
        );
    }
    public Integer getMaxFloor() {
        return dslContext.select(max(PLATFORM_PRISONER.FLOOR))
                .from(PLATFORM_PRISONER)
                .fetchOneInto(Integer.class);
    }

    public Integer getCurrentActiveFloor() {
       Integer activeFloor = dslContext.select(PLATFORM_ACTIVE_FLOOR.ACTIVE_FLOOR)
                .from(PLATFORM_ACTIVE_FLOOR)
                .fetchOne(PLATFORM_ACTIVE_FLOOR.ACTIVE_FLOOR);
        return activeFloor == null ? 0 : activeFloor;
    }

    public void updateActiveFloor(Integer floor) {
        dslContext.update(PLATFORM_ACTIVE_FLOOR)
                .set(PLATFORM_ACTIVE_FLOOR.ACTIVE_FLOOR, floor)
                .execute();
    }
}
