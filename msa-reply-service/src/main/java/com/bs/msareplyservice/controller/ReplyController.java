package com.bs.msareplyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {

    @GetMapping("")
    public String hello(){
        return "hello-msa-reply-service";
    }
}
