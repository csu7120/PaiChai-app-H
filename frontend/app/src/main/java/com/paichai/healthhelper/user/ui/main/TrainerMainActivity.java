// TrainerMainActivity.java
package com.paichai.healthhelper.user.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.user.api.ApiClient;
import com.paichai.healthhelper.user.api.UserApi;
import com.paichai.healthhelper.user.model.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerMainActivity extends AppCompatActivity {
    private UserApi userApi;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main);

        tvName = findViewById(R.id.tvTrainerName);
        userApi = ApiClient.getUserApi();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = prefs.getString("TRAINER_NAME", null);

        if (name != null) {
            tvName.setText(name);
        } else {
            // 저장된 값이 없으면 서버에서 프로필 조회
            String token = prefs.getString("AUTH_TOKEN", null);
            if (token != null) {
                fetchProfileAndSave(token);
            } else {
                tvName.setText("이름 없음");
            }
        }
    }

    private void fetchProfileAndSave(String token) {
        userApi.getProfile("Bearer " + token)
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> res) {
                        if (res.isSuccessful() && res.body() != null) {
                            String name = res.body().getName();

                            tvName.setText(name);

                            getSharedPreferences("prefs", MODE_PRIVATE)
                                    .edit()
                                    .putString("TRAINER_NAME", name)
                                    .apply();
                        } else {
                            Log.e("TrainerMain", "프로필 조회 실패: " + res.code());
                            tvName.setText("이름 없음");
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {
                        Log.e("TrainerMain", "프로필 네트워크 오류", t);
                        tvName.setText("이름 없음");
                    }
                });
    }
}
