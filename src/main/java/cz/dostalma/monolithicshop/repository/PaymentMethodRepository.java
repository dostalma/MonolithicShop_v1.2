package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
