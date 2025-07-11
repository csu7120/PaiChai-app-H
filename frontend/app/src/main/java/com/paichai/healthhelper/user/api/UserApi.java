package com.paichai.healthhelper.user.api;

import com.paichai.healthhelper.user.model.LoginRequest;
import com.paichai.healthhelper.user.model.LoginResponse;
import com.paichai.healthhelper.user.model.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApi {
    @POST("/api/users/login")
    Call<LoginResponse> login(@Body LoginRequest req);

    @GET("/api/users/me")
    Call<ProfileResponse> getProfile(@Header("Authorization") String bearerToken);
}
