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
    public Board save(BoardDTO.createBoardRequest request, User user) throws Exception {
        Board board = Board.createBoard(request, user);

        return boardRepository.save(board);
    }

    @Override
    public Board readBoardById(Long boardId) throws Exception {

        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isEmpty()) throw new Exception("개시글이 존재하지 않습니다.");

        return optionalBoard.get();
    }

    @Override
    public Board modifyBoard(Long boardId, BoardDTO.modifyBoardRequest request, User user) throws Exception {

        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isEmpty()) throw new Exception("개시글이 존재하지 않습니다.");

        Board board = optionalBoard.get();

        if(!board.getUser().getUserId().equals(user.getUserId())) throw new Exception("본인의 글이 아닙니다.");

        return boardRepository.save(Board.modifyBoard(board, request, user));
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
