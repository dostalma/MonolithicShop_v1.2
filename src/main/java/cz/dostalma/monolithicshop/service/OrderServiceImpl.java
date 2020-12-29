package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Order;
import cz.dostalma.monolithicshop.model.ProductInOrder;
import cz.dostalma.monolithicshop.repository.OrderRepository;
import cz.dostalma.monolithicshop.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
}
