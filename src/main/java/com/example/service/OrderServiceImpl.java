package com.example.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.dto.CustomerDTO;
import com.example.dto.OrderDTO;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.OrderDetailsNotFoundException;
import com.example.modelMapper.OrderMapper;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CustomerRepository customerRepo;

    @Override
    public ResponseEntity<?> addOrder(OrderDTO order) {
        Customer customer = customerRepo.findCustomerByUserId(order.getCustomerUserId());
        if(customer == null) {
            throw new CustomerNotFoundException("Could not find any customer with user id "+ order.getCustomerUserId());
        }
        Order newOrder = orderMapper.toEntity(order);
        newOrder.setCustomer(customer);
        orderRepo.save(newOrder);
        return new ResponseEntity<>("Order Details added Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteOrder(long id) {
        Optional<Order> optObj = orderRepo.findById(id);
        if(optObj.isPresent()) {
            orderRepo.deleteById(id);
            return new ResponseEntity<>("Order details of orderId "+ id + " deleted successfully.", HttpStatus.CREATED);
        }

        throw new OrderDetailsNotFoundException("Cannot perform the delete operation as no order with id " + id + " was found.");
    }

    @Override
    public ResponseEntity<?> updateOrder(Order order) {
        Optional<Order> optObj = orderRepo.findById(order.getId());
        //Customer customer = order.getCustomer();

        if(optObj.isPresent()) {
            orderRepo.save(order);
            return new ResponseEntity<>("Order details of order with id " + order.getId() + " updated successfully.", HttpStatus.CREATED);
        }
        throw new OrderDetailsNotFoundException("Cannot perform the update operation as no order details were found with id "+ order.getId());
    }

    @Override
    public OrderDTO getOrder(long id) {
        Optional<Order> optObj = orderRepo.findById(id);
        if(optObj.isPresent()) {
            return orderMapper.toDTO(optObj.get());
        }

        throw new OrderDetailsNotFoundException("Cannot find any order with id " + id);
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return orderRepo.findAll()
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(String customerUserId) {
        Customer customer = customerRepo.findCustomerByUserId(customerUserId);
        if(customer == null) {
            throw new CustomerNotFoundException("Cannot find any user with user id "+ customerUserId);
        }

        return orderRepo.findOrderByCustomer_UserId(customerUserId)
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .toList();
    }


}