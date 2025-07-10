package com.paichai.healthhelper.user.api;

import com.paichai.healthhelper.user.model.LoginRequest;
import com.paichai.healthhelper.user.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/api/users/login")
    Call<LoginResponse> login(@Body LoginRequest req);

}
