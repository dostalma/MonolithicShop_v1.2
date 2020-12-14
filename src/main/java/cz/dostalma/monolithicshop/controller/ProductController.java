package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.facade.ProductFacade;
import cz.dostalma.monolithicshop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

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
        List<Product> productList = productFacade.getAllProducts();

        model.addAttribute("products", productList);
        return "pages/products";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductByid(Model model, @PathVariable Long id) {
        logger.info("Request to retrieve a product by id");
        Optional<Product> product = productFacade.getProductById(id);

        model.addAttribute("product", product.orElse(null));
        return "pages/product-display";

    }
}
