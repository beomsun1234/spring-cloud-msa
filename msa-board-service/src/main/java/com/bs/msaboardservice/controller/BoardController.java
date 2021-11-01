package com.bs.msaboardservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {
    @GetMapping("/board/hello")
    public String home(){
        return "msa-board-service";
    }
}
