package sadiva.mpi.platformbackend.mapper;

import jooq.sadiva.mpi.platformbackend.tables.pojos.PrisonerViolation;
import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.bars.ViolationRes;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarsMapper {
    List<ViolationRes> violationToDtoFromEntity(List<PrisonerViolation> allViolations);
}
