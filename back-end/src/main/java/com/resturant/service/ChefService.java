package com.resturant.service;

import com.resturant.controller.vm.ChefsResponseVM;
import com.resturant.dto.ChefsDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChefService {

    ChefsResponseVM getAllChefs(Integer pageNo, Integer pageSize);
}
