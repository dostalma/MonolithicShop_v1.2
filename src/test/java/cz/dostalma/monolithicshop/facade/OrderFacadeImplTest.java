package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.configuration.PersistenceConfig;
import cz.dostalma.monolithicshop.dto.OrderDto;
import cz.dostalma.monolithicshop.repository.OrderRepository;
import cz.dostalma.monolithicshop.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, OrderFacadeImpl.class, ModelMapper.class,
        CustomerServiceImpl.class, AddressServiceImpl.class, PaymentServiceImpl.class, OrderServiceImpl.class,
        ProductServiceImpl.class})
@TestPropertySource("classpath:persistence-test.properties")
@Transactional
@ActiveProfiles("test")
public class OrderFacadeImplTest {

    @Autowired
    OrderFacade orderFacade;

    @Test
    public void createOrder() {
    }

    @Test
    public void getAllOrdersByCustomerEmail() {
        List<OrderDto> orders =  orderFacade.getAllOrdersByCustomerEmail("user1@foo.com");

        Assert.assertTrue(orders.size() == 2);
        Assert.assertTrue(orders.get(0).getProductMap().size() == 2);
        Assert.assertTrue(orders.get(1).getProductMap().size() == 2);
    }
}