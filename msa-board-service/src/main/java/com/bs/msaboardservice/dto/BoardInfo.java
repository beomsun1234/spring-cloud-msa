package com.bs.msaboardservice.dto;

import com.bs.msaboardservice.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardInfo {
    private String title;
    private String content;
    private String author;
    private Long boardId;
    private Long memberId; // 작성자 id

    @Builder
    public BoardInfo(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getAuthor();
        this.boardId = board.getId();
        this.memberId = board.getMemberId();
    }


}
