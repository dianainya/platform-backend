package sadiva.mpi.platformbackend.repo;

import jooq.sadiva.mpi.platformbackend.tables.PlatformRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

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
}
