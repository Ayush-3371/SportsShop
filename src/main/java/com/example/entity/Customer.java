package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends User {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 10, message = "Contact number should be of 10 digits only")
    private String contactNo;

    @Past
    private LocalDate dob;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orderList;


    public Customer() {
    }

//    public Customer(String userId, String password, String name, String email, String contactNo, LocalDate dob) {
//        super(userId, password);
//        this.name = name;
//        this.email = email;
//        this.contactNo = contactNo;
//        this.dob = dob;
//    }

    public Customer(String userId, String password, String name, String email, String contactNo, LocalDate dob, Address address) {
        super(userId, password);
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dob = dob;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                '}';
    }
}
