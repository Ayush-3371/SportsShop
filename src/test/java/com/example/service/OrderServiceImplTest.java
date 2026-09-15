package com.example.service;

import com.example.dto.OrderDTO;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.OrderDetailsNotFoundException;
import com.example.modelMapper.OrderMapper;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepo;

    @Mock
    OrderMapper orderMapper;

    @Mock
    CustomerRepository customerRepo;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void addOrderSuccessTest() {
        LocalDate billingDate = LocalDate.of(2026, 9, 1);
        OrderDTO dto = new OrderDTO(1000, billingDate, "ayush21", "UPI");
        Order order = new Order(1000, billingDate);
        Customer customer = new Customer();
        customer.setUserId("ayush21");


        when(customerRepo.findCustomerByUserId("ayush21"))
                .thenReturn(customer);
        when(orderMapper.toEntity(dto))
                .thenReturn(order);

        ResponseEntity<?> response = orderService.addOrder(dto);

        Assertions.assertEquals(
                "Order Details added Successfully",
                response.getBody()
        );

        Assertions.assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        Assertions.assertEquals(customer.toString(),order.getCustomer().toString());

        verify(customerRepo).findCustomerByUserId("ayush21");
        verify(orderMapper).toEntity(dto);
        verify(orderRepo).save(order);
        verifyNoMoreInteractions(
                customerRepo,
                orderMapper,
                orderRepo
        );
    }

    @Test
    void addOrder_ShouldThrowException_WhenUserIsNotFound() {
        OrderDTO dto = new OrderDTO();
        dto.setCustomerUserId("ayushk21");

        when(customerRepo.findCustomerByUserId("ayushk21"))
                .thenReturn(null);

        CustomerNotFoundException exception = Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> orderService.addOrder(dto)
        );

        Assertions.assertEquals(
                "Could not find any customer with user id ayushk21",
                exception.getMessage()
        );

        verify(customerRepo).findCustomerByUserId("ayushk21");
        verify(orderMapper,never()).toEntity(any());
        verify(orderRepo, never()).save(any());
        verifyNoMoreInteractions(
                orderRepo,
                orderMapper,
                customerRepo
        );
    }

    @Test
    void deleteOrderSuccessTest() {
        Order order = new Order();
        order.setId(123L);

        when(orderRepo.findById(123L))
                .thenReturn(Optional.of(order));

        ResponseEntity<?> response = orderService.deleteOrder(123L);

        Assertions.assertEquals(
                "Order details of orderId 123 deleted successfully.",
                response.getBody()
        );

        Assertions.assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        verify(orderRepo).findById(123L);
        verify(orderRepo).deleteById(123L);
        verifyNoMoreInteractions(orderRepo);
    }

    @Test
    void deleteOrder_ShouldThrowException_WhenOrderDetailsIsNotFound() {
        when(orderRepo.findById(213L))
                .thenReturn(Optional.empty());

        OrderDetailsNotFoundException exception = Assertions.assertThrows(
                OrderDetailsNotFoundException.class,
                () -> orderService.deleteOrder(213L)
        );

        Assertions.assertEquals(
                "Cannot perform the delete operation as no order with id 213 was found.",
                exception.getMessage()
        );

        verify(orderRepo).findById(213L);
        verify(orderRepo, never()).deleteById(213L);
        verifyNoMoreInteractions(orderRepo);
    }

    @Test
    void updateOrderSuccessTest() {
        LocalDate billingDate = LocalDate.of(2026, 9, 1);
        //OrderDTO dto = new OrderDTO(1000, billingDate, "ayush21", "UPI");
        Order order1 = new Order(1000, billingDate);
        order1.setId(21L);
        LocalDate billingDate2 = LocalDate.of(2026, 9, 7);
        Order order2 = new Order(1200, billingDate2);
        order2.setId(21L);

        when(orderRepo.findById(21L))
                .thenReturn(Optional.of(order1));

        ResponseEntity<?> response = orderService.updateOrder(order2);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(
                "Order details of order with id 21 updated successfully.",
                response.getBody()
        );

        Assertions.assertEquals(
                HttpStatus.CREATED,
                response.getStatusCode()
        );

        verify(orderRepo).findById(21L);
        verify(orderRepo).save(order2);
        verifyNoMoreInteractions(orderRepo);
    }

    @Test
    void updateOrder_ShouldThrowException_WhenNoOrderDetailsAreFound() {
        Order order = new Order();
        order.setId(21L);

        when(orderRepo.findById(21L))
                .thenReturn(Optional.empty());

        OrderDetailsNotFoundException exception = Assertions.assertThrows(
                OrderDetailsNotFoundException.class,
                () -> orderService.updateOrder(order)
        );

        assertEquals(
                "Cannot perform the update operation as no order details were found with id 21",
                exception.getMessage()
        );

        verify(orderRepo).findById(21L);
        verifyNoMoreInteractions(orderRepo);
    }

    @Test
    void getOrderSuccessTest() {
        LocalDate billingDate = LocalDate.of(2026, 9, 1);
        OrderDTO dto = new OrderDTO(1000, billingDate, "ayush21", "UPI");
        Order order = new Order(1000, billingDate);
        order.setId(12L);

        when(orderRepo.findById(12L))
                .thenReturn(Optional.of(order));
        when(orderMapper.toDTO(order))
                .thenReturn(dto);

        OrderDTO result = orderService.getOrder(12L);

        assertNotNull(result);
        assertTrue(result.equals(dto));

        verify(orderRepo).findById(12L);
        verify(orderMapper).toDTO(order);
        verifyNoMoreInteractions(
                orderRepo,
                orderMapper
        );
    }

    @Test
    void getOrder_ShouldThrowException_WhenOrderDetailsIsNotFound() {
        when(orderRepo.findById(13L))
                .thenReturn(Optional.empty());

        OrderDetailsNotFoundException exception = assertThrows(
                OrderDetailsNotFoundException.class,
                () -> orderService.getOrder(13L)
        );

        assertEquals("Cannot find any order with id 13", exception.getMessage());

        verify(orderRepo).findById(13L);
        verify(orderMapper, never()).toDTO(any());
        verifyNoMoreInteractions(
                orderRepo,
                orderMapper
        );
    }

    @Test
    void getAllOrderSuccessTest() {
        Order order1 = new Order();
        order1.setId(21L);
        Order order2 = new Order();
        order2.setId(12L);
        OrderDTO dto1 = new OrderDTO();
        OrderDTO dto2 = new OrderDTO();

        when(orderRepo.findAll())
                .thenReturn(List.of(order1,order2));
        when(orderMapper.toDTO(order1))
                .thenReturn(dto1);
        when(orderMapper.toDTO(order2))
                .thenReturn(dto2);

        List<OrderDTO> result = orderService.getAllOrder();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(List.of(dto1,dto2), result);

        verify(orderRepo).findAll();
        verify(orderMapper, times(2)).toDTO(any(Order.class));
        verifyNoMoreInteractions(
                orderRepo,
                orderMapper
        );
    }

    @Test
    void getAllOrder_ShouldReturnAnEmptyList_WhenNoOrdersAreFound() {
        when(orderRepo.findAll())
                .thenReturn(Collections.emptyList());

        List<OrderDTO> result = orderService.getAllOrder();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(orderRepo).findAll();
        verify(orderMapper, never()).toDTO(any());
        verifyNoMoreInteractions(orderMapper,orderRepo);
    }

    @Test
    void getOrdersByCustomerIdSuccessTest() {
        Customer customer1 = new Customer();
        customer1.setUserId("ayush21");
        customer1.setPassword("ak99");

        Customer customer2 = new Customer();
        customer2.setUserId("ayushk21");
        customer2.setPassword("ak87");

        LocalDate billingDate = LocalDate.of(2026, 9, 7);
        Order order1 = new Order(1000, billingDate);
        order1.setCustomer(customer1);
        Order order2 = new Order(1200, billingDate);
        order2.setCustomer(customer1);
        Order order3 = new Order(1500, billingDate);
        order3.setCustomer(customer2);

        OrderDTO dto1 = new OrderDTO(1000, billingDate, "ayush21", "UPI");
        OrderDTO dto2 = new OrderDTO(1200, billingDate, "ayush21", "Credit Card");
        OrderDTO dto3 = new OrderDTO(1500, billingDate, "ayushk21", "Debit Card");

        when(customerRepo.findCustomerByUserId("ayush21"))
                .thenReturn(customer1);
        when(orderRepo.findOrderByCustomer_UserId("ayush21"))
                .thenReturn(List.of(order1, order2));
        when(orderMapper.toDTO(order1))
                .thenReturn(dto1);
        when(orderMapper.toDTO(order2))
                .thenReturn(dto2);

        List<OrderDTO> result = orderService.getOrdersByCustomerId("ayush21");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(List.of(dto1, dto2), result);

        verify(customerRepo).findCustomerByUserId("ayush21");
        verify(orderRepo).findOrderByCustomer_UserId("ayush21");
        verify(orderMapper, times(2)).toDTO(any(Order.class));
        verifyNoMoreInteractions(
                customerRepo,
                orderRepo,
                orderMapper
        );
    }

    @Test
    void getOrdersByCustomerId_ShouldThrowException_WhenCustomerIsNotFound() {
        when(customerRepo.findCustomerByUserId("ayush21"))
                .thenReturn(null);

        CustomerNotFoundException exception = assertThrows(
                CustomerNotFoundException.class,
                () -> orderService.getOrdersByCustomerId("ayush21")
        );

        assertEquals("Cannot find any user with user id ayush21", exception.getMessage());

        verify(customerRepo).findCustomerByUserId("ayush21");
        verifyNoMoreInteractions(
                customerRepo,
                orderRepo,
                orderMapper
        );
    }

    @Test
    void getOrdersByCustomerId_ShouldReturnEmptyList_WhenNoOrderBelongsToTheCustomer() {
        Customer customer = new Customer();
        customer.setUserId("ayush21");
        customer.setPassword("ak99");

        when(customerRepo.findCustomerByUserId("ayush21"))
                .thenReturn(customer);
        when(orderRepo.findOrderByCustomer_UserId("ayush21"))
                .thenReturn(Collections.emptyList());

        List<OrderDTO> result = orderService.getOrdersByCustomerId("ayush21");

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(customerRepo).findCustomerByUserId("ayush21");
        verify(orderRepo).findOrderByCustomer_UserId("ayush21");
        verify(orderMapper, never()).toDTO(any(Order.class));
        verifyNoMoreInteractions(
                customerRepo,
                orderMapper,
                orderRepo
        );
    }
}