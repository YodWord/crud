package com.solo.crud.domain;

import com.solo.crud.web.controller.DTO.UserDTO;
import lombok.*;


import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(length = 30, nullable = false)
    private String userId;
    @Column(length = 30, nullable = false)
    private String userPw;
    @Column(length = 30, nullable = false)
    private String userNickname;

    public static User createUser(UserDTO.UserSignUpRequest request){
        User user = new User();
        user.userId = request.getUser_id();
        user.userPw = request.getUser_pw();
        user.userNickname = request.getUser_nickname();
        return user;
    }

    public static User updateUser(UserDTO.UserInfoChangeRequest request, User user){
        user.userPw = request.getUser_pw();
        user.userNickname = request.getUser_nickname();
        return user;
    }

}
