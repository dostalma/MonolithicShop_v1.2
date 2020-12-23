package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerById(Long id);

    void deleteCustomerByEmail(String email);

    void saveCustomer(Customer customer);
}
