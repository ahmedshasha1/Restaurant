package com.resturant.config.security;

import com.resturant.model.security.Roles;
import com.resturant.model.security.Users;
import com.resturant.service.auth.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHandler jwtTokenHandler;
    @Autowired
    private UserService userService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (path.contains("login") || path.contains("create-user")
                || path.contains("/v3/api-docs") || path.contains("/swagger-ui")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token == null || !token.contains("Bearer ") ){
            response.reset();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        token = token.substring(7);
        if (!jwtTokenHandler.validateToken(token)){
            response.reset();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
        Users users = userService.checkClientExistByToken(token);
        if (users == null){
            response.reset();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        for (Roles roles: users.getRoles()) {
            SimpleGrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(roles.getRole());
            simpleGrantedAuthorities.add(grantedAuthorities);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(users,null , simpleGrantedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);


    } catch (ServletException e) {
            throw new RuntimeException(e);
        }
}
}
