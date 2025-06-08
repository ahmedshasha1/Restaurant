package com.resturant.service.impl;

import com.resturant.controller.vm.OrderDetailsVM;
import com.resturant.dao.OrdersRepo;
import com.resturant.dto.OrdersDto;
import com.resturant.model.Orders;
import com.resturant.model.Products;
import com.resturant.model.security.Users;
import com.resturant.service.OrderService;
import com.resturant.service.ProductService;
import com.resturant.service.mapper.OrderMapper;
import com.resturant.service.mapper.ProductsMapper;
import com.resturant.util.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ProductService productService;

    @Override
    public Map<String, String> saveOrder(OrdersDto ordersDto) {
        if(Objects.isNull(ordersDto)){
            throw new RuntimeException("order.empty");
        }
        List<Products> products = ProductsMapper.productMapper.DtoToEntityList(productService.findProductsById(ordersDto.getProductsId()));
        double totalPrice = 0.0;

        if(Objects.isNull(products)){
            throw new RuntimeException("order.empty");
        }

        for (Products pro: products) {

        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users= (Users) authentication.getPrincipal();

        Orders orders = OrderMapper.order.dtoToEntity(ordersDto);
        orders.setUsers(users);
        orders.setProducts(products);

//        String code;
//        do{
//            code = UserCode.userCode();
//        } while (ordersRepo.findByCode(code).isPresent());

        String code = UserCode.userCode();
        while (ordersRepo.existsByCode(code)){
            code = UserCode.userCode();
        }

        orders.setCode(code);
        orders.setTotalPrice(String.valueOf(totalPrice));
        orders.setTotalQuantity(String.valueOf(products.size()));

        ordersRepo.save(orders);
        Map<String,String> response = new LinkedHashMap<>();
        response.put("code",orders.getCode());
        System.out.println("Products count: " + products.size());

        return response;
    }

    @Override
    public OrderDetailsVM showOrder(String code) {
        Optional<Orders> orders = ordersRepo.findByCode(code);

        if (orders.isEmpty()) {
            throw new RuntimeException("error.invalid.orderDetails");
        }
        Orders order = orders.get();

        return extractOrderDetailsVM(order );
    }

    @Override
    public List<OrderDetailsVM> getAllOrderDetails() {
        List<Orders> orders=ordersRepo.findAll();
        if (orders.isEmpty()) {
            throw new RuntimeException("error.invalid.orderDetails");
        }
        List<OrderDetailsVM> orderDetailsVMS=new ArrayList<>();
        OrderDetailsVM  orderDetails=null;
        for (int i = 0; i < orders.size(); i++) {
              orderDetails =  extractOrderDetailsVM(orders.get(i));
              orderDetails.setUserName(orders.get(i).getUsers().getName());
              orderDetails.setEmail(orders.get(i).getUsers().getEmail());
              orderDetailsVMS.add(orderDetails);
        }

        return orderDetailsVMS;
    }

    @Override
    public List<OrderDetailsVM> getUserOrderDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users= (Users) authentication.getPrincipal();


        List<OrderDetailsVM> orderDetailsVMS=new ArrayList<>();

        List<Orders> orders=ordersRepo.findByUsersId(users.getId());
        OrderDetailsVM  orderDetails=null;

        for (int i = 0; i < orders.size(); i++) {
            orderDetails =  extractOrderDetailsVM(orders.get(i));
            orderDetailsVMS.add(orderDetails);

        }

        return orderDetailsVMS;
    }

    private OrderDetailsVM extractOrderDetailsVM(Orders order) {
        OrderDetailsVM orderDetailsVM = new OrderDetailsVM();
        orderDetailsVM.setCode(order.getCode());
        orderDetailsVM.setTotalPrice(order.getTotalPrice());
        orderDetailsVM.setTotalQuantity(order.getTotalQuantity());
        orderDetailsVM.setProducts(ProductsMapper.productMapper.toDtoList(order.getProducts()));
        return orderDetailsVM;
    }


}
