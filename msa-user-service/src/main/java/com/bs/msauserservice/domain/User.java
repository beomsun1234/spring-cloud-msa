package com.bs.msauserservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;
    private String nickName;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String email, String password,String nickName, Role role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
    }
}
