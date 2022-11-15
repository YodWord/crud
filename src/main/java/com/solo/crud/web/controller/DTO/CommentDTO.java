package com.solo.crud.web.controller.DTO;

import com.solo.crud.domain.Board;
import com.solo.crud.domain.Comment;
import com.solo.crud.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CommentDTO {

    @Data
    public static class commentCreateRequest{

        @NotNull
        private Long board_id;
        @NotBlank
        private String comment_contents;

    }

    @Data
    public static class commentCreateResponse{

        private Long comment_id;
        private User user;
        private Board board;
        private String comment_contents;
        private Timestamp comment_create_date;

        public static commentCreateResponse response(Comment comment){
            commentCreateResponse cmr =  new commentCreateResponse();

            cmr.comment_id = comment.getCommentNo();
            cmr.user = comment.getUser();
            cmr.board = comment.getBoard();
            cmr.comment_contents = comment.getCommentContents();
            cmr.comment_create_date = comment.getCommentCreateDate();

            return cmr;
        }
    }
}
