package com.paichai.healthhelper.user.api;

import com.paichai.healthhelper.user.model.LoginRequest;
import com.paichai.healthhelper.user.model.LoginResponse;
import com.paichai.healthhelper.user.model.ProfileResponse;
import com.paichai.healthhelper.user.model.UserRequest;

import okhttp3.ResponseBody;
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

    // 회원가입
    @POST("/api/users/register")
    Call<ResponseBody> register(@Body UserRequest req);
}
