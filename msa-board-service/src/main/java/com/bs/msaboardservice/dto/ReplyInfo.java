package com.bs.msaboardservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public ReplyInfo(Long reply_id, Long user_id, Long board_id, String author, String content, UserInfo userInfo) {
        this.reply_id = reply_id;
        this.user_id = user_id;
        this.board_id = board_id;
        this.author = author;
        this.content = content;
        this.userInfo = userInfo;
    }
}