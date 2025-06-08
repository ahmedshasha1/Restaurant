package com.resturant.service.impl;

import com.resturant.controller.vm.ChefsResponseVM;
import com.resturant.dao.ChefsRepo;
import com.resturant.dto.ChefsDto;
import com.resturant.model.Chefs;
import com.resturant.service.ChefService;
import com.resturant.service.mapper.ChefsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefsRepo chefsRepo;


    @Override
    public ChefsResponseVM getAllChefs(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Chefs> chefs = chefsRepo.findAll(pageable);

        return new ChefsResponseVM(
                ChefsMapper.chefsMapper.toDto(chefs.getContent()),
                chefs.getTotalElements()
        );
    }
}
