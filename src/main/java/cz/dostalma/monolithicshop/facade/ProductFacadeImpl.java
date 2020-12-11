package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.model.Product;
import cz.dostalma.monolithicshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacadeImpl implements ProductFacade {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductFacadeImpl.class);

    @Autowired
    ProductService productService;

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
