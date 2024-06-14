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
public class PrisonerService {
    private final PrisonerRepo prisonerRepo;
    private final PrisonerMapper prisonerMapper;
    private final DishRepo dishRepo;

    @Transactional
    public PrisonerRes save(PrisonerCreateOrUpdateReq dto) {
        if (LocalDate.now().minusYears(18).isBefore(dto.birthDate())) {
            throw new ValidationException("Заключенный не может быть младше 18-ти лет");
        }
        if (!dishRepo.isExist(dto.favoriteDish())) {
            throw new ValidationException("Указанного блюда не существует");
        }

        UUID prisonerId;
        try {
            prisonerId = prisonerRepo.save(prisonerMapper.fromDtoToEntity(dto));
        } catch (DuplicateKeyException e) {
            throw new ValidationException("Заключенный с указанным паспортом уже зарегистрирован");
        }

        return getById(prisonerId);
    }

    public PrisonerRes getById(UUID id) {
        return prisonerMapper.fromEntityToDto(prisonerRepo.getById(id));
    }

    public PageResponseDto<PrisonerRes> getPaginated(Pageable pageable, PrisonerFilterParam filterParam) {
        PageEntity<PrisonerEntity> contentAndTotal = prisonerRepo.getAllPaginated(pageable, filterParam);
        return new PageResponseDto<>(
                prisonerMapper.fromEntityToDtoList(contentAndTotal.content()),
                contentAndTotal.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    public void deleteById(UUID id) {
        prisonerRepo.deleteById(id);
    }


    @Transactional
    public PrisonerRes update(UUID id, PrisonerCreateOrUpdateReq dto) {
        UUID prisonerId = prisonerRepo.update(id, prisonerMapper.fromDtoToEntity(dto));
        return getById(prisonerId);
    }
}
