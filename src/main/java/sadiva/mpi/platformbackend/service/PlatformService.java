package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerCreateOrUpdateReq;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerRes;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.mapper.PrisonerMapper;
import sadiva.mpi.platformbackend.repo.DishRepo;
import sadiva.mpi.platformbackend.repo.PrisonerRepo;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PrisonerRepo prisonerRepo;

    public void distributePrisoners() {
//        prisonerRepo.getAllWith
    }
}
