package com.paichai.healthhelper.user.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.mypage.ui.MypageActivity;
import com.paichai.healthhelper.mypage.ui.MypageFragment;
import com.paichai.healthhelper.user.api.UserApi;
import com.paichai.healthhelper.user.model.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMainActivity extends AppCompatActivity {

    private UserApi userApi;
    private TextView tvUserName;
    private Button btnSns, btnMyPage, btnExercise;
    private ImageView imgAdBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        tvUserName = findViewById(R.id.tvUserName);
        btnSns = findViewById(R.id.btnSns);
        btnMyPage = findViewById(R.id.btnMyPage);
        btnExercise = findViewById(R.id.btnExercise);
        imgAdBanner = findViewById(R.id.imgAdBanner);

        userApi = ApiClient.getUserApi(this);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = prefs.getString("USER_NAME", null);

        if (name != null) {
            tvUserName.setText(name);
        } else {
            String token = prefs.getString("AUTH_TOKEN", null);
            if (token != null) {
                fetchProfileAndSave(token);
            } else {
                tvUserName.setText("이름 없음");
            }
        }

        // 버튼 이벤트 추가
        btnSns.setOnClickListener(v ->
                Toast.makeText(this, "SNS 기능 준비 중입니다", Toast.LENGTH_SHORT).show());

        btnMyPage.setOnClickListener(v -> {
            Intent intent = new Intent(UserMainActivity.this, MypageActivity.class);
            startActivity(intent);
        });

        btnExercise.setOnClickListener(v ->
                Toast.makeText(this, "운동하기 페이지 준비 중입니다", Toast.LENGTH_SHORT).show());
    }

    private void fetchProfileAndSave(String token) {
        userApi.getProfile("Bearer " + token).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String name = response.body().getName();
                    tvUserName.setText(name);
                    getSharedPreferences("prefs", MODE_PRIVATE).edit()
                            .putString("USER_NAME", name)
                            .putInt("USER_ID", response.body().getUserId())
                            .apply();
                } else {
                    Log.e("UserMain", "프로필 조회 실패: " + response.code());
                    tvUserName.setText("이름 없음");
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("UserMain", "네트워크 오류", t);
                tvUserName.setText("이름 없음");
            }
        });
    }
}
