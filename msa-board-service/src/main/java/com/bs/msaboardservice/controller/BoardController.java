package com.bs.msaboardservice.controller;

import com.bs.msaboardservice.dto.BoardCreateDto;
import com.bs.msaboardservice.dto.BoardDetailDto;
import com.bs.msaboardservice.dto.BoardInfo;
import com.bs.msaboardservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/hello")
    public String home(){
        return "msa-board-service";
    }

    /**
     * 상세보기
     * @param id
     * @return
     */
    @GetMapping("board/{id}")
    public BoardDetailDto findOneById(@PathVariable Long id, HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return boardService.findOneById(id,header);
    }
    /**
     * 글작성
     */
    @PostMapping("{id}/board")
    public Long createBoard(@PathVariable Long id, @RequestBody BoardCreateDto boardCreateDto){
        return boardService.creatBoard(id, boardCreateDto);
    }
    /**
     * 특정 유저가 작성한 글 보기
     * @param id
     * @return
     */
    @GetMapping("{id}/board")
    public List<BoardInfo> home(@PathVariable Long id){
        return boardService.findBoardsByMemberId(id);
    }

}
