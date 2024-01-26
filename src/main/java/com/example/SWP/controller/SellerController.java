package com.example.SWP.controller;

import com.example.SWP.dto.DisplayOrderDto;
import com.example.SWP.dto.OrderFormDto;
import com.example.SWP.model.Product;
import com.example.SWP.service.OrderService;
import com.example.SWP.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final ProductService productService;
    private final OrderService orderService;

    public SellerController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/add-product")
    public String showProductForm() {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/seller/add-product";
    }

    @GetMapping("/create-order")
    public String showOrderForm(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "placing-order";
    }

    @PostMapping("/create-order")
    public String addOrder(@ModelAttribute OrderFormDto orderForm) {
        orderService.addOrder(orderForm);
        return "redirect:/seller/create-order";
    }

    @GetMapping("/view-orders")
    public String viewOrders(Model model) {
        List<DisplayOrderDto> displayOrders = orderService.ordersToDisplay();
        model.addAttribute("displayOrders", displayOrders);
        return "view-orders-seller";
    }

    @PostMapping("/edit-order-status/{orderId}")
    public String editOrderStatus(@PathVariable Long orderId, @RequestParam String newStatus) {
        orderService.editOrderStatus(orderId, newStatus);
        return "redirect:/seller/view-orders";
    }
}
