package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.configuration.PersistenceConfiguration;
import cz.dostalma.monolithicshop.model.Order;
import cz.dostalma.monolithicshop.model.Product;
import cz.dostalma.monolithicshop.model.ProductInOrder;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@TestPropertySource("classpath:persistence-test.properties")
@Transactional
@ActiveProfiles("test")
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testFindAllOrders() {
        List<Order> orders = orderRepository.findAll();
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() == 1);
    }

    @Test
    public void testFindAllByCustomerId() {
        List<Order> orders = orderRepository.findAllByCustomerId(1l);
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() == 1);
    }

    @Test
    public void testSaveOrder() {
        Optional<Product> product = productRepository.findById(2l);
        Order order = new Order.OrderBuilder()
                .withCustomerId(1l)
                .withShippingAddressId(1l)
                .withPaymentMethodId(1l)
                .withTotalPrice(123.0)
                .withProducts(new HashSet<ProductInOrder>() {{ new ProductInOrder(product.get(), null, 1); }})
                .build();
        orderRepository.saveAndFlush(order);

        List<Order> orders = orderRepository.findAll();
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() == 2);
    }
}
