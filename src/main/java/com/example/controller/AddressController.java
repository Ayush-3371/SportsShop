package com.example.controller;

import com.example.dto.AddressDTO;
import com.example.entity.Address;
import com.example.service.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressServiceImpl addressService;

    public AddressController() {
    }

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    //POST
    @PostMapping("/add")
    AddressDTO addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    void deleteAddress(@PathVariable("id") long id) {
        addressService.deleteAddress(id);
    }

    //UPDATE
    @PutMapping("/update")
    AddressDTO updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    //GET
    @GetMapping("/get/{id}")
    AddressDTO getAddressById(@PathVariable("id") long id) {
        return addressService.getAddressById(id);
    }

    //GET ALL
    @GetMapping("/getAll")
    List<AddressDTO> getAllAddress() {
        return addressService.getAllAddress();
    }

}
