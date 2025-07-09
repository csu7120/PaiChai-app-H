package com.paichai.healthhelper.user.model;

public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    // 소셜 로그인 기능 추가할 때 profileUrl 추가

    public SignupRequest(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
    public SignupRequest() {}

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
}
