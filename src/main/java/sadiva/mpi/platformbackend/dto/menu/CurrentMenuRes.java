package sadiva.mpi.platformbackend.dto.menu;

import sadiva.mpi.platformbackend.dto.dish.DishDtoRes;

public record CurrentMenuRes(
        DishDtoRes dish,
        Integer amount
) {
}
