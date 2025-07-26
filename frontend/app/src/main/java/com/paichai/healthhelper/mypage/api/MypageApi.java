package com.paichai.healthhelper.mypage.api;

import com.paichai.healthhelper.mypage.model.InbodyResponse;
import com.paichai.healthhelper.mypage.model.MyReviewResponse;
import com.paichai.healthhelper.mypage.model.MypageProfileResponse;
import com.paichai.healthhelper.mypage.model.PtSessionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MypageApi {
    @GET("mypage/profile")
    Call<MypageProfileResponse> getProfile(@Header("Authorization") String token);

    @GET("mypage/inbody/latest")
    Call<InbodyResponse> getLatestInbody(@Header("Authorization") String token);

    @GET("mypage/ptsession/ongoing")
    Call<PtSessionResponse> getOngoingPt(@Header("Authorization") String token);

    @GET("mypage/myreviews")
    Call<List<MyReviewResponse>> getMyReviews(@Header("Authorization") String token);
}

