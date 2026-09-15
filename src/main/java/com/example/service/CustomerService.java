package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.dto.OrderDTO;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.exceptions.UserNotFoundException;

import java.util.List;

public interface CustomerService {
    //ADD
    CustomerDTO addCustomer(Customer customer);

    //DELETE
    void deleteCustomerById(long id) throws UserNotFoundException;

    //UPDATE
    CustomerDTO updateCustomer(Customer customer) throws UserNotFoundException;

    //GET by Id
    CustomerDTO getCustomerById(long id) throws UserNotFoundException;

    //GET all
    List<CustomerDTO> getAllCustomer();


}
