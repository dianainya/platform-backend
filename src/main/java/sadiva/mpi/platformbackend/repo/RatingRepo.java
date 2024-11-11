package sadiva.mpi.platformbackend.repo;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PRISONER_RATING;

@Repository
@RequiredArgsConstructor
public class RatingRepo {
    private final DSLContext dslContext;

    public int changeRating(UUID personId, Integer score) {
        return dslContext.update(PRISONER_RATING)
                .set(PRISONER_RATING.SCORE, PRISONER_RATING.SCORE.plus(score))
                .where(PRISONER_RATING.PRISONER_ID.eq(personId))
                .execute();
    }
}
