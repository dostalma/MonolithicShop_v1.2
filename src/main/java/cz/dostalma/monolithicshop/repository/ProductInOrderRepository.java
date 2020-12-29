package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrder.ProductsInOrderKey> {
}
