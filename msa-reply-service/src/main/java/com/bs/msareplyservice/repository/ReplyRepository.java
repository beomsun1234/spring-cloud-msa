package com.bs.msareplyservice.repository;

import com.bs.msareplyservice.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    //게시판 댓글
    List<Reply>findByBoardId(Long boardId);
}
