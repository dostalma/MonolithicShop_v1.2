package cz.dostalma.monolithicshop.service;

import cz.dostalma.monolithicshop.dto.AddressDto;
import cz.dostalma.monolithicshop.model.Address;
import cz.dostalma.monolithicshop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAddressesByCustomerId(Long id) {
        return addressRepository.findAllByCustomerId(id);
    }

    @Override
    public Optional<Address> getAddressByDtoFields(AddressDto dto) {
        return addressRepository.findByStreetAndHouseNumberAndCityAndCountryAndZipCodeAndCustomerId(
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getCity(),
                dto.getCountry(),
                dto.getZipCode(),
                dto.getCustomerId()
        );
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.saveAndFlush(address);
    }
}
