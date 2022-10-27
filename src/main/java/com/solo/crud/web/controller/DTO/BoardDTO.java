package com.solo.crud.web.controller.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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

}
