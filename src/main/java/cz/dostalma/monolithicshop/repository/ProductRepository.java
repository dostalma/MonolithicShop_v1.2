package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByIdIn(Set<Long> productIds);

    void deleteByName(String name);
}
