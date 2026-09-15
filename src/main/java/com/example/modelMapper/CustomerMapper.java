package com.example.modelMapper;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    AddressMapper addressMapper;

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddressDTO(addressMapper.toDTO(customer.getAddress()));
        customerDTO.setDob(customer.getDob());
        customerDTO.setContactNo(customer.getContactNo());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setUserId(customer.getUserId());

        return customerDTO;
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setUserId(dto.getUserId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setContactNo(dto.getContactNo());
        customer.setDob(dto.getDob());
        customer.setAddress(addressMapper.toEntity(dto.getAddressDTO()));

        return customer;
    }
}
