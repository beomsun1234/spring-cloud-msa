package com.bs.msareplyservice.controller;

import com.bs.msareplyservice.dto.ReplyCreateDto;
import com.bs.msareplyservice.dto.ReplyInfo;
import com.bs.msareplyservice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("")
    public String hello(){
        return "hello-msa-reply-service";
    }

    /**
     * 댓글 생성
     * @param id
     * @param replyCreateDto
     * @return
     */
    @PostMapping("board/{boardId}/reply")
    public ReplyInfo createReply(@PathVariable(name = "boardId") Long id, @RequestBody ReplyCreateDto replyCreateDto){
        return replyService.createReply(id, replyCreateDto);
    }

    /**
     * 게시판 댓글 조회(게시글 상세 보기)
     * @param id
     * @return
     */
    @GetMapping("board/{boardId}/reply")
    public List<ReplyInfo> findRepliesByBoardId(@PathVariable(name = "boardId") Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return replyService.findRepliesByBoardId(id,token);
    }

}
