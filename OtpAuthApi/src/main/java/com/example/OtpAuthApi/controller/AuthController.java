package com.example.OtpAuthApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.OtpAuthApi.dto.ActivateDto;
import com.example.OtpAuthApi.dto.UserDto;
import com.example.OtpAuthApi.service.EmailService;
import com.example.OtpAuthApi.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // Đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userService.registerUser(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok("Registration successful. Please verify your email.");
    }


    // Đăng nhập người dùng
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        
        if (userService.loginUser(email, password)) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.badRequest().body("Invalid email or password.");
    }


    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody ActivateDto activateDto) {
        String email = activateDto.getEmail();
        String otpCode = activateDto.getOtpCode();

        if (userService.activateAccount(email, otpCode)) {
            return ResponseEntity.ok("Account activated successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid OTP or expired.");
    }


    // Quên mật khẩu
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok("OTP sent to your email.");
    }

    // Cập nhật mật khẩu mới
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otpCode, @RequestParam String newPassword) {
        if (userService.updatePassword(email, otpCode, newPassword)) {
            return ResponseEntity.ok("Password updated successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid OTP or expired.");
    }
}
