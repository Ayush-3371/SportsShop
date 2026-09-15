package com.example.service;

import com.example.dto.AddressDTO;
import com.example.entity.Address;
import com.example.exceptions.AddressNotFoundException;
import com.example.modelMapper.AddressMapper;
import com.example.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

   @Mock
   private AddressRepository addressRepo;

   @Mock
   private AddressMapper addressMapper;

   @InjectMocks
   private AddressServiceImpl addressService;

   @Test
    void addAddressSuccessTest() {
       Address address = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
       AddressDTO addressDTO = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);

       when(addressMapper.toEntity(addressDTO))
               .thenReturn(address);
       when(addressRepo.save(address))
               .thenReturn(address);
       when(addressMapper.toDTO(address))
               .thenReturn(addressDTO);

       AddressDTO result = addressService.addAddress(addressDTO);

       assertNotNull(result);
       assertEquals(result.toString(), addressDTO.toString());

       verify(addressMapper).toDTO(address);
       verify(addressMapper).toEntity(addressDTO);
       verify(addressRepo).save(address);
       verifyNoMoreInteractions(addressRepo,addressMapper);
   }

   @Test
    void deleteAddressSuccessTest() throws AddressNotFoundException {
       Address address = new Address();
       address.setId(123L);

       when(addressRepo.findById(123L))
               .thenReturn(Optional.of(address));

       addressService.deleteAddress(123L);

       verify(addressRepo).findById(123L);
       verify(addressRepo).deleteById(123L);
       verifyNoMoreInteractions(addressRepo);
   }

   @Test
    void deleteAddress_ShouldThrowException_WhenAddressIsNotFound() {

       when(addressRepo.findById(1242L))
               .thenReturn(Optional.empty());

       AddressNotFoundException exception = Assertions.assertThrows(
               AddressNotFoundException.class,
               () -> addressService.deleteAddress(1242L)
       );

       Assertions.assertEquals(
               "Cannot find any address with id 1242",
               exception.getMessage()
       );

       verify(addressRepo).findById(1242L);
       verify(addressRepo, never()).deleteById(1242L);
       verifyNoMoreInteractions(addressRepo);
   }

   @Test
    void updateAddressSuccessTest() throws AddressNotFoundException {
       Address address1 = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
       address1.setId(23L);
       //AddressDTO dto1 = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
       Address address2 = new Address("508", "Jasmine Colive PG, Road No. 33 ", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
       address2.setId(23L);
       AddressDTO dto2 = new AddressDTO("508", "Jasmine Colive PG, Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);

       when(addressRepo.findById(23L))
               .thenReturn(Optional.of(address1));
       when(addressRepo.save(address2))
               .thenReturn(address2);
       when(addressMapper.toDTO(address2))
               .thenReturn(dto2);

       AddressDTO result = addressService.updateAddress(address2);

       Assertions.assertNotNull(result);
       Assertions.assertEquals(result.toString(), dto2.toString());

       verify(addressRepo).findById(23L);
       verify(addressRepo).save(address2);
       verify(addressMapper).toDTO(address2);
       verifyNoMoreInteractions(addressRepo,addressMapper);
   }

   @Test
    void updateAddress_ShouldThrowException_WhenAddressIsNotFound() {
       Address address = new Address();
       address.setId(1234L);

       when(addressRepo.findById(1234L))
               .thenReturn(Optional.empty());

       AddressNotFoundException exception = Assertions.assertThrows(
               AddressNotFoundException.class,
               () -> addressService.updateAddress(address)
       );

       Assertions.assertEquals(
               "Cannot update the address because no such address with id 1234 exists.",
               exception.getMessage()
       );

       verify(addressMapper, never()).toDTO(any());
       verify(addressRepo).findById(1234L);
       verifyNoMoreInteractions(addressRepo, addressMapper);
   }

   @Test
   void getAddressByIdSuccessTest() throws AddressNotFoundException {
      Address address = new Address("508", "Jasmine Colive PG, Road No. 33 ", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
      address.setId(23L);
      AddressDTO dto = new AddressDTO("508", "Jasmine Colive PG, Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);

      when(addressRepo.findById(23L))
              .thenReturn(Optional.of(address));
      when(addressMapper.toDTO(address))
              .thenReturn(dto);

      AddressDTO result = addressService.getAddressById(23L);

      Assertions.assertNotNull(result);
      Assertions.assertEquals(result.toString(), dto.toString());

      verify(addressRepo).findById(23L);
      verify(addressMapper).toDTO(address);
      verifyNoMoreInteractions(addressRepo, addressMapper);
   }

   @Test
   void getAddressById_ShouldThrowException_WhenAddressIsNotFound() {
      when(addressRepo.findById(432L))
              .thenReturn(Optional.empty());

      AddressNotFoundException exception = Assertions.assertThrows(
              AddressNotFoundException.class,
              () -> addressService.getAddressById(432L)
      );

      Assertions.assertEquals(
              "Cannot find any address with id 432",
              exception.getMessage()
      );

      verify(addressMapper, never()).toDTO(any());
      verify(addressRepo).findById(432L);
      verifyNoMoreInteractions(addressRepo,addressMapper);
   }

   @Test
   void getAllAddressSuccessTest() {
      Address address1 = new Address("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
      AddressDTO dto1 = new AddressDTO("408", "Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
      Address address2 = new Address("508", "Jasmine Colive PG, Road No. 33 ", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);
      AddressDTO dto2 = new AddressDTO("508", "Jasmine Colive PG, Road No. 33", "Ayyappa Society", "Hyderabad", "Telangana", 500081L);


      when(addressRepo.findAll())
              .thenReturn(List.of(address1,address2));
      when(addressMapper.toDTO(address1))
              .thenReturn(dto1);
      when(addressMapper.toDTO(address2))
              .thenReturn(dto2);

      List<AddressDTO> result = addressService.getAllAddress();

      Assertions.assertNotNull(result);
      Assertions.assertEquals(2, result.size());
      Assertions.assertEquals(result, List.of(dto1,dto2));

      verify(addressRepo).findAll();
      verify(addressMapper).toDTO(address1);
      verify(addressMapper).toDTO(address2);
      verifyNoMoreInteractions(addressRepo,addressMapper);
   }

   @Test
   void getAllAddress_ShouldReturnEmptyList_WhenNoAddressExists() {
      when(addressRepo.findAll())
              .thenReturn(Collections.emptyList());

      List<AddressDTO> result = addressService.getAllAddress();

      Assertions.assertNotNull(result);
      Assertions.assertTrue(result.isEmpty());

      verify(addressMapper, never()).toDTO(any());
      verify(addressRepo).findAll();
      verifyNoMoreInteractions(addressRepo,addressMapper);
   }
}