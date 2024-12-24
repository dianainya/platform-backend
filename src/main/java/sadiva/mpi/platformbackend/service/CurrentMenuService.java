package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.dto.admin.menu.DishPickupReq;
import sadiva.mpi.platformbackend.entity.CurrentMenuEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.mapper.CurrentMenuMapper;
import sadiva.mpi.platformbackend.repo.CurrentMenuRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentMenuService {
    private final CurrentMenuRepo currentMenuRepo;
    private final CurrentMenuMapper currentMenuMapper;

    public PageResponseDto<CurrentMenuRes> getCurrentMenu(Pageable pageable) {
        PageEntity<CurrentMenuEntity> res = currentMenuRepo.getAllPaginated(pageable);
        return new PageResponseDto<>(
                currentMenuMapper.mapToMenuDtoList(res.content()),
                res.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    public void pickDishes(List<DishPickupReq> dishPickupReqs) {
        currentMenuRepo.pickDishes(currentMenuMapper.mapToDishPickupEntities(dishPickupReqs));
    }
}
