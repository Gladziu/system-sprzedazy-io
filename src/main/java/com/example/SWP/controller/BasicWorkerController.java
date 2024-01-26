package com.example.SWP.controller;

import com.example.SWP.dto.DisplayOrderDto;
import com.example.SWP.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BasicWorkerController {
    private final OrderService orderService;

    public BasicWorkerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/view-orders")
    public String viewOrders(Model model) {
        List<DisplayOrderDto> displayOrders = orderService.ordersToDisplay();
        model.addAttribute("displayOrders", displayOrders);
        return "view-orders";
    }

    @PostMapping("/edit-order-status/{orderId}")
    public String editOrderStatus(@PathVariable Long orderId, @RequestParam String newStatus) {
        orderService.editOrderStatus(orderId, newStatus);
        return "redirect:/view-orders";  // Przekierowanie na stronę z listą zamówień
    }
}
