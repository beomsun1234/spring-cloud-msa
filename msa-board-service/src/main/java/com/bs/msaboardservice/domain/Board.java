package com.bs.msaboardservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Long memberId; //작성자 id

    private String author;

    @Builder
    public Board(Long id, String title, String content, Long memberId, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.author = author;
    }


}
