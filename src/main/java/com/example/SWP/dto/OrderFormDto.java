package com.example.SWP.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderFormDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String postCode;
    private String city;
    private String paymentMethod;
    private String status;
    private String waybillNumber;
    private List<Long> selectedProducts;
    private List<Integer> quantities;
}