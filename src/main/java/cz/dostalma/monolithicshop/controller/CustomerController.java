package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.dto.CustomerDto;
import cz.dostalma.monolithicshop.facade.CustomerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger
            = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerFacade customerFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String customerDetails(Model model, @PathVariable Long id) {
        logger.info("Request to retrieve a customer by id");
        CustomerDto customer = customerFacade.getCustomerById(id);

        model.addAttribute("customer", customer);
        return "pages/customer-details";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute CustomerDto customer, Model model) {
        customerFacade.updateCustomer(customer);

        model.addAttribute("customer", customer);

        return "pages/customer-details";
    }
}
