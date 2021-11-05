package com.bs.msaboardservice.client;

import com.bs.msaboardservice.dto.ReplyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "msa-reply-service")
public interface ReplyServiceClient {
    @GetMapping("board/{id}/reply")
    List<ReplyInfo> getReplies(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
