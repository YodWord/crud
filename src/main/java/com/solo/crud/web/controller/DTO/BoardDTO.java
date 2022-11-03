package com.solo.crud.web.controller.DTO;

import com.solo.crud.domain.Board;
import com.solo.crud.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class BoardDTO {

    @Data
    public static class createBoardRequest{

        @NotBlank
        private String board_title;
        @NotBlank
        private String board_contents;

    }

    @Data
    public static class modifyBoardRequest{

        @NotBlank
        private String board_title;
        @NotBlank
        private String board_contents;

    }

    @Data
    public static class modifyBoardResponse{

        private Long board_id;
        private User user;
        private String board_title;
        private String board_contents;
        private Timestamp board_created_date;


        public static modifyBoardResponse modifyBoardResponse(Board mdfBoard){
            modifyBoardResponse board = new modifyBoardResponse();
            board.board_id = mdfBoard.getBoardNo();
            board.user = mdfBoard.getUser();
            board.board_title = mdfBoard.getBoardTitle();
            board.board_contents = mdfBoard.getBoardContents();
            board.board_created_date = mdfBoard.getBoardCreateDate();
            return board;
        }


    }


}
