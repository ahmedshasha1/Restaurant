package com.resturant.controller.vm;

import com.resturant.dto.ChefsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChefsResponseVM {
    private List<ChefsDto> chefs;
    private Long totalPageSize;
}
