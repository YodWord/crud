package com.solo.crud.web.controller;

import com.solo.crud.web.argumentResolver.Login;
import com.solo.crud.web.controller.DTO.BoardDTO;
import com.solo.crud.domain.Board;
import com.solo.crud.domain.User;
import com.solo.crud.web.service.boards.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Object> createBoard(@RequestBody @Validated BoardDTO.createBoardRequest request,
                                              BindingResult bindingResult, @Login User user) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        Board board = Board.createBoard(request, user);
        boardService.save(board);

        response.put("result", "성공");
        response.put("board", board);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard (@PathVariable Long boardId, @RequestBody @Validated BoardDTO.modifyBoardRequest request,
                                          BindingResult bindingResult, @Login User user) throws Exception{
        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        if(user == null){
            response.put("result", "로그인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        return null;
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId, @Login User user) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        Board board = boardService.deleteBoard(boardId, user);

        response.put("result", "성공");
        response.put("board", board);

        return ResponseEntity.ok().body(response);
    }

}
