package sadiva.mpi.platformbackend.mapper;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerCreateReq;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerRes;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PrisonerMapper {
    @Mapping(target = "favoriteDish", source = "favoriteDish")
    Prisoner fromDtoToCreateEntity(PrisonerCreateReq dto, UUID favoriteDish);
    PrisonerRes fromEntityToDto(PrisonerEntity entity);
    
    List<PrisonerRes> fromEntityToDtoList(List<PrisonerEntity> content);
}
