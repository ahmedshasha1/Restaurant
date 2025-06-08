package com.resturant.controller.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.resturant.dto.ProductsDto;
import com.resturant.model.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsVM {
    private String userName;
    private String email;
    private String code;
    private String totalPrice;
    private String totalQuantity;
    private List<ProductsDto> products;

}
