package com.example.modelMapper;

import com.example.dto.AddressDTO;
import com.example.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setDoorNo(address.getDoorNo());
        dto.setStreet(address.getStreet());
        dto.setArea(address.getArea());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPinCode(address.getPinCode());

        return dto;
    }

    public Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setDoorNo(dto.getDoorNo());
        address.setStreet(dto.getStreet());
        address.setArea(dto.getArea());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPinCode(dto.getPinCode());

        return address;
    }
}
