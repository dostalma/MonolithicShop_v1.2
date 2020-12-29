package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.dto.PaymentMethodDto;
import cz.dostalma.monolithicshop.model.PaymentMethod;
import cz.dostalma.monolithicshop.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id);
    }
}
