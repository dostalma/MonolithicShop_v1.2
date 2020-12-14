package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductFacade {

    List<Product> getAllProducts();

    List<Product> getProductsByIds(Set<Long> productIds);

    Optional<Product> getProductById(Long id);

}
