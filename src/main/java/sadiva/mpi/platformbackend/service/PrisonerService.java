package sadiva.mpi.platformbackend.service;

import jakarta.validation.Valid;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerCreateReq;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerFilterParam;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerRes;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerUpdateReq;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;
import sadiva.mpi.platformbackend.mapper.PrisonerMapper;
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
    private final DishService dishService;

    @Transactional
    public PrisonerRes save(PrisonerCreateReq dto) {
        if (LocalDate.now().minusYears(18).isBefore(dto.birthDate())) {
            throw new ValidationException("Заключенный не может быть младше 18-ти лет");
        }
        Dish dish = dishService.getByName(dto.favoriteDishName());
        if (dish == null) {
            dish = dishService.createNotImplementedDish(dto.favoriteDishName());
        }

        UUID prisonerId;
        try {
            prisonerId = prisonerRepo.save(prisonerMapper.fromDtoToCreateEntity(dto, dish.getId()));
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
    public PrisonerRes update(UUID id, @Valid PrisonerUpdateReq dto) {
        Dish dish = dishService.getByName(dto.favoriteDishName());
        if (dish == null) {
            dish = dishService.createNotImplementedDish(dto.favoriteDishName());
        }
        UUID prisonerId = prisonerRepo.update(id, dish.getId(), dto.isAlive(), dto.weight());
        return getById(prisonerId);
    }
}
