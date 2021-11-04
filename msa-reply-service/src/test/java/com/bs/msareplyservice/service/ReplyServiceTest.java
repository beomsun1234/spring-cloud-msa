package com.bs.msareplyservice.service;

import com.bs.msareplyservice.domain.Reply;
import com.bs.msareplyservice.dto.ReplyCreateDto;
import com.bs.msareplyservice.dto.ReplyInfo;
import com.bs.msareplyservice.repository.ReplyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class ReplyServiceTest {

    @Mock
    private ReplyRepository replyRepository;

    @InjectMocks
    private ReplyService replyService;

    @Test
    @DisplayName("댓글 생성")
    void test_createReply(){
        //given
        Long fakeBoardId = 1L;
        ReplyCreateDto replyCreateDto = ReplyCreateDto.builder().author("test").content("test").memberId(1L).build();
        given(replyRepository.save(any())).willReturn(replyCreateDto.toEntity(fakeBoardId));
        //when
        replyService.createReply(fakeBoardId, replyCreateDto);
        //then
        System.out.println("로그인성공");
    }

    @Test
    @DisplayName("특정 게시판 댓글 조회(게시판 상세)")
    void test_findRepliesByBoardId(){
        //given
        Long fakeBoardId = 1L;

        Reply reply = Reply.builder().boardId(fakeBoardId).author("test").content("test").memberId(1L).id(1L).build();
        Reply reply2 = Reply.builder().boardId(fakeBoardId).author("test2").content("test2").memberId(2L).id(2L).build();
        List<Reply> replies1 = Arrays.asList(reply, reply2);
        given(replyRepository.findByBoardId(anyLong())).willReturn(replies1);
        //when
        List<ReplyInfo> replies = replyService.findRepliesByBoardId(fakeBoardId);
        //then
        Assertions.assertThat(replies.size()).isEqualTo(2);
    }

}