package com.solo.crud.web.controller.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @Data
    public static class UserSignUpRequest{

        @NotBlank
        private String user_id;
        @NotBlank
        private String user_pw;
        @NotBlank
        private String user_nickname;

    }

    @Data
    public static class UserLoginRequest{

        @NotBlank
        private String user_id;
        @NotBlank
        private String user_pw;

    }

    @Data
    public static class UserInfoChangeRequest{

        @NotBlank
        private String user_pw;
        @NotBlank
        private String user_nickname;

    }


}
