package com.bs.msagateway.filter;

import com.bs.msagateway.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;


@Component
@Slf4j
public class CustomAuthFilter extends AbstractGatewayFilterFactory<CustomAuthFilter.Config> {

    private final JwtUtil jwtUtil;

    public CustomAuthFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // Request Header 에 Authorization 이 존재하는지 확인
            if(!request.getHeaders().containsKey("Authorization")){
                return handleUnAuthorized(exchange); // 401 Error
            }
            //Authorization 헤더 추출 후
            List<String> authHeader = request.getHeaders().get("Authorization");
            String tokenWithBearer = Objects.requireNonNull(authHeader).get(0);
            //헤더가 Bearer로 시작하는지 확인
            if(!tokenWithBearer.startsWith("Bearer")){
                return handleUnAuthorized(exchange);
            }
            String token = jwtUtil.extractHeader(tokenWithBearer);
            // 토큰 검증
            if(!jwtUtil.validateToken(token)) {
                return handleUnAuthorized(exchange); // 토큰이 일치하지 않을 때
            }
            return chain.filter(exchange); // 토큰이 일치할 때
        });
    }

    private Mono<Void> handleUnAuthorized(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {
    }
}
