package com.bs.msareplyservice.repository;

import com.bs.msareplyservice.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
