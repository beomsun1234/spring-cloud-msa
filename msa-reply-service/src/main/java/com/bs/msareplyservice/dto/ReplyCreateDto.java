package com.bs.msareplyservice.dto;

import com.bs.msareplyservice.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyCreateDto {
    private Long memberId;
    private String author;
    private String content;

    @Builder
    public ReplyCreateDto(Long memberId, String author, String content) {
        this.memberId = memberId;
        this.author = author;
        this.content = content;
    }

    public Reply toEntity(Long boardId){
        return Reply
                .builder()
                .author(author)
                .boardId(boardId)
                .content(content)
                .memberId(memberId)
                .build();
    }
}
