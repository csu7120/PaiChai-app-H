package com.paichai.healthhelper.trainerclientrequest.api;

import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequest;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequestStatusUpdateRequest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrainerClientRequestApi {
    @GET("/api/trainer-request")
    Call<List<TrainerClientRequest>> getPendingRequests(@Query("trainerId") int trainerId);

    @PATCH("/api/trainer-request/{requestId}")
    Call<Void> updateRequestStatus(
            @Path("requestId") int requestId,
            @Body TrainerClientRequestStatusUpdateRequest request
    );
}
