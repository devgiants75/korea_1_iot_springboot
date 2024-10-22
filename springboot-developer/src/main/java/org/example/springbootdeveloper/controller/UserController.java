package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.UserLoginRequestDto;
import org.example.springbootdeveloper.dto.request.UserRequestDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
// final, non null 설정 된 필드를 필수 매개변수로 하는 생성자를 만드는 애너테이션
@RequiredArgsConstructor
public class UserController {

    // 1. 생성자 의존성 주입 방식
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    // 2. 필드 의존성 주입 방식
//    @Autowired
//    private UserService userService;

    // HTTP 메서드: POST
    // URI 경로: /signup
    // - 회원가입 로직: username, password, email
    @PostMapping("/signup")
    public ResponseDto<String> signup(@RequestBody UserRequestDto dto) {
        try {
            String result = userService.signup(dto);
            return ResponseDto.setSuccess("Signup successful", result);
        } catch(Exception e) {
            return ResponseDto.setFailed("Signup failed" + e.getMessage());
        }
    }

    // HTTP 메서드: POST
    // URI 경로: /login
    // - 로그인 로직: username, password
    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody UserLoginRequestDto dto) {
        try {
            String result = userService.login(dto);
            return ResponseDto.setSuccess("Login successful", result);
        } catch(Exception e) {
            return ResponseDto.setFailed("Login failed: " + e.getMessage());
        }
    }
}
