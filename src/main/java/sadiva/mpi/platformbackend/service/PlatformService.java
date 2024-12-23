package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.platform.PlatformActiveFloorRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformDistribAvailabilityRes;
import sadiva.mpi.platformbackend.dto.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PlatformEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.mapper.PlatformMapper;
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

    @Transactional
    public void distributePrisoners() {
        List<PrisonerEntity> prisonerList = prisonerRepo.getAllPrisonerOrderByRating();
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

    @Transactional
    public void downFloor() {
        final Integer maxFloor = platformRepo.getMaxFloor();
        final Integer activeFloor = platformRepo.getCurrentActiveFloor();

        if (Objects.equals(maxFloor, activeFloor)) {
            throw new ValidationException("Плафторма достигла низа");
        }

        this.platformRepo.updateActiveFloor(activeFloor + 1);
    }

    public void finish() {
        this.platformRepo.updateActiveFloor(0);
    }

    public void start() {
        this.platformRepo.updateActiveFloor(1);
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
        return new PlatformDistribAvailabilityRes(true, null);
    }
}
