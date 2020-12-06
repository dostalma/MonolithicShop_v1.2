package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);

}
