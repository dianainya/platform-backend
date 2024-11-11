package test.sadiva.mpi.platformbackend.utils;

import jooq.sadiva.mpi.platformbackend.tables.pojos.PlatformUser;
import jooq.sadiva.mpi.platformbackend.tables.records.PlatformUserRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import static jooq.sadiva.mpi.platformbackend.Tables.PLATFORM_USER;
import static test.sadiva.mpi.platformbackend.utils.RandomUtils.randomString;

@RequiredArgsConstructor
public class PlatformUserUtils {
    private final DSLContext dslContext;

    public PlatformUser createPlatformUser() {
        PlatformUser user = new PlatformUser();
        user.setUsername(randomString());
        user.setPassword(randomString());
        user.setActivated(RandomUtils.randomBoolean());

        return dslContext.insertInto(PLATFORM_USER)
                .set(new PlatformUserRecord(user))
                .returning()
                .fetchOneInto(PlatformUser.class);
    }
}
