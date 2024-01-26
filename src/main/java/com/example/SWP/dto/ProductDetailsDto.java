package com.example.SWP.dto;

import com.example.SWP.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDetailsDto {
    private Product product;
    private int quantity;

}
