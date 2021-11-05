package com.bs.msaboardservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfo {
    private Long user_id;
    private String email;
    private String nickName;
    private String role;
}

