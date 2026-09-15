package com.example.service;

import com.example.dto.AddressDTO;
import com.example.entity.Address;
import java.util.List;

public interface AddressService {
    //ADD
    AddressDTO addAddress(AddressDTO addressDTO);

    //DELETE by id
    void deleteAddress(long id);

    //UPDATE
    AddressDTO updateAddress(Address address);

    //GET
    AddressDTO getAddressById(long id);

    //GET ALL
    List<AddressDTO> getAllAddress();
}
