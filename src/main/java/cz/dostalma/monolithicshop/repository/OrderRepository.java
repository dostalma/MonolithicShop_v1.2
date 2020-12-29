package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.Order;
import cz.dostalma.monolithicshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long id);

}
