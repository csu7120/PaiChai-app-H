package com.paichai.healthhelper.trainerclientrequest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.Toast;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.ptsession.ui.PtRegisterActivity;
import com.paichai.healthhelper.trainerclientrequest.api.TrainerClientRequestApi;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequest;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequestStatusUpdateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerRequestAdapter extends RecyclerView.Adapter<TrainerRequestAdapter.RequestViewHolder> {
    // List, Array의 데이터를 가져와서 인덱스마다 하나의 데이터로 변환
    private List<TrainerClientRequest> requestList;
    private int trainerId;

    public TrainerRequestAdapter(List<TrainerClientRequest> requestList, int trainerId) {
        this.requestList = requestList;
        this.trainerId   = trainerId;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 하나의 아이템 객체화
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trainer_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        // 데이터 값을 화면에 붙이는 역할

        TrainerClientRequest request = requestList.get(position);
        holder.tvClientName.setText(request.getClientName());
        holder.tvRequestStatus.setText(request.getStatus());

        // 수락 버튼
        holder.btnAccept.setOnClickListener(v -> {
            Context ctx = v.getContext();
            Intent intent = new Intent(ctx, PtRegisterActivity.class);
            intent.putExtra("trainerId", trainerId);          // 어댑터 필드
            intent.putExtra("clientId",  request.getClientId()); // 요청 DTO 에서 clientId 꺼내기
            intent.putExtra("requestId",  request.getRequestId());
            ctx.startActivity(intent);
        });

        // 거절 버튼
        holder.btnReject.setOnClickListener(v -> {
            updateStatus(holder.itemView.getContext(), request.getRequestId(), "REJECTED", position);
        });
    }
    
    @Override
    public int getItemCount() { // 객체화 된 아이템 개수 카운트
        return requestList != null ? requestList.size() : 0;
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvClientName, tvRequestStatus;
        Button btnAccept, btnReject;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvRequestStatus = itemView.findViewById(R.id.tvRequestStatus);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnReject = itemView.findViewById(R.id.btnReject);
        }
    }

    private void updateStatus(Context context, int requestId, String status, int position) {
        TrainerClientRequestApi api = ApiClient.getInstance(context).create(TrainerClientRequestApi.class);
        TrainerClientRequestStatusUpdateRequest dto = new TrainerClientRequestStatusUpdateRequest(status);

        api.updateRequestStatus(requestId, dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "요청이 처리되었습니다.", Toast.LENGTH_SHORT).show();
                    requestList.get(position).setStatus(status);
                    notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "처리에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
