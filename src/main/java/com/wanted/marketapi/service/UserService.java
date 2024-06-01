package com.wanted.marketapi.service;

import com.wanted.marketapi.domain.User;
import com.wanted.marketapi.dto.UserRequestDTO;
import com.wanted.marketapi.dto.UserResponseDTO;
import com.wanted.marketapi.mapper.UserMapper;
import com.wanted.marketapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO insertUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.save(userMapper.dtoToUser(userRequestDTO));
        return userMapper.userToDTO(user);
    }
}
