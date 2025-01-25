package com.example.OtpAuthApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OtpAuthApi.entity.Otp;
import com.example.OtpAuthApi.repository.OtpRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000)); // Tạo OTP 6 chữ số
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP hết hạn sau 5 phút
        otpRepository.save(otpEntity);
        return otp;
    }

    public boolean verifyOtp(String email, String otpCode) {
        Otp otpEntity = otpRepository.findByEmailAndOtpCode(email, otpCode);
        if (otpEntity != null && otpEntity.getExpiryTime().isAfter(LocalDateTime.now())) {
            otpRepository.delete(otpEntity); // Xóa OTP sau khi xác nhận thành công
            return true;
        }
        return false;
    }
}
