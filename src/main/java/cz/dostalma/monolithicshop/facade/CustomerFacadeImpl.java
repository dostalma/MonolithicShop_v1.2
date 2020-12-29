package cz.dostalma.monolithicshop.facade;

import cz.dostalma.monolithicshop.dto.AddressDto;
import cz.dostalma.monolithicshop.dto.CustomerDto;
import cz.dostalma.monolithicshop.dto.PaymentMethodDto;
import cz.dostalma.monolithicshop.mapper.DtoToEntityMapper;
import cz.dostalma.monolithicshop.model.Address;
import cz.dostalma.monolithicshop.model.Customer;
import cz.dostalma.monolithicshop.model.PaymentMethod;
import cz.dostalma.monolithicshop.service.AddressService;
import cz.dostalma.monolithicshop.service.CustomerService;
import cz.dostalma.monolithicshop.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("customerMapper")
    DtoToEntityMapper customerEntityMapper;

    @Autowired
    @Qualifier("addressMapper")
    DtoToEntityMapper addressEntityMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return modelMapper.map(customerService.getCustomerById(id).orElse(null), CustomerDto.class);
    }

    @Override
    public void createNewCustomer(CustomerDto customer) {
        // @TODO
    }

    @Override
    public void registerCustomer(CustomerDto dto) {
        if (dto == null) {
            return;
        }
        Customer customerEntity = customerService.getCustomerByEmail(dto.getEmail()).orElse(new Customer());
        customerEntityMapper.map(dto, customerEntity);
        customerService.saveCustomer(customerEntity);

        AddressDto addressDto = dto.getAddress();
        if (addressDto != null) {
            Long customerId = customerService.getCustomerByEmail(dto.getEmail()).get().getId();
            addressDto.setCustomerId(customerId);
            Optional<Address> addressEntityOpt = addressService.getAddressByDtoFields(addressDto);
            if (addressEntityOpt.isPresent()) {
                return;
            }
            Address addressEntity = new Address();
            addressEntityMapper.map(addressDto, addressEntity);

            addressService.saveAddress(addressEntity);
        }
    }

    @Override
    public void updateCustomer(CustomerDto dto) {
        Customer entity = customerService.getCustomerByEmail(dto.getEmail()).orElse(null);

        if (entity != null) {
            Long id = entity.getId();
            entity =  modelMapper.map(dto, Customer.class);
            entity.setId(id);
            customerService.saveCustomer(entity);
        }
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return modelMapper.map(addressService.getAddressById(id).orElse(null), AddressDto.class);
    }

    @Override
    public List<PaymentMethodDto> getAllPaymentMethods() {
        return paymentService.getAllPaymentMethods().stream()
                .map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDto getPaymentMethodById(Long id) {
        return modelMapper.map(paymentService.getPaymentMethodById(id).orElse(new PaymentMethod()), PaymentMethodDto.class);
    }
}
