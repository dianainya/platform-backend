package sadiva.mpi.platformbackend.service;

import jooq.sadiva.mpi.platformbackend.tables.pojos.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.product.ProductCreateOrUpdateDtoReq;
import sadiva.mpi.platformbackend.dto.product.ProductDtoRes;
import sadiva.mpi.platformbackend.entity.PageEntity;
import sadiva.mpi.platformbackend.mapper.ProductMapper;
import sadiva.mpi.platformbackend.repo.ProductRepo;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public ProductDtoRes save(ProductCreateOrUpdateDtoReq productCreateOrUpdateDtoReq) {
        Product product;
        try {
            product = productRepo.save(productMapper.fromDtoToEntity(productCreateOrUpdateDtoReq));
        } catch (Exception e) {
            throw new ValidationException("Product with name " + productCreateOrUpdateDtoReq.name() + " already exists");
        }
        return productMapper.fromEntityToDto(product);
    }

    public ProductDtoRes getById(UUID id) {
        return productMapper.fromEntityToDto(productRepo.getById(id));
    }

    public PageResponseDto<ProductDtoRes> getPaginated(Pageable pageable) {
        PageEntity<Product> contentAndTotal = productRepo.getAllPaginated(pageable);
        return new PageResponseDto<>(
                productMapper.fromEntityToDtoList(contentAndTotal.content()),
                contentAndTotal.total(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    public void deleteById(UUID id) {
        productRepo.deleteById(id);
    }

    public ProductDtoRes update(UUID id, ProductCreateOrUpdateDtoReq productCreateOrUpdateDtoReq) {
        Product product = productRepo.update(id, productMapper.fromDtoToEntity(productCreateOrUpdateDtoReq));
        return productMapper.fromEntityToDto(product);
    }

//    public ProductDtoRes changeAmount(UUID id, Integer amount) {
//        Product product = productRepo.changeAmount(id, amount);
//        return productMapper.fromEntityToDto(product);
//    }
}
