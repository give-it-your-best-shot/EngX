package com.engx.engxserver.service.impl;

import com.engx.engxserver.entity.User;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.security.CustomUserDetails;
import com.engx.engxserver.service.base.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException(Long.toString(userId));
        }
        return new CustomUserDetails(user.get());
    }
}
