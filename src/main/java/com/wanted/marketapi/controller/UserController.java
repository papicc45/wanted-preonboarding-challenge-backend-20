package com.wanted.marketapi.controller;

import com.wanted.marketapi.dto.user.AuthenticationResponseDTO;
import com.wanted.marketapi.dto.user.UserRequestDTO;
import com.wanted.marketapi.dto.user.UserResponseDTO;
import com.wanted.marketapi.service.CustomUserDetailService;
import com.wanted.marketapi.service.UserService;
import com.wanted.marketapi.util.JwtTokenUtil;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequestDTO userRequestDTO) throws Exception{
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequestDTO.getLoginId(), userRequestDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Invalid credentials. ", e);
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(userRequestDTO.getLoginId());
        final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.insertUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }
}
