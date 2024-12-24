package sadiva.mpi.platformbackend.mapper;

import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.admin.platform.PlatformStructureRes;
import sadiva.mpi.platformbackend.entity.PlatformEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    List<PlatformStructureRes> entityToDto(List<PlatformEntity> platformStructure);
}
