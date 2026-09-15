package com.example.service;

import com.example.dto.AddressDTO;
import com.example.entity.Address;
import com.example.exceptions.AddressNotFoundException;
import com.example.modelMapper.AddressMapper;
import com.example.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepo;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO) {

        Address address = addressMapper.toEntity(addressDTO);
        Address saveAddress = addressRepo.save(address);

        return addressMapper.toDTO(saveAddress);
    }

    @Override
    public void deleteAddress(long id) {
        Optional<Address> optObj = addressRepo.findById(id);
        if(optObj.isPresent()) {
            addressRepo.deleteById(id);
            System.out.println("Address with id " + id + " deleted.");
            return;
        }

        throw new AddressNotFoundException("Cannot find any address with id " + id);
    }

    @Override
    public AddressDTO updateAddress(Address address) {
        Optional<Address> optObj = addressRepo.findById(address.getId());
        if(optObj.isEmpty()) {
            throw new AddressNotFoundException("Cannot update the address because no such address with id " + address.getId() + " exists.");
        }

        Address savedAddress = addressRepo.save(address);

        return addressMapper.toDTO(savedAddress);
    }

    @Override
    public AddressDTO getAddressById(long id) {
        Optional<Address> optObj = addressRepo.findById(id);
        if(optObj.isPresent()) {
            return addressMapper.toDTO(optObj.get());
        }

        throw new AddressNotFoundException("Cannot find any address with id " + id);
    }

    @Override
    public List<AddressDTO> getAllAddress() {
        return addressRepo.findAll()
                .stream()
                .map(address -> addressMapper.toDTO(address))
                .toList();
    }
}
