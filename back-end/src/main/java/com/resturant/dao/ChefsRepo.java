package com.resturant.dao;

import com.resturant.model.Chefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefsRepo extends JpaRepository<Chefs,Long> {
}
