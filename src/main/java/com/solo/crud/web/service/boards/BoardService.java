package com.solo.crud.web.service.boards;

import com.solo.crud.domain.Board;
import com.solo.crud.domain.User;
import com.solo.crud.web.controller.DTO.BoardDTO;

public interface BoardService {

    public Board save (Board board) throws Exception;

    public Board modifyBoard(Long boardId, BoardDTO.modifyBoardRequest request, User user) throws Exception;

    public Board deleteBoard(Long boardId, User user)throws Exception;

}
