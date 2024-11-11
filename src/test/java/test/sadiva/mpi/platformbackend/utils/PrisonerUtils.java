package test.sadiva.mpi.platformbackend.utils;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import jooq.sadiva.mpi.platformbackend.tables.records.PrisonerRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.time.LocalDate;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PRISONER;
import static test.sadiva.mpi.platformbackend.utils.RandomUtils.randomString;

@RequiredArgsConstructor
public class PrisonerUtils {
    private final DSLContext dslContext;

    public Prisoner createPrisoner(UUID favoriteDishId) {
        Prisoner prisoner = new Prisoner();
        prisoner.setFirstName(randomString());
        prisoner.setLastName(randomString());
        prisoner.setPatronymic(randomString());
        prisoner.setWeight(RandomUtils.randomDouble(60.0, 100.0));
        prisoner.setBirthDate(LocalDate.of(RandomUtils.randomInt(1960, 2005), RandomUtils.randomInt(1, 12), RandomUtils.randomInt(1, 28)));
        prisoner.setPassport(randomString());
        prisoner.setFavoriteDish(favoriteDishId);

        return dslContext.insertInto(PRISONER)
                .set(new PrisonerRecord(prisoner))
                .returning()
                .fetchOneInto(Prisoner.class);
    }
}
