package com.bs.msaboardservice.service;

import com.bs.msaboardservice.domain.Board;
import com.bs.msaboardservice.dto.BoardCreateDto;
import com.bs.msaboardservice.dto.BoardInfo;
import com.bs.msaboardservice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     * 게시판 생성
     * @param memberId
     * @param boardCreateDto
     * @return
     */
    @Transactional
    public Long creatBoard(Long memberId,BoardCreateDto boardCreateDto){
        return boardRepository.save(boardCreateDto.toEntity(memberId)).getId();
    }

    /**
     * 게시판 상세보기
     * @param boardId
     * @return
     */
    @Transactional(readOnly = true)
    public BoardInfo findOneById(Long boardId){
        return BoardInfo
                .builder()
                .board(boardRepository.findById(boardId)
                        .orElseGet(()-> Board.builder().build()))
                .build();
    }

    /**
     * 유저가 작성한 게시판 보기
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public List<BoardInfo> findBoardsByMemberId(Long memberId){
        List<Board> boards = boardRepository.findByMemberId(memberId);
        if(boards.isEmpty()){
            return new ArrayList<>();
        }
        return boards.stream().map(board -> BoardInfo.builder().board(board).build()).collect(Collectors.toList());
    }
}
