package com.resturant.config.security;

import com.resturant.dto.security.UserDto;
import com.resturant.model.security.Roles;
import com.resturant.model.security.Users;
import com.resturant.sitting.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenHandler {
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;
    private String secret;
    private Duration timeExpired;

    public JwtTokenHandler(JwtConfig jwtConfig) {
        this.secret = jwtConfig.getSecret();
        this.timeExpired= jwtConfig.getTime();
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser= Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(Users user){
        for (Roles role: user.getRoles()) {
            role.setUsers(null);
        }
        Date issueTime= new Date();
        Date timeOfExpire = Date.from(issueTime.toInstant().plus(timeExpired));

        return jwtBuilder.setIssuedAt(issueTime)
                .setExpiration(timeOfExpire)
                .setSubject(user.getEmail())
                .claim("role", user.getRoles()).compact() ;
    }

    public boolean validateToken(String token){
        if(jwtParser.isSigned(token)){
            try{
                Claims claims = jwtParser.parseClaimsJws(token).getBody();
                Date issue = claims.getIssuedAt();
                Date expire = claims.getExpiration();

                return expire.after(new Date()) && issue.before(expire);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public String getSubject(String token){
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }
}
