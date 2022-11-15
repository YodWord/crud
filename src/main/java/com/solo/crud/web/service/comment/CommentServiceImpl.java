package com.solo.crud.web.service.comment;

import com.solo.crud.domain.Board;
import com.solo.crud.domain.Comment;
import com.solo.crud.domain.User;
import com.solo.crud.web.controller.DTO.CommentDTO;
import com.solo.crud.web.repository.BoardRepository;
import com.solo.crud.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public CommentDTO.commentCreateResponse save(CommentDTO.commentCreateRequest request, User user) throws Exception {
        Optional<Board> optionalBoard = boardRepository.findById(request.getBoard_id());

        if(optionalBoard.isEmpty()) throw new Exception("개시글이 존재하지 않습니다.");
        Board board = optionalBoard.get();

        Comment comment = Comment.createComment(request, user, board);
        commentRepository.save(comment);

        return CommentDTO.commentCreateResponse.response(comment);
    }
}
