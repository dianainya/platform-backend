package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.PlatformRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static jooq.sadiva.mpi.platformbackend.Tables.USER_ROLE;
import static jooq.sadiva.mpi.platformbackend.tables.PlatformRole.PLATFORM_ROLE;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleRepo {
    private final DSLContext dsl;

    public void create(String name) {
        dsl.insertInto(PLATFORM_ROLE)
                .set(PLATFORM_ROLE.NAME, name)
                .execute();
    }

    public PlatformRole findByName(String name) {
        return dsl.selectFrom(PLATFORM_ROLE)
                .where(PLATFORM_ROLE.NAME.eq(name))
                .fetchOneInto(PlatformRole.class);
    }

    public List<String> getAll() {
        return dsl.select(PLATFORM_ROLE.NAME).from(PLATFORM_ROLE).fetch(PLATFORM_ROLE.NAME);
    }

    public void batchSave(UUID userId, List<String> roles) {
        var batch = dsl.batch(dsl.insertInto(USER_ROLE)
                .set(USER_ROLE.USER_ID, (UUID) null)
                .set(USER_ROLE.ROLE, (String) null));

        for (String role : roles) {
            batch = batch.bind(userId, role);
        }
        batch.execute();
    }

    public void deleteByUserId(UUID userId) {
        dsl.delete(USER_ROLE)
                .where(USER_ROLE.USER_ID.eq(userId))
                .execute();
    }
}
