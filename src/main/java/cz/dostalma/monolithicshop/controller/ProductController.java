package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.facade.ProductFacade;
import cz.dostalma.monolithicshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductFacade productFacade;

    @GetMapping
    public String getProducts(Model model) {
        logger.info("Request to retrieve all products");
        List<Product> list = productFacade.getAllProducts();

        model.addAttribute("products", list);
        return "products";

    }

}
