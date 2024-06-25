package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.Prisoner;
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
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PLATFORM_PRISONER;
import static jooq.sadiva.mpi.platformbackend.Tables.PRISONER;

@Repository
@RequiredArgsConstructor
public class PlatformRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;
    private static final Prisoner FIRST_PRISONER = PRISONER.as("firstPrisoner");
    private static final Prisoner SECOND_PRISONER = PRISONER.as("secondPrisoner");

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

        List<PlatformEntity> res = applyPagination(select, pageable).fetch(this::mapPlatformEntity);
        Integer totalCount = count.fetchOneInto(Integer.class);

        return new PageEntity<>(res, totalCount);
    }

    private @NotNull SelectConditionStep<Record> getSelectStep() {
        return dslContext.select().from(PLATFORM_PRISONER)
                .leftJoin(FIRST_PRISONER).on(PLATFORM_PRISONER.FIRST_PRISONER.eq(FIRST_PRISONER.ID))
                .leftJoin(SECOND_PRISONER).on(PLATFORM_PRISONER.SECOND_PRISONER.eq(SECOND_PRISONER.ID))
                .where();
    }

    private PlatformEntity mapPlatformEntity(Record record) {
        return new PlatformEntity(
                record.get(PLATFORM_PRISONER.FLOOR),
                new PrisonerFioEntity(
                        record.get(FIRST_PRISONER.ID),
                        record.get(FIRST_PRISONER.LAST_NAME),
                        record.get(FIRST_PRISONER.FIRST_NAME),
                        record.get(FIRST_PRISONER.PATRONYMIC)
                ),
                new PrisonerFioEntity(
                        record.get(SECOND_PRISONER.ID),
                        record.get(SECOND_PRISONER.LAST_NAME),
                        record.get(SECOND_PRISONER.FIRST_NAME),
                        record.get(SECOND_PRISONER.PATRONYMIC)
                )
        );
    }
}
