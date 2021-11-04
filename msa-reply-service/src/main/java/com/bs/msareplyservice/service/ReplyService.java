package com.bs.msareplyservice.service;


import com.bs.msareplyservice.domain.Reply;
import com.bs.msareplyservice.dto.ReplyCreateDto;
import com.bs.msareplyservice.dto.ReplyInfo;
import com.bs.msareplyservice.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public Long createReply(Long boardId, @RequestBody ReplyCreateDto replyCreateDto){
        return replyRepository.save(replyCreateDto.toEntity(boardId)).getId();
    }

    /**
     * 특정 게시판 댓글 조회(게시판 상세)
     * @param bordId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ReplyInfo> findRepliesByBoardId(Long bordId){
        List<Reply> replies = replyRepository.findByBoardId(bordId);
        if (replies.isEmpty()){
            return new ArrayList<>();
        }
        return replies.stream().map(reply -> ReplyInfo.builder().reply(reply).build()).collect(Collectors.toList());
    }
}
