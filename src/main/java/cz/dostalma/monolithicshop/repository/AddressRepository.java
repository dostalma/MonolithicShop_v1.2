package cz.dostalma.monolithicshop.repository;

import cz.dostalma.monolithicshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByCustomerId(Long customerId);

    Optional<Address> findByStreetAndHouseNumberAndCityAndCountryAndZipCodeAndCustomerId(
            String street, String houseNumber, String city, String country, String zipCode, Long customerId
    );
}
