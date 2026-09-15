package com.example.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CustomerDTO {
    @NotBlank
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Please enter a valid email.")
    private String email;

    @NotBlank
    @Size(min = 10, max = 10, message = "Contact Number should be a 10 digit number.")
    private String contactNo;

    @Past
    private LocalDate dob;

    @NotNull
    private AddressDTO addressDTO;

    //No-arg Constructor
    public CustomerDTO() {
    }

    public CustomerDTO(String userId, String name, String email, String contactNo, LocalDate dob, AddressDTO addressDTO) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dob = dob;
        this.addressDTO = addressDTO;
    }

    //Getters & Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", addressDTO=" + addressDTO +
                '}';
    }
}
