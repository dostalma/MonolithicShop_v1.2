package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<PaymentMethod> getAllPaymentMethods();

    Optional<PaymentMethod> getPaymentMethodById(Long id);
}
