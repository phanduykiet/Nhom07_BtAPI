package com.example.OtpAuthApi.dto;

public class UserDto {
    private String email;
    private String password;

    // Constructor không tham số
    public UserDto() {}

    // Constructor có tham số (Optional)
    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter và Setter cho email và password
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
