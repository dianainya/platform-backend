package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import jooq.sadiva.mpi.platformbackend.tables.pojos.PrisonerRating;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.entity.DishShortEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.*;
import static org.jooq.impl.DSL.row;

@Repository
@RequiredArgsConstructor
public class PrisonerRepo implements BasePaginatedRepository {
    private final DSLContext dslContext;

    public UUID save(Prisoner prisoner) {
        return dslContext.insertInto(PRISONER)
                .set(PRISONER.LAST_NAME, prisoner.getLastName())
                .set(PRISONER.PATRONYMIC, prisoner.getPatronymic())
                .set(PRISONER.FIRST_NAME, prisoner.getFirstName())
                .set(PRISONER.WEIGHT, prisoner.getWeight())
                .set(PRISONER.BIRTH_DATE, prisoner.getBirthDate())
                .set(PRISONER.PASSPORT, prisoner.getPassport())
                .set(PRISONER.FAVORITE_DISH, prisoner.getFavoriteDish())
                .returningResult(PRISONER.ID)
                .fetchOneInto(UUID.class);
    }

    public PrisonerEntity getById(UUID id) {
        return getSelectStep()
                .and(PRISONER.ID.eq(id))
                .fetchOne(this::mapPrisonerEntity);
    }

    public List<PrisonerEntity> getAllAlivePrisonerOrderByRating() {
        return getSelectStep()
                .and(PRISONER.IS_ALIVE.isTrue())
                .orderBy(PRISONER_RATING.SCORE.desc())
                .fetch(this::mapPrisonerEntity);
    }

    public PageEntity<PrisonerEntity> getAllPaginated(Pageable pageable, PrisonerFilterParam filterParam) {
        var select = getSelectStep();
        var count = dslContext.selectCount().from(PRISONER).where();

        select = applyFilter(select, filterParam);
        count = applyFilter(count, filterParam);

        List<PrisonerEntity> res = applyPagination(select, pageable).fetch(this::mapPrisonerEntity);
        Integer totalCount = count.fetchOne(0, Integer.class);
        return new PageEntity<>(res, totalCount);
    }
    public @NotNull List<PrisonerEntity> getAll(PrisonerFilterParam filterParam) {
        var select = getSelectStep();
        return applyFilter(select, filterParam).fetch(this::mapPrisonerEntity);
    }

    public void deleteById(UUID id) {
        dslContext.deleteFrom(PRISONER)
                .where(PRISONER.ID.eq(id))
                .execute();
    }

    public UUID update(UUID id, UUID dishId, Boolean isAlive, Double weight) {
        return dslContext.update(PRISONER)
                .set(PRISONER.WEIGHT, weight)
                .set(PRISONER.FAVORITE_DISH,dishId)
                .set(PRISONER.IS_ALIVE,isAlive)
                .where(PRISONER.ID.eq(id))
                .returningResult(PRISONER.ID)
                .fetchOneInto(UUID.class);
    }

    private <T extends Record> SelectConditionStep<T> applyFilter(SelectConditionStep<T> query, PrisonerFilterParam filterParam) {
        if (filterParam.search() != null && !filterParam.search().isEmpty()) {
            Condition condition = DSL.or(
                    PRISONER.LAST_NAME.likeIgnoreCase("%" + filterParam.search() + "%"),
                    PRISONER.FIRST_NAME.likeIgnoreCase("%" + filterParam.search() + "%"),
                    PRISONER.PATRONYMIC.likeIgnoreCase("%" + filterParam.search() + "%")
            );

            query = query.and(condition);

        }
        if (filterParam.isDishImplemented() != null ) {
            query = query.and(filterParam.isDishImplemented() ? PRISONER.FAVORITE_DISH.isNotNull() : PRISONER.FAVORITE_DISH.isNull());
        }
        return query;
    }

    private @NotNull SelectConditionStep<Record3<Prisoner, DishShortEntity, PrisonerRating>> getSelectStep() {
        return dslContext.select(
                        PRISONER.mapping(Prisoner::new),
                        row(DISH.ID, DISH.NAME).mapping(DishShortEntity::new),
                        PRISONER_RATING.mapping(PrisonerRating::new)
                )
                .from(PRISONER)
                .leftJoin(DISH).on(PRISONER.FAVORITE_DISH.eq(DISH.ID))
                .leftJoin(PRISONER_RATING).on(PRISONER.ID.eq(PRISONER_RATING.PRISONER_ID))
                .where();
    }

    private PrisonerEntity mapPrisonerEntity(Record3<Prisoner, DishShortEntity, PrisonerRating> r) {
        return new PrisonerEntity(
                r.component1().getId(),
                r.component1().getLastName(),
                r.component1().getFirstName(),
                r.component1().getPatronymic(),
                r.component1().getPassport(),
                r.component1().getWeight(),
                r.component1().getBirthDate(),
                r.component2(),
                r.component3().getScore(),
                r.component1().getIsAlive()
        );
    }
}
