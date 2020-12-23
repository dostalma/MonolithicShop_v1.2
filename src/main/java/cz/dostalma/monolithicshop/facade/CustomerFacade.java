package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.dto.CustomerDto;

import java.util.List;

public interface CustomerFacade {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    void createNewCustomer(CustomerDto customer);

    void registerCustomer(CustomerDto customer);

    void updateCustomer(CustomerDto customer);

}
