package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.dto.ProductDto;

import java.util.List;
import java.util.Set;

public interface ProductFacade {

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByIds(List<Long> productIds);

    ProductDto getProductById(Long id);

    void createProduct(ProductDto product);

    void updateProduct(ProductDto product);
}
