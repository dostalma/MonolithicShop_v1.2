package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Order;
import cz.dostalma.monolithicshop.model.ProductInOrder;
import cz.dostalma.monolithicshop.repository.OrderRepository;
import cz.dostalma.monolithicshop.repository.ProductInOrderRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Order saveOrder(Order order) {
        Order savedOrderEntity = orderRepository.saveAndFlush(order);

        order.getProducts().forEach(productInOrder -> productInOrder.setId(
                new ProductInOrder.ProductsInOrderKey(productInOrder.getProduct().getId(), savedOrderEntity.getId())));
        productInOrderRepository.saveAll(order.getProducts());

        return savedOrderEntity;
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(Long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        orders.forEach(order -> Hibernate.initialize(order.getProducts()));

        return orders;
    }

    @Override
    public List<Order> getAllOrdersByCustomerEmail(String email) {
        List<Order> orders = orderRepository.findAllOrdersByCustomerEmail(email);
        orders.forEach(order -> Hibernate.initialize(order.getProducts()));

        return orders;
    }
}
