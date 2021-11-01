package com.bs.msauserservice.controller;

import com.bs.msauserservice.dto.LoginDto;
import com.bs.msauserservice.dto.SignUpDto;
import com.bs.msauserservice.dto.UserInfo;
import com.bs.msauserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/hello")
    public String home(){
        return "msa-user-service";
    }

    @GetMapping("/user/{id}")
    public UserInfo findOneById(@PathVariable Long id){
        return userService.getUserInfoById(id);
    }

    @PostMapping("/auth/login")
    public String findOneById(@RequestBody LoginDto loginDto){
        return userService.logInUser(loginDto);
    }

    @PostMapping("/auth/join")
    public Long findOneById(@RequestBody SignUpDto signUpDto){
        return userService.signUpUser(signUpDto);
    }
}
