package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.dto.OrderDTO;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.exceptions.UserNotFoundException;
import com.example.modelMapper.CustomerMapper;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public CustomerDTO addCustomer(Customer customer) {
        Customer addedCustomer = customerRepo.save(customer);
        return customerMapper.toDTO(addedCustomer);
    }

    @Override
    public void deleteCustomerById(long id) throws UserNotFoundException {
        Optional<Customer> optObj = customerRepo.findById(id);
        if(optObj.isPresent()){
            customerRepo.deleteById(id);
            System.out.println("Customer with id " + id + " deleted.");
            return;
        }
        throw new UserNotFoundException("Cannot find any customer with id " + id);
    }

    @Override
    public CustomerDTO updateCustomer(Customer customer) throws UserNotFoundException {
        Optional<Customer> optObj = customerRepo.findById(customer.getId());
        if(optObj.isEmpty()){
            throw new UserNotFoundException("Cannot find any customer with id " + customer.getId());
        }

        Customer savedCustomer = customerRepo.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(long id) throws UserNotFoundException {
        Optional<Customer> optObj = customerRepo.findById(id);
        if(optObj.isPresent()){
            return customerMapper.toDTO(optObj.get());
        }
        throw new UserNotFoundException("Cannot find any customer with id " + id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepo.findAll()
                .stream()
                .map(customer -> customerMapper.toDTO(customer))
                .toList();
    }


}
