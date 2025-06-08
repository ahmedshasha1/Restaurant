package com.resturant.controller;

import com.resturant.controller.vm.OrderDetailsVM;
import com.resturant.dto.OrdersDto;
import com.resturant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<Map<String,String>> saveOrder(@RequestBody OrdersDto ordersDto){
        return ResponseEntity.created(URI.create("/orders/saveOrder")).body(orderService.saveOrder(ordersDto));
    }

    @GetMapping("/orderDetails/code/{code}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<OrderDetailsVM> getByCode(@PathVariable @Validated String code){
        return ResponseEntity.ok().body(orderService.showOrder(code));
    }

    @GetMapping("/allOrderDetails")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<OrderDetailsVM>> getAllOrderDetails() {
        return ResponseEntity.ok(orderService.getAllOrderDetails());
    }

    @GetMapping("/userOrderDetails")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<OrderDetailsVM>> getUserOrderDetails() {
        return ResponseEntity.ok(orderService.getUserOrderDetails());
    }

}
