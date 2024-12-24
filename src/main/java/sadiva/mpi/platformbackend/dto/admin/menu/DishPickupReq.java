package sadiva.mpi.platformbackend.dto.admin.menu;

import java.util.UUID;

public record DishPickupReq(
        UUID dishId,
        Integer amount
) {
}