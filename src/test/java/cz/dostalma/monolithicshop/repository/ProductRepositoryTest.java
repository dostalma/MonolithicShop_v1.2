package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.configuration.PersistenceConfiguration;
import cz.dostalma.monolithicshop.model.Product;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class})
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Before
    public void init() {
        productRepository.save(
                new Product.ProductBuilder()
                .withName("TestProduct1")
                .withPrice(3.99)
                .build());
    }

    @Test
    public void testFindAll() {
        List<Product> products = Lists.newArrayList(productRepository.findAll());
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals("TestProduct1", products.get(0).getName());
        Assert.assertEquals(Double.valueOf(3.99), products.get(0).getPrice());
    }

}
