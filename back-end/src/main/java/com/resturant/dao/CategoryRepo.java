package com.resturant.dao;

import com.resturant.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    boolean existsByNameIgnoreCase(String name);
    Category findCategoriesById(Long id);

}
