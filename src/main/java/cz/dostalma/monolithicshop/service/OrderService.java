package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Order;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order);

    List<Order> getAllOrdersByCustomerId(Long id);

    List<Order> getAllOrdersByCustomerEmail(String email);

}
