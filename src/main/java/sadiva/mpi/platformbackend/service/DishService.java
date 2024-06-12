package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.dish.DishCreateOrUpdateReqDto;
import sadiva.mpi.platformbackend.dto.dish.DishDtoRes;
import sadiva.mpi.platformbackend.dto.dish.DishFilterParam;
import sadiva.mpi.platformbackend.entity.DishEntity;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.mapper.DishMapper;
import sadiva.mpi.platformbackend.repo.DishRepo;
import sadiva.mpi.platformbackend.service.exception.NotFoundException;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo dishRepo;
    private final DishMapper dishMapper;

    public PageResponseDto<DishDtoRes> getPaginated(DishFilterParam filterParam, Pageable pageable) {
        PageEntity<DishEntity> pageEntity = dishRepo.getPaginated(filterParam, pageable);

        return new PageResponseDto<>(
                dishMapper.fromEntitiesToDtoList(pageEntity.content()),
                pageEntity.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    public DishDtoRes getById(UUID id) {
        DishEntity dish = dishRepo.getById(id);
        if (dish == null) {
            throw new NotFoundException("Блюдо с ID " + id + " не найдено");
        }
        return dishMapper.fromEntityToDto(dish);
    }

    @Transactional
    public DishDtoRes save(DishCreateOrUpdateReqDto dto) {
        UUID dishId;
        try {
            dishId = dishRepo.save(dishMapper.fromDtoToEntity(dto), dto.ingredients());
        } catch (DuplicateKeyException e) {
            throw new ValidationException("Блюдо с названием " + dto.name() + " уже существует");
        }
        return getById(dishId);
    }

    @Transactional
    public DishDtoRes update(UUID id, DishCreateOrUpdateReqDto dto) {
        UUID dishId = dishRepo.update(id, dishMapper.fromDtoToEntity(dto), dto.ingredients());
        if (dishId == null) {
            throw new NotFoundException("Блюдо с ID " + id + " не найдено");
        }
        return getById(dishId);
    }

    public void deleteById(UUID id) {
        dishRepo.deleteById(id);
    }
}
