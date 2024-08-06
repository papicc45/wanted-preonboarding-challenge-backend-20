package com.wanted.marketapi.service;

import com.wanted.marketapi.domain.User;
import com.wanted.marketapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with loginId: " + loginId);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLoginId(), user.getPassword(), Collections.emptyList()
        );
    }

}
