package cz.dostalma.monolithicshop.service;


import cz.dostalma.monolithicshop.dto.AddressDto;
import cz.dostalma.monolithicshop.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> getAllAddresses();

    List<Address> getAddressesByCustomerId(Long id);

    Optional<Address> getAddressByDtoFields(AddressDto dto);

    void saveAddress(Address address);
}
