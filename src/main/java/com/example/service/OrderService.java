package com.example.service;

import com.example.dto.OrderDTO;
import com.example.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    //ADD
    ResponseEntity addOrder(OrderDTO order);

    //DELETE
    ResponseEntity deleteOrder(long id);

    //UPDATE
    ResponseEntity updateOrder(Order order);

    //GET
    OrderDTO getOrder(long id);

    //GET ALL
    List<OrderDTO> getAllOrder();

    //GET ALL Orders by a Customer id
    List<OrderDTO> getOrdersByCustomerId(String customerUserId);
}
