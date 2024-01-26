package com.example.SWP.service;

import com.example.SWP.model.OrderProduct;
import com.example.SWP.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public void addOrderedProduct(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }
}
