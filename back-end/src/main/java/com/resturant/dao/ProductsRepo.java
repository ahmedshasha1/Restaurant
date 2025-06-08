package com.resturant.dao;

import com.resturant.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Long> {
    boolean existsByNameIgnoreCase(String name);
     Page<Products> findByCategory_Id(Long id , Pageable pageable);



     Products findProductsById(Long id);

    @Query("SELECT p FROM Products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :letters, '%')) AND LOWER(p.description) LIKE LOWER(CONCAT('%', :letters, '%'))")
    Page<Products> findByNameAndDescriptionContaining(@Param("letters") String letters, Pageable pageable);

    @Query("SELECT p FROM Products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :key, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :key, '%'))")
    Page<Products> findAllProductByNameOrDescriptionContaining(@Param("key") String key, Pageable pageable);



}
