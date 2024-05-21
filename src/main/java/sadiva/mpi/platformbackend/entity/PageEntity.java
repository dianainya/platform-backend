package sadiva.mpi.platformbackend.entity;

import java.util.List;

public record PageEntity<T>(
        List<T> content,
        Integer total
) {
}
