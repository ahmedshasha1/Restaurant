package com.resturant.controller;

import com.resturant.controller.vm.ChefsResponseVM;
import com.resturant.dto.ChefsDto;
import com.resturant.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chefs")
public class ChefsController {
    @Autowired
    private ChefService chefService;

    @GetMapping("pageNumber/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ChefsResponseVM> getAllChefs(@PathVariable Integer pageNo , @PathVariable  Integer pageSize){
        return ResponseEntity.ok(chefService.getAllChefs(pageNo-1,pageSize));
    }
}
