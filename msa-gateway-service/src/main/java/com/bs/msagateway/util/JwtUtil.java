package com.bs.msagateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private String secretKey;

    public JwtUtil( @Value("${secret.key}") String secretKey){
        this.secretKey = secretKey;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String extractHeader(String token){
        return token.substring(7);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        log.info("---토큰 유효 기간 체크--");
        try {
            final Date expiration = extractAllClaims(token).getExpiration();
            return expiration.before(new Date());
        }catch (Exception e) {
            log.info("토큰에러 발생");
            return false;
        }
    }
    
    public Boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            log.info("---인증된 토큰임--");
            return !isTokenExpired(token);
        }catch (JwtException e){
            log.info("유효하지 않은 토큰");
            return false;
        }
    }

}
