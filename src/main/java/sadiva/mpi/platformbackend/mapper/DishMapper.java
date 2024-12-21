package sadiva.mpi.platformbackend.mapper;

import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.dish.DishCreateOrUpdateReqDto;
import sadiva.mpi.platformbackend.dto.dish.DishDtoRes;
import sadiva.mpi.platformbackend.entity.DishEntity;
import sadiva.mpi.platformbackend.entity.IngredientEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishEntity fromDtoToEntity(DishCreateOrUpdateReqDto productCreateOrUpdateDtoReq);

    DishDtoRes fromEntityToDto(DishEntity dish);
    List<DishDtoRes> fromEntitiesToDtoList(List<DishEntity> dishes);
    DishDtoRes.IngredientDtoRes fromEntityToDto(IngredientEntity ingredient);
}
