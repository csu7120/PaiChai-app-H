package com.paichai.healthhelper.trainerclientrequest.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.trainerclientrequest.adapter.TrainerRequestAdapter;
import com.paichai.healthhelper.trainerclientrequest.api.TrainerClientRequestApi;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerRequestListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainerRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_request_list);

        int trainerId = getIntent().getIntExtra("trainerId", -1);
        Log.d("TrainerMain", "trainerId = " + trainerId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TrainerClientRequestApi api = ApiClient.getInstance(this).create(TrainerClientRequestApi.class);
        api.getPendingRequests(trainerId).enqueue(new Callback<List<TrainerClientRequest>>() {
            @Override
            public void onResponse(Call<List<TrainerClientRequest>> call, Response<List<TrainerClientRequest>> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null || response.body().isEmpty()) { // 테스트용 : 요청이 하나도 없을 때
                        Toast.makeText(getApplicationContext(), "요청이 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new TrainerRequestAdapter(response.body());
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "응답 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TrainerClientRequest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
