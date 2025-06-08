package com.resturant.service;

import com.resturant.controller.vm.OrderDetailsVM;
import com.resturant.dto.OrdersDto;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String,String> saveOrder(OrdersDto ordersDto);

    OrderDetailsVM showOrder(String code);

    List<OrderDetailsVM> getAllOrderDetails();

    List<OrderDetailsVM> getUserOrderDetails();
}
