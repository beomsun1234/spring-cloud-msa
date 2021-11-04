package com.bs.msauserservice.service;

import com.bs.msauserservice.domain.User;
import com.bs.msauserservice.dto.LoginDto;
import com.bs.msauserservice.dto.SignUpDto;
import com.bs.msauserservice.dto.UserInfo;
import com.bs.msauserservice.repository.UserRepository;
import com.bs.msauserservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    /**
     * 로그인
     * @param loginDto
     * @return
     */
    @Transactional(readOnly = true)
    public String logInUser(LoginDto loginDto){
        User loginUser = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()).orElseThrow();
        String token = jwtUtil.generateToken(loginUser);
        log.info("토큰={}",token);
        return token;
    }

    /**
     * 여러 유저 찾기 by Ids
     * @param ids
     * @return
     */
    @Transactional(readOnly = true)
    public List<UserInfo> findAllByIds(List<Long> ids){
        List<User> users = userRepository.findAllById(ids);
        if(users.isEmpty()){
            return new ArrayList<>();
        }
        return users.stream().map(user -> UserInfo.builder().user(user).build()).collect(Collectors.toList());
    }

    /**
     * 회원가입
     * @param signUpDto
     * @return
     */
    @Transactional
    public Long signUpUser(SignUpDto signUpDto){
        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("에러");
        }
        return userRepository.save(signUpDto.toEntity()).getId();
    }

    /**
     * 유저 상세
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public UserInfo getUserInfoById(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return UserInfo.builder().user(user).build();
    }
}
