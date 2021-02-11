package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.dto.CheckoutOrderDto;
import cz.dostalma.monolithicshop.dto.OrderDto;

import java.util.List;

public interface OrderFacade {

    public void createOrder(CheckoutOrderDto checkoutOrderDto);

    public List<OrderDto> getAllOrdersByCustomerEmail(String email);

}
