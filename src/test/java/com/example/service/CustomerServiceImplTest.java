package com.example.service;

import com.example.dto.AddressDTO;
import com.example.dto.CustomerDTO;
import com.example.entity.Address;
import com.example.exceptions.UserNotFoundException;
import com.example.modelMapper.AddressMapper;
import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.entity.Customer;
import com.example.modelMapper.CustomerMapper;
import org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Before All....");
    }

    @AfterAll
    static void setUpAfterAll() {
        System.out.println("After All....");
    }

    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("Before Each....");
    }

    @AfterEach
    void setUpAfterEach() {
        System.out.println("After Each....");
    }

    @Test
    void addCustomerSuccessTest() {

        LocalDate dob = LocalDate.of(2003, 06, 21);
        Address address = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        AddressDTO addressDTO = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        Customer customer = new Customer("ayush21", "ak99", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, address);
        CustomerDTO customerDTO = new CustomerDTO("ayush21", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, addressDTO);

        when(customerRepo.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO outputDto = customerService.addCustomer(customer);

        Assertions.assertNotNull(outputDto);
        Assertions.assertEquals(outputDto.toString(), customerDTO.toString());

        verify(customerRepo).save(customer);
        verify(customerMapper).toDTO(customer);
        verifyNoMoreInteractions(customerRepo,customerMapper);
    }

    @Test
    void deleteCustomerByIdSuccessTest() throws UserNotFoundException {

        Customer customer = new Customer();
        customer.setId(1L);

        when(customerRepo.findById(1L))
                .thenReturn(Optional.of(customer));

        customerService.deleteCustomerById(1L);

        verify(customerRepo).findById(1L);
        verify(customerRepo).deleteById(1L);
    }

    @Test
    void deleteCustomerById_ShouldThrowException_WhenUserIsNotFound() {

        when(customerRepo.findById(10000L)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(UserNotFoundException.class, () -> customerService.deleteCustomerById(10000L));

        String expectedMessage = "Cannot find any customer with id 10000";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);

        verify(customerRepo).findById(10000L);
        verify(customerRepo, never()).deleteById(anyLong());
    }

    @Test
    void updateCustomerSuccessTest() throws UserNotFoundException {

        LocalDate dob = LocalDate.of(2003, 06, 21);
        Address address = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        AddressDTO addressDTO = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        Customer currentCustomer = new Customer("ayush21", "ak99", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, address);
        currentCustomer.setId(1L);
        Customer newCustomer = new Customer("ayushk21", "ak99", "Ayush Kumar", "ayush@mphasis.com", "9999911111", dob, address);
        newCustomer.setId(1L);
        CustomerDTO newCustomerDTO = new CustomerDTO("ayushk21", "Ayush Kumar", "ayush@mpahsis.com", "9999911111", dob, addressDTO);

        when(customerRepo.findById(1L))
                .thenReturn(Optional.of(currentCustomer));
        when(customerRepo.save(newCustomer))
                .thenReturn(newCustomer);
        when(customerMapper.toDTO(newCustomer))
                .thenReturn(newCustomerDTO);

        CustomerDTO result = customerService.updateCustomer(newCustomer);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.toString(), newCustomerDTO.toString());

        verify(customerRepo).findById(1L);
        verify(customerRepo).save(newCustomer);
        verify(customerMapper).toDTO(newCustomer);
    }

    @Test
    void updateCustomer_ShouldThrowException_WhenUserDoesNotExist() {
        Customer customer = new Customer();
        customer.setId(10L);

        when(customerRepo.findById(10L))
                .thenReturn(Optional.empty());

        UserNotFoundException exception = Assertions.assertThrows(
                UserNotFoundException.class,
                () -> customerService.updateCustomer(customer));

        Assertions.assertEquals(
                "Cannot find any customer with id 10",
                exception.getMessage());

        verify(customerRepo).findById(10L);
        verify(customerRepo, never()).save(any());
        verify(customerMapper, never()).toDTO(any());
    }

    @Test
    void getCustomerByIdSuccessTest() {
        LocalDate dob = LocalDate.of(2003, 06, 21);
        Address address = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        AddressDTO addressDTO = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        Customer customer = new Customer("ayush21", "ak99", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, address);
        customer.setId(1L);
        CustomerDTO customerDTO = new CustomerDTO("ayush21", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, addressDTO);

        when(customerRepo.findById(1L))
                .thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer))
                .thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.toString(), customerDTO.toString());

        verify(customerRepo).findById(1L);
        verify(customerMapper).toDTO(customer);
    }

    @Test
    void getCustomerById_ThrowsException_WhenUserDoesNotExist() {
        Customer customer = new Customer();
        customer.setId(20L);

        when(customerRepo.findById(20L))
                .thenReturn(Optional.empty());

        UserNotFoundException exception = Assertions.assertThrows(
                UserNotFoundException.class,
                () -> customerService.getCustomerById(customer.getId())
        );

        Assertions.assertEquals(
                "Cannot find any customer with id 20",
                exception.getMessage()
        );

        verify(customerRepo).findById(20L);
        verify(customerMapper, never()).toDTO(any());
    }

    @Test
    void getAllCustomerSuccessTest() {
        LocalDate dob = LocalDate.of(2003, 06, 21);
        Address address = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        AddressDTO addressDTO = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
        Customer customer1 = new Customer("ayush21", "ak99", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, address);
        Customer customer2 = new Customer("ayushk21", "ak77", "Ayush Kumar", "ayush@mphasis.com", "9999922222", dob, address);

        CustomerDTO dto1 = new CustomerDTO("ayush21", "Ayush Kumar", "ayush@gmail.com", "9999922222", dob, addressDTO);
        CustomerDTO dto2 = new CustomerDTO("ayushk21", "Ayush Kumar", "ayush@mphasis.com", "9999922222", dob, addressDTO);

        when(customerRepo.findAll())
                .thenReturn(List.of(customer1, customer2));
        when(customerMapper.toDTO(customer1))
                .thenReturn(dto1);
        when(customerMapper.toDTO(customer2))
                .thenReturn(dto2);

        List<CustomerDTO> result = customerService.getAllCustomer();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(List.of(dto1,dto2), result);

        verify(customerRepo).findAll();
        verify(customerMapper).toDTO(customer1);
        verify(customerMapper).toDTO(customer2);
        verifyNoMoreInteractions(customerRepo,customerMapper);
    }

    @Test
    void getAllCustomer_ReturnsEmptyListWhenNoCustomerExists() {
        when(customerRepo.findAll())
                .thenReturn(Collections.emptyList());

        List<CustomerDTO> result = customerService.getAllCustomer();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());

        verify(customerRepo).findAll();
        verify(customerMapper, never()).toDTO(any());
        verifyNoMoreInteractions(customerRepo,customerMapper);

    }
}
