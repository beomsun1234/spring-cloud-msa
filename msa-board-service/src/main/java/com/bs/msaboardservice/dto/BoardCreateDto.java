package com.bs.msaboardservice.dto;

import com.bs.msaboardservice.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCreateDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public BoardCreateDto(String title,String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Board toEntity(Long memberId){
        return Board.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .author(author)
                .build();
    }
}
