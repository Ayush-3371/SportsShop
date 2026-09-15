package com.example.dto;

import com.example.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {

    @NotNull
    private double amount;

    @PastOrPresent(message = "Billing date cannot be a future date.")
    private LocalDate billingDate;

    @NotBlank
    private String customerUserId;

    @NotBlank
    private String paymentType;


    //No arg Constructor
    public OrderDTO() {
    }

    public OrderDTO(double amount, LocalDate billingDate, String customerUserId, String paymentType) {
        this.amount = amount;
        this.billingDate = billingDate;
        this.customerUserId = customerUserId;
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO dto = (OrderDTO) o;
        return Double.compare(amount, dto.amount) == 0 && Objects.equals(billingDate, dto.billingDate) && Objects.equals(customerUserId, dto.customerUserId) && Objects.equals(paymentType, dto.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, billingDate, customerUserId, paymentType);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "amount=" + amount +
                ", billingDate=" + billingDate +
                ", customerUserId='" + customerUserId + '\'' +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
