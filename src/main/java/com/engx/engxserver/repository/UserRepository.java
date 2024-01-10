package com.engx.engxserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.engx.engxserver.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u where u.email = ?1")
  User findByEmailAddress(String email);
}
