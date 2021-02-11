package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.configuration.PersistenceConfig;
import cz.dostalma.monolithicshop.model.ProductInOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@TestPropertySource("classpath:persistence-test.properties")
@Transactional
@ActiveProfiles("test")
public class ProductInOrderRepositoryTest {

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Test
    public void test1() {
        Set<ProductInOrder> productInOrderSet =  productInOrderRepository.getAllProductInOrderOfCustomerByEmail("user1@foo.com");

        assertTrue(productInOrderSet.size() == 2);
    }

}