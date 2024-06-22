package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.PrisonerViolation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.sadiva.mpi.platformbackend.Tables.PRISONER_VIOLATION;

@Repository
@RequiredArgsConstructor
public class ViolationRepo {
    private final DSLContext dslContext;

    public @NotNull List<PrisonerViolation> getAllViolations() {
        return dslContext.selectFrom(PRISONER_VIOLATION).fetchInto(PrisonerViolation.class);
    }

    public Integer getScoreByCode(String code) {
        return dslContext.select(PRISONER_VIOLATION.SCORE).from(PRISONER_VIOLATION)
                .where(PRISONER_VIOLATION.CODE.eq(code))
                .fetchOneInto(Integer.class);
    }
}
