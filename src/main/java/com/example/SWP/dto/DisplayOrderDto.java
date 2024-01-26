package com.example.SWP.dto;

import com.example.SWP.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class DisplayOrderDto {
    private Order order;
    private List<ProductDetailsDto> productDetails;
}
