package com.paichai.healthhelper.ptsession.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.ptsession.api.PtSessionPackageApi;
import com.paichai.healthhelper.ptsession.model.PtSessionPackageRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PtRegisterActivity extends AppCompatActivity {

    EditText etTotalCount, etStartDate, etEndDate;
    Button btnRegister;

    int trainerId;
    int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_register);

        etTotalCount = findViewById(R.id.etTotalCount);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        btnRegister = findViewById(R.id.btnRegisterPackage);

        trainerId = getIntent().getIntExtra("trainerId", -1);
        clientId = getIntent().getIntExtra("clientId", -1);

        btnRegister.setOnClickListener(v -> registerPackage());
    }

    private void registerPackage() {
        int totalCount = Integer.parseInt(etTotalCount.getText().toString());
        String start = etStartDate.getText().toString();
        String end = etEndDate.getText().toString();

        PtSessionPackageRequest request = new PtSessionPackageRequest(
                trainerId, clientId, totalCount, start, end
        );

        PtSessionPackageApi api = ApiClient.getInstance(this).create(PtSessionPackageApi.class);
        Call<Void> call = api.register(request);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(PtRegisterActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PtRegisterActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
