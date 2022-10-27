package com.solo.crud.web.controller;

import com.solo.crud.web.SessionConst;
import com.solo.crud.web.argumentResolver.Login;
import com.solo.crud.web.controller.DTO.UserDTO;
import com.solo.crud.domain.User;
import com.solo.crud.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> signUp (@RequestBody @Validated UserDTO.UserSignUpRequest userSignUpRequest,
                                          BindingResult bindingResult) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.save(userSignUpRequest);

        response.put("result", "성공");
        response.put("user", user);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserDTO.UserLoginRequest userLoginRequest,
                                        BindingResult bindingResult,  HttpServletRequest request) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.login(userLoginRequest);

        if(user == null
                || !(user.getUserId().equals(userLoginRequest.getUser_id()) && user.getUserPw().equals(userLoginRequest.getUser_pw()))) {
            response.put("result", "아이디 비밀번호 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("result", "성공");
        response.put("user", user);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, user);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout (HttpServletRequest request){
        HttpSession session = request.getSession();

        if(session != null){
            session.invalidate();
        }
        HashMap <String, Object> response = new HashMap<>();
        response.put("result", "성공");

        return ResponseEntity.ok().body(response);
    }

    //TODO: 유저 정보 수정
    @PutMapping("/update")
    public ResponseEntity<?> infoChange(@RequestBody @Validated UserDTO.UserInfoChangeRequest request, BindingResult bindingResult,
                                        @Login User user) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        if(user == null){
            response.put("result", "로그인 필요");
            return ResponseEntity.status(401).body(response);
        }

        User userResponse = userService.infoChange(request, user);

        response.put("result","성공");
        response.put("user", userResponse);

        return ResponseEntity.ok().body(response);
    }

}
