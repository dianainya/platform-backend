package sadiva.mpi.platformbackend.dto.admin.menu;

import sadiva.mpi.platformbackend.dto.admin.dish.DishDtoRes;

public record CurrentMenuRes(
        DishDtoRes dish,
        Integer amount
) {
}
