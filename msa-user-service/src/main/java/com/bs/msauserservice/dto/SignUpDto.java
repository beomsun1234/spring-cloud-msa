package com.bs.msauserservice.dto;

import com.bs.msauserservice.domain.Role;
import com.bs.msauserservice.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String password;
    private String nickName;


    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .role(Role.ROLE_USER)
                .build();
    }
}
