package com.example.SWP.initializer;

import com.example.SWP.model.Order;
import com.example.SWP.model.OrderProduct;
import com.example.SWP.model.Product;
import com.example.SWP.repository.OrderProductRepository;
import com.example.SWP.repository.OrderRepository;
import com.example.SWP.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    @Autowired
    public DataInitializer(ProductRepository productRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product sampleProduct = new Product();
        sampleProduct.setName("Sikawka 3000 EXTREME");
        sampleProduct.setPrice(BigDecimal.valueOf(800.99));
        productRepository.save(sampleProduct);

        Product sampleProduct2 = new Product();
        sampleProduct2.setName("Zraszacz 1500 SUPER-FAST");
        sampleProduct2.setPrice(BigDecimal.valueOf(460.49));
        productRepository.save(sampleProduct2);

        Order order = Order.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .phoneNumber("123456789")
                .address("Sezamkowa 12")
                .postCode("10-101")
                .city("Warszawa")
                .paymentMethod("PŁATNOŚĆ Z GÓRY")
                .status("W REALIZACJI")
                .waybillNumber("31345602")
                .build();
        orderRepository.save(order);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(sampleProduct);
        orderProduct.setOrder(order);
        orderProduct.setQuantity(3);
        orderProductRepository.save(orderProduct);
    }

}
