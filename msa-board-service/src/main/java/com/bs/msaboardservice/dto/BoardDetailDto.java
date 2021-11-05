package com.bs.msaboardservice.dto;

import com.bs.msaboardservice.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDetailDto {
    private String title;
    private String content;
    private String author;
    private Long boardId;
    private Long memberId; // 작성자 id
    private List<ReplyInfo> replyInfo = new ArrayList<>();

    @Builder
    public BoardDetailDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getAuthor();
        this.boardId = board.getId();
        this.memberId = board.getMemberId();
    }

    public void setReplyInfo(List<ReplyInfo> replyInfo){
        this.replyInfo = replyInfo;
    }
}
