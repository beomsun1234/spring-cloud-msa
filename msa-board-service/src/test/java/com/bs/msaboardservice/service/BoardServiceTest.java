package com.bs.msaboardservice.service;

import com.bs.msaboardservice.domain.Board;
import com.bs.msaboardservice.dto.BoardCreateDto;
import com.bs.msaboardservice.dto.BoardInfo;
import com.bs.msaboardservice.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

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
    @DisplayName("게시판 조회, 상세 보기")
    void test_findOneById(){
        Long fakeMemberId = 1L;
        Long fakeBoardId = 1L;
        Board board = Board.builder().id(fakeBoardId).author("test").memberId(fakeMemberId).content("test").title("test").build();
        given(boardRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(board));
        //when
        BoardInfo boardInfo = boardService.findOneById(fakeBoardId);
        //then
        Assertions.assertThat(boardInfo.getAuthor()).isEqualTo("test");
    }

    @Test
    @DisplayName("유저가 작성한 게시판 보기")
    void test_findBoardsByMemberId(){
        Long fakeMemberId = 1L;
        Long fakeBoardId = 1L;
        Board board = Board.builder().id(fakeBoardId).author("test").memberId(fakeMemberId).content("test").title("test").build();
        given(boardRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(board));
        //when
        BoardInfo boardInfo = boardService.findOneById(fakeBoardId);
        //then
        Assertions.assertThat(boardInfo.getAuthor()).isEqualTo("test");
    }
}