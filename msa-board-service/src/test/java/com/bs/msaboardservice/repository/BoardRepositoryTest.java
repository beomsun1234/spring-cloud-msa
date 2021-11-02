package com.bs.msaboardservice.repository;

import com.bs.msaboardservice.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 저장")
    void test_save(){
        //given
        Board board = Board.builder().author("test").content("test").memberId(1L).title("test").build();
        //when
        Board savedBoard = boardRepository.save(board);
        //then
        Assertions.assertThat(savedBoard.getContent()).isEqualTo("test");
    }
    @Test
    @DisplayName("게시판 상세 조회")
    void test_findById(){
        //given
        Board board = Board.builder().author("test").content("test").memberId(1L).title("test").build();
        Board savedBoard = boardRepository.save(board);
        //when
        Board findBoard = boardRepository.findById(savedBoard.getId()).orElseThrow();
        //then
        Assertions.assertThat(findBoard.getContent()).isEqualTo("test");
    }
    @Test
    @DisplayName("게시판 조회, 유저가 작성한 게시판 조회")
    void test_findByMemberId(){
        //given
        Board board = Board.builder().author("test").content("test").memberId(1L).title("test").build();
        Board board2 = Board.builder().author("test").content("test2").memberId(1L).title("test2").build();
        Board savedBoard = boardRepository.save(board);
        Board savedBoard2 = boardRepository.save(board2);
        //when
        List<Board> boards = boardRepository.findByMemberId(1L);
        //then
        Assertions.assertThat(boards.size()).isEqualTo(2);
    }
}