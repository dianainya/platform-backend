package sadiva.mpi.platformbackend.controller.rest.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sadiva.mpi.platformbackend.dto.PageResponseDto;
import sadiva.mpi.platformbackend.dto.product.ProductCreateOrUpdateDtoReq;
import sadiva.mpi.platformbackend.dto.product.ProductDtoRes;
import sadiva.mpi.platformbackend.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/products")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('admin, prisoner_register', 'cook')")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PageResponseDto<ProductDtoRes> getPaginated(@ParameterObject @PageableDefault(page = 1) Pageable pageable) {
        return productService.getPaginated(pageable);
    }

    @GetMapping("/{id}")
    public ProductDtoRes getById(@PathVariable UUID id) {
        return productService.getById(id);
    }


    @PostMapping
    public ProductDtoRes save(@RequestBody @Valid ProductCreateOrUpdateDtoReq productCreateOrUpdateDtoReq) {
        return productService.save(productCreateOrUpdateDtoReq);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductDtoRes update(@PathVariable UUID id,
                                @RequestBody @Valid ProductCreateOrUpdateDtoReq productCreateOrUpdateDtoReq) {
        return productService.update(id, productCreateOrUpdateDtoReq);
    }

//    @PatchMapping("/{id}")
//    @Operation(summary = "Change product amount")
//    public ProductDtoRes changeAmount(@PathVariable UUID id, @RequestParam Integer amount) {
//        return productService.changeAmount(id, amount);
//    }

}
