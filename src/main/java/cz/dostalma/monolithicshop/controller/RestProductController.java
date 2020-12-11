package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.facade.ProductFacade;
import cz.dostalma.monolithicshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class RestProductController {

    private static final Logger logger
            = LoggerFactory.getLogger(RestProductController.class);

    @Autowired
    ProductFacade productFacade;

    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("REST Request to retrieve all products");
        List<Product> list = productFacade.getAllProducts();
        return list;
    }

}
