package com.bs.msauserservice.controller;

import com.bs.msauserservice.dto.LoginDto;
import com.bs.msauserservice.dto.SignUpDto;
import com.bs.msauserservice.dto.UserInfo;
import com.bs.msauserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/hello")
    public String home(){
        return "msa-user-service";
    }

    /**
     * 유저 찾기(게시판 상세보기시 댓글 작성한 유저정보를 제공하기 위해)
     * @param ids
     * @return
     */
    @GetMapping("/user/reply")
    public List<UserInfo> findAllByIds(@RequestParam List<Long> ids){
        return userService.findAllByIds(ids);
    }

    /**
     * 유저상세보기
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public UserInfo findOneById(@PathVariable Long id){
        return userService.getUserInfoById(id);
    }

    /**
     * 로그인
     * @param id
     * @return
     */
    @PostMapping("/auth/login")
    public String findOneById(@RequestBody LoginDto loginDto){
        return userService.logInUser(loginDto);
    }

    /**
     * 회원가입
     * @param signUpDto
     * @return
     */
    @PostMapping("/auth/join")
    public Long findOneById(@RequestBody SignUpDto signUpDto){
        return userService.signUpUser(signUpDto);
    }
}
