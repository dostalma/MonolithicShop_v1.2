package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Product;
import cz.dostalma.monolithicshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getByName(String name) {
        return Optional.ofNullable(productRepository.findByName(name));
    }

    @Override
    public void deleteByName(String name) {
        productRepository.deleteByName(name);
    }
}
