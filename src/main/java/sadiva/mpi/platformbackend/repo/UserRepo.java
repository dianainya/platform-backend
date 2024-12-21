package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.pojos.PlatformUser;
import jooq.sadiva.mpi.platformbackend.tables.records.PlatformUserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import sadiva.mpi.platformbackend.entity.PlatformUserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.PLATFORM_USER;
import static jooq.sadiva.mpi.platformbackend.Tables.USER_ROLE;
import static org.jooq.impl.DSL.multiset;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepo {
    private final DSLContext dsl;

    public PlatformUser create(String username, String password) {
        return dsl.insertInto(PLATFORM_USER)
                .set(PLATFORM_USER.USERNAME, username)
                .set(PLATFORM_USER.PASSWORD, password)
                .set(PLATFORM_USER.ACTIVATED, false)
                .returning()
                .fetchOneInto(PlatformUser.class);
    }

    public Optional<PlatformUserEntity> findByUsername(String username) {
        return Optional.ofNullable(dsl.select(
                        PLATFORM_USER,
                        multiset(dsl.select(USER_ROLE.ROLE).from(USER_ROLE)
                                .where(USER_ROLE.USER_ID.eq(PLATFORM_USER.USER_ID))
                                .asTable("roles")))
                .from(PLATFORM_USER)
                .where(PLATFORM_USER.USERNAME.eq(username))
                .fetchOne(this::mapToEntity));
    }


    public boolean isExistByUsername(String username) {
        return dsl.fetchCount(PLATFORM_USER, PLATFORM_USER.USERNAME.eq(username)) > 0;
    }

    private PlatformUserEntity mapToEntity(Record2<PlatformUserRecord, Result<Record1<String>>> platformUserRecordResultRecord2) {
        return new PlatformUserEntity(
                platformUserRecordResultRecord2.component1().getUsername(),
                platformUserRecordResultRecord2.component1().getPassword(),
                platformUserRecordResultRecord2.component1().getActivated(),
                platformUserRecordResultRecord2.component2().map(Record1::value1)
        );
    }

    public void assignRoles(UUID userId, List<String> roles) {
        dsl.deleteFrom(USER_ROLE)
                .where(USER_ROLE.USER_ID.eq(userId))
                .execute();
        for (String role : roles) {
            dsl.insertInto(USER_ROLE)
                    .set(USER_ROLE.USER_ID, userId)
                    .set(USER_ROLE.ROLE, role)
                    .execute();
        }
    }
}
