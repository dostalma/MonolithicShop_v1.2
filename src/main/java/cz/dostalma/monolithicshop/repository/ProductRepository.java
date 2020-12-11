package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    void deleteByName(String name);
}
