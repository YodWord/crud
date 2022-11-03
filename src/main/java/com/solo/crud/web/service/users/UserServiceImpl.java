package com.solo.crud.web.service.users;

import com.solo.crud.web.controller.DTO.UserDTO;
import com.solo.crud.domain.User;
import com.solo.crud.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
//@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User save(UserDTO.UserSignUpRequest request) throws Exception {
        if(isDuplicate(request.getUser_id())) throw new Exception("이미 존재하는 아이디");
        User user = User.createUser(request);
        return userRepository.save(user);
    }

    @Override
    public User login(UserDTO.UserLoginRequest request) throws Exception {
        return userRepository.findByUserId(request.getUser_id())
                .filter(user -> user.getUserPw().equals(request.getUser_pw())).orElse(null);
    }

    @Override
    public User infoChange(UserDTO.UserInfoChangeRequest request, User user) throws Exception {
        User userChange = User.updateUser(request, user);
        return userRepository.save(userChange);
    }

    @Override
    public Boolean isDuplicate(String user_id) throws Exception {
        return userRepository.findByUserId(user_id).isPresent();
    }
}
