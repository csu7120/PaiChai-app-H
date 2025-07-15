package com.paichai.healthhelper.user.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @SerializedName("token")
    private String token;
    private String roleId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getRoleId() { return roleId; }

    public void setRoleId(String roleId) { this.roleId = roleId; }
}
