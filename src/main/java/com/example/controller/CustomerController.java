package com.example.controller;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exceptions.UserNotFoundException;
import com.example.service.CustomerService;
import com.example.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    public CustomerController() {
    }

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    CustomerDTO addCustomer(@Valid @RequestBody Customer customer) {
        return this.customerService.addCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    void deleteCustomerById(@PathVariable("id") @Positive(message = "Id should be greater than 0") long id) throws UserNotFoundException {
        customerService.deleteCustomerById(id);
    }

    @PutMapping("/update")
    CustomerDTO updateCustomer(@Valid @RequestBody Customer customer) throws UserNotFoundException {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/getCustomerById/{id}")
    CustomerDTO getCustomer(@PathVariable("id") @Positive(message = "Id should be greater than 0") long id) throws UserNotFoundException {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getAll")
    List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }
}
