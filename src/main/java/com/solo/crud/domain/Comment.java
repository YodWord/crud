package com.solo.crud.domain;

import com.solo.crud.web.controller.DTO.CommentDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    private String commentContents;

    private Timestamp commentCreateDate;


    public static Comment createComment(CommentDTO.commentCreateRequest request, User user, Board board){
        Comment comment = new Comment();
        comment.user = user;
        comment.board = board;
        comment.commentContents = request.getComment_contents();
        comment.commentCreateDate = Timestamp.valueOf(LocalDateTime.now());

        return comment;
    }

    public static Comment modifyComment(Comment comment, CommentDTO.modifyCommentRequest request){
        comment.commentContents = request.getComment_contents();
        comment.commentCreateDate = Timestamp.valueOf(LocalDateTime.now());

        return comment;
    }


}
