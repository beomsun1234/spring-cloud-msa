package com.bs.msareplyservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfo {
    private Long user_id;
    private String email;
    private String nickName;
    private String role;

    @Builder
    public UserInfo(Long user_id, String email, String nickName, String role) {
        this.user_id = user_id;
        this.email = email;
        this.nickName = nickName;
        this.role = role;
    }
}
