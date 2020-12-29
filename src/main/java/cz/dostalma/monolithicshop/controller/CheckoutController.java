package cz.dostalma.monolithicshop.controller;

import cz.dostalma.monolithicshop.CheckoutStep;
import cz.dostalma.monolithicshop.dto.*;
import cz.dostalma.monolithicshop.facade.CustomerFacade;
import cz.dostalma.monolithicshop.facade.OrderFacade;
import cz.dostalma.monolithicshop.facade.ProductFacade;
import cz.dostalma.monolithicshop.validation.CheckoutStepValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private static final Logger logger
            = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private CheckoutOrderDto checkoutOrderDto;

    @Autowired
    private CheckoutStepValidator checkoutStepValidator;

    @GetMapping
    public String proceedToCheckout(@CookieValue(value = "basket-content", required = false) String basketContent, Model model) {
        // @TODO add additional initial condition
        if (StringUtils.isEmpty(basketContent) /* @TODO further validation */) {
            logger.info("Blocked unauthorized attempt to enter checkout");
            return "redirect:/";
        }

        CheckoutStep validationResultStep = checkoutStepValidator.validate(CheckoutStep.STEP_ADDRESS, checkoutOrderDto);
        if (!CheckoutStep.STEP_ADDRESS.equals(validationResultStep)) {
            return validationResultStep.getRedirectionLink();
        }

        List<Long> productIds = new ArrayList<>();
        for (String strId : basketContent.split(";")) {
            try {
                if (!StringUtils.isEmpty(strId)  && StringUtils.isNumeric(strId)) {
                    productIds.add(Long.valueOf(strId));
                } else {
                    logger.info("Received non-numerical product id: " + strId);
                }
            } catch (NumberFormatException ex) {
                logger.error("Error during parsing of basket product id: " + strId, ex);
            }
        }
        List<ProductDto> productList = productFacade.getProductsByIds(productIds);
        HashMap<ProductDto, Integer> productQuantitiesMap = new HashMap<>();
        for (Long productId : productIds) {
            for (ProductDto prod : productList) {
                if (productId.equals(prod.getId())) {
                    if (productQuantitiesMap.containsKey(prod)) {
                        productQuantitiesMap.replace(prod, productQuantitiesMap.get(prod) + 1);
                    } else {
                        productQuantitiesMap.put(prod, 1);
                    }
                }
            }
        }

        checkoutOrderDto.setProducts(productQuantitiesMap);

        List<CustomerDto> customers = customerFacade.getAllCustomers();

        CustomerDto customerDto = customerFacade.getCustomerById(1l);

        if (customerDto.getAddress() == null) {
            customerDto.setAddress(customerFacade.getAddressById(2l));
        }

        model.addAttribute("customers", customers);

        model.addAttribute("customerDto", customerDto);

        return CheckoutStep.STEP_ADDRESS.getPage();
    }

    @PostMapping("/submitAddress")
        public String submitAddress(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "pages/checkout-address";
        }
        customerFacade.registerCustomer(customerDto);
        checkoutOrderDto.setCustomer(customerDto);

        return CheckoutStep.STEP_PAYMENT.getRedirectionLink();
    }

    @GetMapping("/payment")
    public String proceedToPayment(Model model) {
        CheckoutStep validationResultStep = checkoutStepValidator.validate(CheckoutStep.STEP_PAYMENT, checkoutOrderDto);
        if (!CheckoutStep.STEP_PAYMENT.equals(validationResultStep)) {
            return validationResultStep.getRedirectionLink();
        }

        List<PaymentMethodDto> paymentMethods = customerFacade.getAllPaymentMethods();

        model.addAttribute("paymentMethods", paymentMethods);
        model.addAttribute("selectedPaymentMethod", new PaymentMethodDto());

        return CheckoutStep.STEP_PAYMENT.getPage();
    }

    @PostMapping("/submitPayment")
    public String submitPayment(@ModelAttribute PaymentMethodDto selectedPaymentMethod, Model model) {

        PaymentMethodDto fullPaymentMethod = customerFacade.getPaymentMethodById(selectedPaymentMethod.getId());
        checkoutOrderDto.setPaymentMethod(fullPaymentMethod);

        return CheckoutStep.STEP_CONFIRMATION.getRedirectionLink();
    }

    @GetMapping("/confirmation")
    public String proceedToConfirmation(Model model) {
        CheckoutStep validationResultStep = checkoutStepValidator.validate(CheckoutStep.STEP_CONFIRMATION, checkoutOrderDto);
        if (!CheckoutStep.STEP_CONFIRMATION.equals(validationResultStep)) {
            return validationResultStep.getRedirectionLink();
        }

        model.addAttribute("products", checkoutOrderDto.getProducts());
        model.addAttribute("customer", checkoutOrderDto.getCustomer());
        model.addAttribute("paymentMethod", checkoutOrderDto.getPaymentMethod());

        return CheckoutStep.STEP_CONFIRMATION.getPage();
    }

    @PostMapping("/submitOrder")
    public String submitOrder() {
        checkoutOrderDto.setOrderSubmitted(true);

        // @TODO create final Order
        orderFacade.createOrder(checkoutOrderDto);

        return CheckoutStep.STEP_COMPLETE.getRedirectionLink();
    }

    @GetMapping("/complete")
    public String showConfirmationPage(Model model) {
        CheckoutStep validationResultStep = checkoutStepValidator.validate(CheckoutStep.STEP_COMPLETE, checkoutOrderDto);
        if (!CheckoutStep.STEP_COMPLETE.equals(validationResultStep)) {
            return validationResultStep.getRedirectionLink();
        }

        checkoutOrderDto.setOrderSubmitted(false);
        checkoutOrderDto.clearProductsAndPaymentMethod();

        model.addAttribute("deleteBasketCookieFlag", "true");

        return CheckoutStep.STEP_COMPLETE.getPage();
    }
}
