package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.dto.AddressDto;
import cz.dostalma.monolithicshop.dto.CustomerDto;
import cz.dostalma.monolithicshop.facade.CustomerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private static final Logger logger
            = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    CustomerFacade customerFacade;

    @GetMapping
    public String proceedToCheckout(Model model) {
        // @TODO add initial condition
        if (false) {

        }
        List<CustomerDto> customers = customerFacade.getAllCustomers();

        model.addAttribute("customers", customers);

        model.addAttribute("customer", customerFacade.getCustomerById(1l));
        model.addAttribute("address", new AddressDto());

        return "pages/checkout";
    }

    @PostMapping("/submitAddress")
        public String submitAddress(@ModelAttribute CustomerDto customer, @ModelAttribute AddressDto address, Model model) {

        customer.setAddress(address);
        customerFacade.registerCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("address", address);

        return "pages/checkout";
    }

}
