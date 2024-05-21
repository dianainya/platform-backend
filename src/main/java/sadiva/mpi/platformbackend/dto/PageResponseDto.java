package sadiva.mpi.platformbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность представляюшая собой страницу пагинации")
public record PageResponseDto<T>(
        List<T> content,
        @Schema(description = "Общее количество сущностей")
        int totalCount,
        @Schema(description = "Текущая страница")
        int page,
        @Schema(description = "Размер страницы")
        int size) {
}
