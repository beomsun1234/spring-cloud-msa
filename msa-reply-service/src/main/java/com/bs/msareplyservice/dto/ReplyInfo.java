package com.bs.msareplyservice.dto;

import com.bs.msareplyservice.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReplyInfo {
    private Long reply_id;
    private Long user_id;
    private Long board_id;
    private String author;
    private String content;
    private UserInfo userInfo;

    @Builder
    public ReplyInfo(Reply reply){
        this.user_id = reply.getMemberId();
        this.reply_id = reply.getId();
        this.board_id = reply.getBoardId();
        this.author = reply.getAuthor();
        this.content = reply.getContent();
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }
}
