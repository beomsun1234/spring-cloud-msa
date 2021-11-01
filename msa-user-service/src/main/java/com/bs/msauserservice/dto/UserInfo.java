package com.bs.msauserservice.dto;

import com.bs.msauserservice.domain.Role;
import com.bs.msauserservice.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfo {
    private String email;
    private String nickName;
    private Role role;

    @Builder
    public UserInfo(User user){
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.role = user.getRole();
    }
}
