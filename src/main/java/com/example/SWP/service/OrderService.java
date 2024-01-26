package com.example.SWP.service;

import com.example.SWP.dto.DisplayOrderDto;
import com.example.SWP.dto.OrderFormDto;
import com.example.SWP.dto.ProductDetailsDto;
import com.example.SWP.model.Order;
import com.example.SWP.model.OrderProduct;
import com.example.SWP.model.Product;
import com.example.SWP.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderProductService orderProductService;
    public OrderService(OrderRepository orderRepository, ProductService productService, OrderProductService orderProductService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    public void addOrder(OrderFormDto orderForm) {
        Order order = getOrder(orderForm);
        List<OrderProduct> orderProducts = getOrderProducts(orderForm, order);
        for (OrderProduct orderProduct : orderProducts) {
            orderProductService.addOrderedProduct(orderProduct);
        }
        orderRepository.save(order);
    }

    private static Order getOrder(OrderFormDto orderForm) {
        return Order.builder()
                .firstName(orderForm.getFirstName())
                .lastName(orderForm.getLastName())
                .phoneNumber(orderForm.getPhoneNumber())
                .address(orderForm.getAddress())
                .postCode(orderForm.getPostCode())
                .city(orderForm.getCity())
                .paymentMethod(orderForm.getPaymentMethod())
                .status(orderForm.getStatus())
                .waybillNumber(orderForm.getWaybillNumber())
                .build();
    }

    private List<OrderProduct> getOrderProducts(OrderFormDto orderForm, Order order) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        int index = 0;
        List<Integer> quantities = orderForm.getQuantities();
        for (Long productId : orderForm.getSelectedProducts()) {
            Product product = productService.getProductById(productId);
            int quantity = (index >= 0 && index < quantities.size()) ? quantities.get(index) : 0;
            OrderProduct orderProduct = new OrderProduct(order, product, quantity);
            orderProducts.add(orderProduct);
            index++;
        }
        return orderProducts;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<DisplayOrderDto> ordersToDisplay() {
        List<DisplayOrderDto> displayOrderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        List<OrderProduct> orderProducts = orderProductService.getAllOrderProducts();

        for(Order order : orders) {
            List<ProductDetailsDto> detailsOfProducts = new ArrayList<>();
            for(OrderProduct orderProduct : orderProducts) {
                if(Objects.equals(order.getId(), orderProduct.getOrder().getId())) {
                    Product product = productService.getProductById(orderProduct.getProduct().getId());
                    int quantity = orderProduct.getQuantity();

                    detailsOfProducts.add(new ProductDetailsDto(product, quantity));
                }
            }

            DisplayOrderDto displayOrderDto = DisplayOrderDto.builder()
                    .order(order)
                    .productDetails(detailsOfProducts)
                    .build();

            displayOrderDtos.add(displayOrderDto);

        }
        return displayOrderDtos;
    }

    @Transactional
    public void editOrderStatus(Long orderId, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(newStatus);
            orderRepository.save(order);
        }
    }
}
