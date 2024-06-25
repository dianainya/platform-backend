package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PlatformEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.mapper.PlatformMapper;
import sadiva.mpi.platformbackend.repo.PlatformRepo;
import sadiva.mpi.platformbackend.repo.PrisonerRepo;

import java.util.List;

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
}
