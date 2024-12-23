package sadiva.mpi.platformbackend.mapper;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Dish;
import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import org.jooq.Record2;
import org.jooq.Result;
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


    static DishEntity mapDishEntity(Dish dish, Result<Record2<Integer, Product>> productsRecord) {
        return new DishEntity(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getReceipt(),
                productsRecord.stream().map(record -> new IngredientEntity(
                        record.component1(),
                        record.component2()
                )).toList());
    }
}
