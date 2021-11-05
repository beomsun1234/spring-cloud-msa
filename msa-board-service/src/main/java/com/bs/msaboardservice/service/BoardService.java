package com.bs.msaboardservice.service;

import com.bs.msaboardservice.client.ReplyServiceClient;
import com.bs.msaboardservice.domain.Board;
import com.bs.msaboardservice.dto.BoardCreateDto;
import com.bs.msaboardservice.dto.BoardDetailDto;
import com.bs.msaboardservice.dto.BoardInfo;
import com.bs.msaboardservice.dto.ReplyInfo;
import com.bs.msaboardservice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyServiceClient replyServiceClient;
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
    public BoardDetailDto findOneById(Long boardId,String token){
        //게시판 찾기
        Board board = boardRepository.findById(boardId).orElseThrow();
        BoardDetailDto boardDetailDto = BoardDetailDto.builder().board(board).build();
        //찾은 후 댓글서비스에 댓글목록 요청
        List<ReplyInfo> replies = replyServiceClient.getReplies(boardId, token);
        if(replies.isEmpty()){
            //댓글이 없으면 빈 댓글 반환
            log.info("댓글 없음");
            return boardDetailDto;
        }
        boardDetailDto.setReplyInfo(replies);
        return boardDetailDto;
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
