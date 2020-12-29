package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.model.Customer;
import cz.dostalma.monolithicshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomerByEmail(String email) {
        customerRepository.deleteByEmail(email);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }
}
