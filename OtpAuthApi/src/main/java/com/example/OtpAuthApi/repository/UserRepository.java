package com.example.OtpAuthApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OtpAuthApi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
