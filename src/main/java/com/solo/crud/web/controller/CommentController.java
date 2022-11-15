package com.solo.crud.web.controller;

import com.solo.crud.domain.Comment;
import com.solo.crud.domain.User;
import com.solo.crud.web.argumentResolver.Login;
import com.solo.crud.web.controller.DTO.CommentDTO;
import com.solo.crud.web.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody @Validated CommentDTO.commentCreateRequest request, BindingResult bindingResult,
                                           @Login User user) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값을 확인해주세요");
            return ResponseEntity.badRequest().body(response);
        }

        if(user == null){
            response.put("result", "로그인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        //TODO: 박싱? 래핑? 이게 맞나?
        CommentDTO.commentCreateResponse commentResponse = commentService.save(request, user);

        response.put("result", "성공");
        response.put("commentCreateResponse", commentResponse);

        return ResponseEntity.ok().body(response);
    }

}
