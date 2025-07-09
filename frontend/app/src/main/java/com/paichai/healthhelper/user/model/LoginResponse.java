package com.paichai.healthhelper.user.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    public String getToken() { return token; }
}
