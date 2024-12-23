package sadiva.mpi.platformbackend.dto.menu;

import java.util.UUID;

public record DishPickupReq(
        UUID dishId,
        Integer amount
) {
}