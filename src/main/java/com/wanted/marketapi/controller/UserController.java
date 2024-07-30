package com.wanted.marketapi.controller;

import com.wanted.marketapi.dto.user.UserRequestDTO;
import com.wanted.marketapi.dto.user.UserResponseDTO;
import com.wanted.marketapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.insertUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }
}
