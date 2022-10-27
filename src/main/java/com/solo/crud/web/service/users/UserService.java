package com.solo.crud.web.service.users;

import com.solo.crud.web.controller.DTO.UserDTO;
import com.solo.crud.domain.User;

public interface UserService {

    public User save(UserDTO.UserSignUpRequest request) throws Exception;

    public User login(UserDTO.UserLoginRequest request) throws Exception;

    public User infoChange(UserDTO.UserInfoChangeRequest request, User user) throws Exception;

}
