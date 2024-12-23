package sadiva.mpi.platformbackend.mapper;

import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.user.UserRes;
import sadiva.mpi.platformbackend.entity.PlatformUserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserRes> entitiesToDto(List<PlatformUserEntity> content);
}
