package com.resturant.dao;

import com.resturant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {

    boolean existsByCode(String code);

    Optional<Orders> findByCode(String code);

    List<Orders> findByUsersId(Long id);
}
