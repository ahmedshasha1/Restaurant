package com.resturant.dao.auth;

import com.resturant.model.Category;
import com.resturant.model.security.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<Users,Long> {

    Users getUsersByEmail(String email);
}
