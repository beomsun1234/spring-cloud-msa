package com.bs.msareplyservice.dto;

import com.bs.msareplyservice.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyInfo {
    private Long reply_id;
    private Long member_id;
    private Long board_id;
    private String author;
    private String content;

    @Builder
    public ReplyInfo(Reply reply){
        this.reply_id = reply.getId();
        this.board_id = reply.getBoardId();
        this.member_id = reply.getMemberId();
        this.author = reply.getAuthor();
        this.content = reply.getContent();
    }
}
