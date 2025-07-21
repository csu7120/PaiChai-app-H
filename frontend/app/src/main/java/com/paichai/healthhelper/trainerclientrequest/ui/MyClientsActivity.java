package com.paichai.healthhelper.trainerclientrequest.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.trainerclientrequest.adapter.MyClientAdapter;
import com.paichai.healthhelper.trainerclientrequest.api.TrainerClientRequestApi;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequestResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyClientsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyClientAdapter adapter;
    List<TrainerClientRequestResponse> myClients = new ArrayList<>();
    int trainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);

        trainerId = getIntent().getIntExtra("trainerId", -1);

        recyclerView = findViewById(R.id.recyclerViewClients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyClientAdapter(myClients, trainerId);
        recyclerView.setAdapter(adapter);

        loadMyClients(trainerId);
    }

    private void loadMyClients(int trainerId) {
        TrainerClientRequestApi api = ApiClient.getTrainerClientRequestApi(this);
        Call<List<TrainerClientRequestResponse>> call = api.getAcceptedClients(trainerId);

        call.enqueue(new Callback<List<TrainerClientRequestResponse>>() {
            @Override
            public void onResponse(Call<List<TrainerClientRequestResponse>> call, Response<List<TrainerClientRequestResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    myClients.clear();
                    myClients.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyClientsActivity.this, "불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TrainerClientRequestResponse>> call, Throwable t) {
                Toast.makeText(MyClientsActivity.this, "네트워크 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
