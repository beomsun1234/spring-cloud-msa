package com.bs.msareplyservice.client;

import com.bs.msareplyservice.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msa-user-service")
public interface UserServiceClient {
    /**
     * 유저 서비스에서 댓글작성한(특정게시판) 사람 가져오기
     * @param token
     * @param ids
     * @return
     */
    @GetMapping("user/reply")
    List<UserInfo> getUserInfos(@RequestHeader("Authorization") String token, @RequestParam List<Long> ids);
}
