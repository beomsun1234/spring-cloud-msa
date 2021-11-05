package com.bs.msareplyservice.service;


import com.bs.msareplyservice.client.UserServiceClient;
import com.bs.msareplyservice.domain.Reply;
import com.bs.msareplyservice.dto.ReplyCreateDto;
import com.bs.msareplyservice.dto.ReplyInfo;
import com.bs.msareplyservice.dto.UserInfo;
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
    private final UserServiceClient userServiceClient;
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
    public List<ReplyInfo> findRepliesByBoardId(Long bordId, String token){
        List<Reply> replies = replyRepository.findByBoardId(bordId);
        if (replies.isEmpty()){
            return new ArrayList<>();
        }
        List<ReplyInfo> replyInfos = replies.stream()
                .map(reply -> ReplyInfo.builder().reply(reply).build())
                .collect(Collectors.toList());
        //댓글 작성한 유저 id list로 만들고
        List<Long> ids = replies.stream().map(Reply::getMemberId).collect(Collectors.toList());
        //유저서비스 호출
        List<UserInfo> userInfos = userServiceClient.getUserInfos(token, ids);
        //댓글 정보에 유저 정보 삽입
        for (ReplyInfo replyInfo : replyInfos) {
            userInfos.stream().filter(userInfo -> {
                return userInfo.getUser_id().equals(replyInfo.getUser_id());
            }).forEach(replyInfo::setUserInfo);
        }
        return replyInfos;
    }
}
