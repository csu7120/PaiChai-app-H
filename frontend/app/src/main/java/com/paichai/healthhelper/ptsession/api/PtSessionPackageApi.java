package com.paichai.healthhelper.ptsession.api;

import com.paichai.healthhelper.ptsession.model.PtSessionPackageRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PtSessionPackageApi {
    @POST("/api/pt-packages")
    Call<Void> register(@Body PtSessionPackageRequest request);
}
