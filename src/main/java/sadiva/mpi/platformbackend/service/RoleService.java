package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sadiva.mpi.platformbackend.dto.admin.user.RolesRes;
import sadiva.mpi.platformbackend.repo.RoleRepo;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public RolesRes getAll() {
        return new RolesRes(roleRepo.getAll());
    }
}
