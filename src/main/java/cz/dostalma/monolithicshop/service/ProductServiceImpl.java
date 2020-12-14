package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Product;
import cz.dostalma.monolithicshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByIds(Set<Long> productIds) {
        return productRepository.findAllById(productIds);
    }

    @Override
    public Optional<Product> getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteByName(String name) {
        productRepository.deleteByName(name);
    }
}
