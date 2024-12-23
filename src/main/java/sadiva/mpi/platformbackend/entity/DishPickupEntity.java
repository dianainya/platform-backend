package sadiva.mpi.platformbackend.entity;

import java.util.UUID;

public record DishPickupEntity (
        UUID dishId,
        Integer amount
){
}
