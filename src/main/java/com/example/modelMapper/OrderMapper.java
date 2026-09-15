package com.example.modelMapper;

import com.example.dto.OrderDTO;
import com.example.entity.Order;
import com.example.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setBillingDate(order.getBillingDate());
        dto.setCustomerUserId(order.getCustomer().getUserId());
        dto.setPaymentType(order.getPaymentType().getType());
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setBillingDate(dto.getBillingDate());
        order.setAmount(dto.getAmount());
        Payment payment = new Payment();
        payment.setType(dto.getPaymentType());
        payment.setStatus("Successful");
        order.setPaymentType(payment);
        return order;
    }
}
