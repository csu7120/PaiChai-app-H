package com.paichai.healthhelper.user.model;

public class UserRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String roleId;

    public UserRequest(String email, String password, String name, String phone, String roleId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roleId = roleId;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getRoleId() { return roleId; }
}
