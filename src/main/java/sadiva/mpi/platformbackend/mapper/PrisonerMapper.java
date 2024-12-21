package sadiva.mpi.platformbackend.mapper;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Prisoner;
import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerCreateOrUpdateReq;
import sadiva.mpi.platformbackend.dto.prisoner.PrisonerRes;
import sadiva.mpi.platformbackend.entity.PrisonerEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrisonerMapper {
    Prisoner fromDtoToEntity(PrisonerCreateOrUpdateReq dto);
    PrisonerRes fromEntityToDto(PrisonerEntity entity);
    
    List<PrisonerRes> fromEntityToDtoList(List<PrisonerEntity> content);
}
