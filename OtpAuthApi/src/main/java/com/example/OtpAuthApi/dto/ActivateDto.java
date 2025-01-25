package com.example.OtpAuthApi.dto;

public class ActivateDto {
    private String email;
    private String otpCode;

    // Constructor không tham số
    public ActivateDto() {}

    // Constructor có tham số (Optional)
    public ActivateDto(String email, String otpCode) {
        this.email = email;
        this.otpCode = otpCode;
    }

    // Getter và Setter cho email và otpCode
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}
