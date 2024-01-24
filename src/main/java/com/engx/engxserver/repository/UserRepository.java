package com.engx.engxserver.repository;

import com.engx.engxserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into users (id, email, first_name, last_name, password, user_role) values (?1, ?2, ?3, ?4, ?5, ?6)",
            nativeQuery = true)
    void insertCSV(Long id, String email, String firstName, String lastName, String password, String role);
}
