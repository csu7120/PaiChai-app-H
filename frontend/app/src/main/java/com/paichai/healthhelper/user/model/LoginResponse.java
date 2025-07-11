package com.paichai.healthhelper.user.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String token;
    private String roleId;
    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getRoleId() { return roleId; }

    public void setRoleId(String roleId) { this.roleId = roleId; }
}
