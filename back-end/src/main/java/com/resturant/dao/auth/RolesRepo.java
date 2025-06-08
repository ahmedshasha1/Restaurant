package com.resturant.dao.auth;


import com.resturant.model.security.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo  extends JpaRepository<Roles,Long> {

    Roles getByRole(String role);
}
