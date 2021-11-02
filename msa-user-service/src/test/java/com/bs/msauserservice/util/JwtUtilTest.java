package com.bs.msauserservice.util;

import com.bs.msauserservice.domain.Role;
import com.bs.msauserservice.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void init(){
        String secretKey = "teststetsetseetsetesetsetsetestsetestsetseet";
        jwtUtil = new JwtUtil(secretKey);
    }

    @Test
    @DisplayName("토큰 생성")
    void test_generateToken(){
        //given
        User loginuser = User.builder().id(1L).role(Role.ROLE_USER).nickName("test").email("test").build();
        //when
        String token = jwtUtil.generateToken(loginuser);
        //then
        System.out.println("token="+token);
    }

}