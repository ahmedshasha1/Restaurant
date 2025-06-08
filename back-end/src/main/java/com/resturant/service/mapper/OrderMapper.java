package com.resturant.service.mapper;

import com.resturant.dto.OrdersDto;
import com.resturant.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper order = Mappers.getMapper(OrderMapper.class);

    OrdersDto toDto(Orders orders);
    Orders dtoToEntity(OrdersDto ordersDto);

    List<OrdersDto> toDtoList(List<Orders> orders);






}
