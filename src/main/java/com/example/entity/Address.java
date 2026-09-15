package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String doorNo;

    @NotBlank(message = "Enter your street details")
    private String street;

    @NotBlank(message = "Enter relevant details or nearest landmark.")
    private String area;

    @NotBlank(message = "Please enter the name of the city where you live.")
    private String city;

    @NotBlank(message = "Please enter the name of the state where you live.")
    private String state;

    @NotBlank(message = "Please enter a valid 6 digit Pin Code")
    private long pinCode;

    public Address() {
    }

    public Address(String doorNo, String street, String area, String city, String state, long pinCode) {
        this.doorNo = doorNo;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }
}
