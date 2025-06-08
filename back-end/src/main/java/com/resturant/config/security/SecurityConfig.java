package com.resturant.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        // Disable session creation
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Enable CORS
        http.securityMatcher("/**").cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.authorizeHttpRequests(
                api -> api
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs*/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/create-user").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/getProducts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/getProductsBy/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/getProductByName/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/getProductsByletter/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/getProductSize").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/getProductSizeByCategoryId/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/getProductSizeByKey/**").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/Category/getAll").hasAnyRole("USER", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/Chefs/getAll").hasAnyRole("USER", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "/auth/addClientWithRole").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/product/createNewProduct").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/create-product").hasAnyRole("MANAGER","ADMIN")

                        .requestMatchers(HttpMethod.POST, "/category/create-category").hasAnyRole("MANAGER","ADMIN")

                        .requestMatchers(HttpMethod.POST, "/orders/saveOrder").hasAnyRole("USER","ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/orderDetails/code/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/orders/userOrderDetails").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/orders/allOrderDetails").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/contact-info").hasRole("USER")
                        //.requestMatchers(HttpMethod.POST, "/category/save").hasRole("USER")
                        .anyRequest().authenticated() // Secure all other endpoints
        );




        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
