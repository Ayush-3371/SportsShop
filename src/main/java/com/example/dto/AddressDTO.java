package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {
    @NotBlank
    private String doorNo;

    @NotBlank
    private String street;

    @NotBlank
    private String area;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotNull
    private Long pinCode;

    //No-arg constructor
    public AddressDTO() {
    }

    public AddressDTO(String doorNo, String street, String area, String city, String state, Long pinCode) {
        this.doorNo = doorNo;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    //Getters & Setters
    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "doorNo='" + doorNo + '\'' +
                ", street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }
}
