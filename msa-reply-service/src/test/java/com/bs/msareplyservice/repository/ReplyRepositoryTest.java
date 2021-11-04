package com.bs.msareplyservice.repository;

import com.bs.msareplyservice.domain.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @DisplayName("댓글 생성")
    void test_save(){
        //given
        Reply reply = Reply.builder().memberId(1L).content("test").author("test").boardId(1L).build();
        //when
        Reply savedReply = replyRepository.save(reply);
        //then
        Assertions.assertThat(savedReply.getContent()).isEqualTo("test");
    }

    @Test
    @DisplayName("게시판 댓글 조회")
    void test_findByBoardId(){
        //given
        Reply reply = Reply.builder().memberId(1L).content("test").author("test").boardId(1L).build();
        Reply reply2 = Reply.builder().memberId(2L).content("test2").author("test2").boardId(1L).build();
        replyRepository.save(reply);
        replyRepository.save(reply2);
        //when
        List<Reply> findReplies = replyRepository.findByBoardId(1L);
        //then
        Assertions.assertThat(findReplies.size()).isEqualTo(2);
    }

}