package com.bs.msaboardservice.service;

import com.bs.msaboardservice.client.ReplyServiceClient;
import com.bs.msaboardservice.domain.Board;
import com.bs.msaboardservice.dto.BoardCreateDto;
import com.bs.msaboardservice.dto.BoardDetailDto;
import com.bs.msaboardservice.dto.BoardInfo;
import com.bs.msaboardservice.dto.ReplyInfo;
import com.bs.msaboardservice.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Mock
    private ReplyServiceClient replyServiceClient;
    @Test
    @DisplayName("게시판 생성")
    void test_createBoard(){
        Long fakeMemberId = 1L;
        BoardCreateDto boardCreateDto = BoardCreateDto.builder().author("test").content("test").title("test").build();
        Board board = boardCreateDto.toEntity(fakeMemberId);
        given(boardRepository.save(any())).willReturn(board);
        //when //then
        boardService.creatBoard(fakeMemberId,boardCreateDto);
        //then
        System.out.println("게시판 생성 성공");
    }

    @Test
    @DisplayName("게시판 조회, 상세 보기, 댓글 없을 경우")
    void test_findOneById(){
        Long fakeMemberId = 1L;
        Long fakeBoardId = 1L;
        String token = "testToken";
        Board board = Board.builder().id(fakeBoardId).author("test").memberId(fakeMemberId).content("test").title("test").build();
        given(boardRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(board));
        given(replyServiceClient.getReplies(anyLong(),anyString())).willReturn(new ArrayList<>());
        //when
        BoardDetailDto boardInfo = boardService.findOneById(fakeBoardId,token);
        //then
        Assertions.assertThat(boardInfo.getTitle()).isEqualTo("test");
    }
    @Test
    @DisplayName("게시판 조회, 상세 보기, 댓글 있을 경우")
    void test2_findOneById(){
        Long fakeMemberId = 1L;
        Long fakeBoardId = 1L;
        String token = "testToken";
        Board board = Board.builder().id(fakeBoardId).author("test").memberId(fakeMemberId).content("test").title("test").build();
        ReplyInfo replyInfo = ReplyInfo.builder().author("test").content("replyTest").board_id(fakeBoardId).reply_id(1L).user_id(fakeMemberId).build();
        ReplyInfo replyInfo2 = ReplyInfo.builder().author("test2").content("replyTest2").board_id(fakeBoardId).reply_id(2L).user_id(fakeMemberId).build();
        given(boardRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(board));
        given(replyServiceClient.getReplies(anyLong(),anyString())).willReturn(Arrays.asList(replyInfo,replyInfo2));
        //when
        BoardDetailDto boardInfo = boardService.findOneById(fakeBoardId,token);
        //then
        Assertions.assertThat(boardInfo.getTitle()).isEqualTo("test");
        Assertions.assertThat(boardInfo.getReplyInfo().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("유저가 작성한 게시판 보기")
    void test_findBoardsByMemberId(){
        Long fakeMemberId = 1L;
        Long fakeBoardId = 1L;
        String token = "testToken";
        Board board = Board.builder().id(fakeBoardId).author("test").memberId(fakeMemberId).content("test").title("test").build();
        given(boardRepository.findByMemberId(anyLong())).willReturn(Collections.singletonList(board));
        //when
        List<BoardInfo> boards = boardService.findBoardsByMemberId(fakeMemberId);
        //then
        Assertions.assertThat(boards.size()).isEqualTo(1);
    }
}