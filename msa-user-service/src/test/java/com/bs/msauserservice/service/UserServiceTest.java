package com.bs.msauserservice.service;

import com.bs.msauserservice.domain.Role;
import com.bs.msauserservice.domain.User;
import com.bs.msauserservice.dto.LoginDto;
import com.bs.msauserservice.dto.SignUpDto;
import com.bs.msauserservice.dto.UserInfo;
import com.bs.msauserservice.repository.UserRepository;
import com.bs.msauserservice.util.JwtUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입")
    void test_signUpUser(){
        //given
        SignUpDto signUpDto = SignUpDto.builder().email("test").nickName("test").password("1234").build();
        given(userRepository.save(any())).willReturn(signUpDto.toEntity());
        //when //then
        userService.signUpUser(signUpDto);
    }
    @Test
    @DisplayName("로그인 완료 후 토큰 발급")
    void test_logInUser(){
        //given
        LoginDto loginDto = LoginDto.builder().email("test").password("1234").build();
        User user = User.builder().email("test").password("test").role(Role.ROLE_USER).nickName("test").id(1L).build();
        given(userRepository.findByEmailAndPassword(anyString(),anyString())).willReturn(java.util.Optional.ofNullable(user));
        //when //then
        userService.logInUser(loginDto);
    }

    @Test
    @DisplayName("로그인 완류 후 토큰 발급")
    void test_getUserInfoById(){
        //given
        User user = User.builder().email("test").password("test").role(Role.ROLE_USER).nickName("test").id(1L).build();
        given(userRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(user));
        //when
        UserInfo userInfo = userService.getUserInfoById(1L);
        //then
        Assertions.assertThat(userInfo.getEmail()).isEqualTo("test");
    }

}