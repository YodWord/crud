package com.solo.crud.web.service.boards;

import com.solo.crud.domain.Board;
import com.solo.crud.domain.User;
import com.solo.crud.web.controller.DTO.BoardDTO;
import com.solo.crud.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Board save(Board board) throws Exception {
        return boardRepository.save(board);
    }

    @Override
    public Board modifyBoard(Long boardId, BoardDTO.modifyBoardRequest request, User user) throws Exception {


        return null;
    }

    @Override
    public Board deleteBoard(Long boardId, User user) throws Exception {
        Optional<Board> boardOptional = boardRepository.findById(boardId);

        if(boardOptional.isEmpty()) throw new Exception("개시글이 존재하지 않습니다.");

        Board board = boardOptional.get();

        if(!board.getUser().getUserId().equals(user.getUserId())) throw new Exception("본인의 글이 아닙니다.");

        boardRepository.deleteById(boardId);

        return board;
    }
}
