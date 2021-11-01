package com.bs.msauserservice.util;

import com.bs.msauserservice.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey;

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60L * 60L;

    public JwtUtil(@Value("${secret.key}") String secretKey){
        this.secretKey = secretKey;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(String email, Role role){
        Claims claims = Jwts.claims().setSubject("msa-auth");
        claims.put("email",email);
        claims.put("role", role);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDATION_SECOND))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}
