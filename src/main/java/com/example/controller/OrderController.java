package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.entity.Order;
import com.example.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    public OrderController() {
    }

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addOrder(@RequestBody OrderDTO order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @GetMapping("/get/{id}")
    OrderDTO getOrder(@PathVariable("id") long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/getAllOrder")
    List<OrderDTO> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/getOrderByUserId/{customer_UserId}")
    List<OrderDTO> getOrderByCustomerUserId(@PathVariable("customer_UserId") String userId) {
        return orderService.getOrdersByCustomerId(userId);
    }
}
