package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getByName(String name);

    void deleteByName(String name);
}
