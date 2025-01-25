package com.example.OtpAuthApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OtpAuthApi.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByEmailAndOtpCode(String email, String otpCode);
}
