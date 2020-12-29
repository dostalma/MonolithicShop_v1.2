package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.configuration.PersistenceConfiguration;
import cz.dostalma.monolithicshop.model.Product;
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

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@TestPropertySource("classpath:persistence-test.properties")
@Transactional
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> products = productRepository.findAll();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() == 2);
        Assert.assertEquals("TestProduct1", products.get(0).getName());
        Assert.assertEquals(Double.valueOf(3.99), products.get(0).getPrice());
    }

    @Test
    public void testFindAllByOrderId() {
        List<Product> products = productRepository.findAllByOrderId(1l);
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals("TestProduct1", products.get(0).getName());
        Assert.assertEquals(Double.valueOf(3.99), products.get(0).getPrice());
    }

}
