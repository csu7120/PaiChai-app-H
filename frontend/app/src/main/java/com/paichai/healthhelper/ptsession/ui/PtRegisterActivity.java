package com.paichai.healthhelper.ptsession.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.ptsession.api.PtSessionPackageApi;
import com.paichai.healthhelper.ptsession.model.PtSessionPackageRequest;
import com.paichai.healthhelper.trainerclientrequest.api.TrainerClientRequestApi;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequestStatusUpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PtRegisterActivity extends AppCompatActivity {

    private EditText etTotalSessions, etStartDate, etEndDate;
    private Button btnRegister;

    private int trainerId;
    private int clientId;
    private int requestId; //

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_register);

        etTotalSessions = findViewById(R.id.etTotalSessions);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        btnRegister = findViewById(R.id.btnRegisterPackage);

        trainerId = getIntent().getIntExtra("trainerId", -1);
        clientId = getIntent().getIntExtra("clientId", -1);
        requestId = getIntent().getIntExtra("requestId", -1);

        btnRegister.setOnClickListener(v -> registerPackage());
    }

    private void registerPackage() {
        int totalCount = Integer.parseInt(etTotalSessions.getText().toString());
        String start  = etStartDate.getText().toString();
        String end    = etEndDate.getText().toString();

        // 1) PT 패키지 등록 API
        PtSessionPackageRequest reqPkg = new PtSessionPackageRequest(
                trainerId, clientId, totalCount, start, end
        );

        PtSessionPackageApi pkgApi = ApiClient.getInstance(this)
                .create(PtSessionPackageApi.class);

        pkgApi.register(reqPkg).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(PtRegisterActivity.this, "패키지 등록 실패", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PtRegisterActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();

                // 2) 요청 상태 ACCEPTED 로 업데이트 API 호출
                TrainerClientRequestApi reqApi = ApiClient
                        .getInstance(PtRegisterActivity.this)
                        .create(TrainerClientRequestApi.class);

                TrainerClientRequestStatusUpdateRequest statusReq =
                        new TrainerClientRequestStatusUpdateRequest("ACCEPTED");

                reqApi.updateRequestStatus(
                                (int) requestId,    // 1st 파라미터: int 타입으로 캐스트
                                statusReq           // 2nd 파라미터: DTO 인스턴스
                        )
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call2, Response<Void> resp2) {
                                if (resp2.isSuccessful()) {
                                    Toast.makeText(PtRegisterActivity.this,
                                            "요청 수락 완료", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PtRegisterActivity.this,
                                            "요청 처리 실패: " + resp2.message(),
                                            Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                            @Override
                            public void onFailure(Call<Void> call2, Throwable t2) {
                                Toast.makeText(PtRegisterActivity.this,
                                        "요청 처리 오류: " + t2.getMessage(),
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PtRegisterActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
