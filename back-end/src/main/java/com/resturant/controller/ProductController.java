package com.resturant.controller;

import com.resturant.controller.vm.ProductResponseVM;
import com.resturant.dto.ProductsDto;
import com.resturant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/pageNumber/{pageNo}/pageSize/{pageSize}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<ProductResponseVM> getAllProducts(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo-1 ,pageSize));
    }

    @GetMapping("/category/get-products-by-category-id/{id}/pageNumber/{pageNo}/pageSize/{pageSize}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<ProductResponseVM> getProductsByCategoryId(@PathVariable @Validated long id,@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return ResponseEntity.ok(productService.getProductsByCategoryID(id, pageNo-1 ,pageSize));
    }

    @GetMapping("/get-product-by-key/{key}/pageNumber/{pageNo}/pageSize/{pageSize}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<ProductResponseVM> getProductsNameOrDesc(@PathVariable  @Validated String key,@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return ResponseEntity.ok(productService.getAllProductByNameOrDescription(key, pageNo-1 ,pageSize));
    }

    @GetMapping("/search/{letter}/pageNumber/{pageNo}/pageSize/{pageSize}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<ProductResponseVM> search(@PathVariable  @Validated String letter,@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        return ResponseEntity.ok(productService.getAllProductByLetter(letter, pageNo-1 ,pageSize));
    }

    @PostMapping("/create-product")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> createProducts(@RequestBody @Validated ProductsDto productsDto){
        productService.createProduct(productsDto);
        return ResponseEntity.created(URI.create("/products/createNewProduct")).build();
    }
    @PostMapping("/delete-product")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> deleteProduct(@RequestBody @Validated Long id){
        productService.deleteProduct(id);
        return ResponseEntity.created(URI.create("/products/deleteProduct")).build();
    }


}
