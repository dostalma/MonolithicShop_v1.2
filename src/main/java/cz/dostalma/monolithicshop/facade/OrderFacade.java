package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.dto.CheckoutOrderDto;
import cz.dostalma.monolithicshop.dto.OrderDto;

public interface OrderFacade {

    public void createOrder(CheckoutOrderDto checkoutOrderDto);

}
