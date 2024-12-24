package sadiva.mpi.platformbackend.mapper;

import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.admin.menu.DishPickupReq;
import sadiva.mpi.platformbackend.dto.admin.menu.CurrentMenuRes;
import sadiva.mpi.platformbackend.entity.CurrentMenuEntity;
import sadiva.mpi.platformbackend.entity.DishPickupEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrentMenuMapper {
    List<DishPickupEntity> mapToDishPickupEntities(List<DishPickupReq> dishPickupReqs);

    List<CurrentMenuRes> mapToMenuDtoList(List<CurrentMenuEntity> content);
}
