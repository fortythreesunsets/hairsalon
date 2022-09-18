package com.fortythreesunsets.hairsalon.service.impl;

import com.fortythreesunsets.hairsalon.entities.Address;
import com.fortythreesunsets.hairsalon.repositories.AddressRepository;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import com.fortythreesunsets.hairsalon.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AppointmentService appointmentService;

    public AddressServiceImpl(AddressRepository addressRepository, AppointmentService appointmentService) {
        this.addressRepository = addressRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public Optional<Address> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() { return addressRepository.findAll(); }

    @Override
    public Address save(Address address) {
        if (address ==  null)
            throw new IllegalArgumentException("Dirección incorrecta. No se ha creado la dirección");

        return addressRepository.save(address);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !addressRepository.existsById(id))
            return false;

        addressRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        addressRepository.deleteAll();
        return true;
    }
}
