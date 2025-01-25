package com.example.OtpAuthApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.OtpAuthApi.entity.User;
import com.example.OtpAuthApi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(false); // Tài khoản chưa được kích hoạt
        userRepository.save(user);

        String otp = otpService.generateOtp(email);
        emailService.sendOtp(email, otp);
    }

    public boolean loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user.isActive(); // Kiểm tra tài khoản đã kích hoạt hay chưa
        }
        return false;
    }

    public boolean activateAccount(String email, String otpCode) {
        if (otpService.verifyOtp(email, otpCode)) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setActive(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtp(email, otp);
    }

    public boolean updatePassword(String email, String otpCode, String newPassword) {
        if (otpService.verifyOtp(email, otpCode)) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
