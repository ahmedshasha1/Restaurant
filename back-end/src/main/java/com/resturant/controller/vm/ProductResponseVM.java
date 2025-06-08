package com.resturant.controller.vm;

import com.resturant.dto.ProductsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseVM {
    private List<ProductsDto> products;
    private Long totalPageSize;
}
