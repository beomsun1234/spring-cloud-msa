package com.bs.msagateway.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;

    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtc2EtYXV0aCIsImVtYWlsIjoidGVzdCIsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE2MzU2NzcwNjgsImV4cCI6MTYzNTY4MDY2OH0.vpbe-XCygz-AYXcoly5xrP9q0neP6pnPidQ195Oe3hg";

    @BeforeEach
    void init(){
        String secretKey = "testestestestestestestestsetestestesteststtestse";
        jwtUtil = new JwtUtil(secretKey);
    }

    @Test
    @DisplayName("토큰 검증, 유효기간 지난 토큰으로 체크")
    void test(){
        //when
        Boolean validateToken = jwtUtil.validateToken(token);
        //then
        Assertions.assertThat(validateToken).isFalse();
    }


}