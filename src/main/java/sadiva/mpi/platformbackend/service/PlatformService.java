package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.platform.PlatformActiveFloorRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformDistribAvailabilityRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.entity.DishCountEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PlatformEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.mapper.PlatformMapper;
import sadiva.mpi.platformbackend.repo.CurrentMenuRepo;
import sadiva.mpi.platformbackend.repo.PlatformRepo;
import sadiva.mpi.platformbackend.repo.PrisonerRepo;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PrisonerRepo prisonerRepo;
    private final PlatformRepo platformRepo;
    private final PlatformMapper platformMapper;
    private final CurrentMenuRepo currentMenuRepo;
    private final TransactionTemplate transactionTemplate;

    @Transactional
    public void distributePrisoners() {
        List<PrisonerEntity> prisonerList = prisonerRepo.getAllAlivePrisonerOrderByRating();
        finish();
        platformRepo.truncate();
        platformRepo.distributePrisoners(prisonerList);
    }

    public PageResponseDto<PlatformStructureRes> getPlatformStructure(Pageable pageable) {
        PageEntity<PlatformEntity> res = platformRepo.getPlatformStructure(pageable);
        return new PageResponseDto<>(
                platformMapper.entityToDto(res.content()),
                res.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    public void downFloor() {
        transactionTemplate.execute(status -> {
            final Integer maxFloor = platformRepo.getMaxFloor();
            final Integer currentActiveFloor = platformRepo.getCurrentActiveFloor();
            final Integer nextFloor = platformRepo.getNextActiveFloor(currentActiveFloor);

            if (Objects.equals(maxFloor, currentActiveFloor)) {
                throw new ValidationException("Плафторма достигла низа");
            }
            this.platformRepo.updateActiveFloor(nextFloor);
            return null;
        });
    }

    public void finish() {
        transactionTemplate.execute(status -> {
            platformRepo.updateActiveFloor(0);
            currentMenuRepo.truncate();
            return null;
        });
    }

    public void start() {
        transactionTemplate.execute(status -> {
            finish();
            createCurrentMenu();
            downFloor();
            return null;
        });
    }

    public PlatformActiveFloorRes getActiveFloor() {
        final Integer activeFloor = this.platformRepo.getCurrentActiveFloor();
        return new PlatformActiveFloorRes(activeFloor);
    }

    public PlatformDistribAvailabilityRes getDistributeAvailability() {
        List<PrisonerEntity> prisonerList = prisonerRepo.getAll(new PrisonerFilterParam(null, false));

        if (!prisonerList.isEmpty()) {
            return new PlatformDistribAvailabilityRes(false, "Не у всех заключенных заполнено блюдо");
        }
        if (platformRepo.isPlatformActive()) {
            return new PlatformDistribAvailabilityRes(false, "Плафторма запущена");
        }

        return new PlatformDistribAvailabilityRes(true, null);
    }


    private void createCurrentMenu() {
        List<DishCountEntity> dishCounts = platformRepo.getAllDishesAmountInPlatform();
        int rowAffected = currentMenuRepo.createCurrentMenu(dishCounts);
        log.info("Created current menu. Affected rows: {}", rowAffected);
    }
}
