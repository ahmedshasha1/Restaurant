package com.resturant.service.auth;

import com.resturant.dto.security.TokenDto;
import com.resturant.dto.security.UserLoginDto;

public interface AuthService {
    TokenDto login(UserLoginDto userLoginDto);

}
