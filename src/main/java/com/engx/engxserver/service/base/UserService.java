package com.engx.engxserver.service.base;

import com.engx.engxserver.entity.User;

public interface UserService {
  User getUserByEmail(String email);
}
