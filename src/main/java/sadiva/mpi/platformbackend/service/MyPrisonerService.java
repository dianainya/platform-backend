package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.admin.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.dto.admin.menu.DishPickupReq;
import sadiva.mpi.platformbackend.dto.publics.prisonser.PrisonerFloorRes;
import sadiva.mpi.platformbackend.repo.PlatformRepo;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPrisonerService {
    private final PlatformRepo platformRepo;
    private final CurrentMenuService currentMenuService;

    public PrisonerFloorRes getMyFloor(String username) {
        Integer currentFloor = platformRepo.getCurrentActiveFloor();
        Integer myFloor = platformRepo.getMyFloor(username);
        return new PrisonerFloorRes(myFloor, currentFloor);
    }

    public PageResponseDto<CurrentMenuRes> getCurrentMenu(String username, Pageable pageable) {
        checkFloorOrThrow(username);
        return currentMenuService.getCurrentMenu(pageable);
    }

    public void pickDishes(String username, List<DishPickupReq> dishPickupReqs) {
        checkFloorOrThrow(username);
        currentMenuService.pickDishes(dishPickupReqs);
    }

    private void checkFloorOrThrow(String username) {
        Integer currentFloor = platformRepo.getCurrentActiveFloor();
        Integer myFloor = platformRepo.getMyFloor(username);
        if (!Objects.equals(currentFloor, myFloor)) {
            throw new ValidationException("ПЛатформа не твоем этаже");
        }
    }
}
