package com.engx.engxserver.service.impl;

import org.springframework.stereotype.Service;

import com.engx.engxserver.entity.User;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.service.base.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User getUserByEmail(String email) {
    return userRepository.findByEmailAddress(email);
  }
}
