package com.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resturant.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsDto extends BaseEntityDto {
    private String description;
    private Double price;
    private CategoryDto category;
}
