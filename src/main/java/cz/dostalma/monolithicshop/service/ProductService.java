package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByIds(Set<Long> productIds);

    Optional<Product> getByName(String name);

    Optional<Product> getById(Long id);

    void deleteByName(String name);
}
