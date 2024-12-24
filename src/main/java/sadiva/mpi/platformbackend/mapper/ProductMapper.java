package sadiva.mpi.platformbackend.mapper;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import org.mapstruct.Mapper;
import sadiva.mpi.platformbackend.dto.admin.product.ProductCreateOrUpdateDtoReq;
import sadiva.mpi.platformbackend.dto.admin.product.ProductDtoRes;
import sadiva.mpi.platformbackend.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromDtoToEntity(ProductCreateOrUpdateDtoReq productCreateOrUpdateDtoReq);

    ProductDtoRes fromEntityToDto(ProductEntity entity);

    List<ProductDtoRes> fromEntityToDtoList(List<ProductEntity> entityList);
}
