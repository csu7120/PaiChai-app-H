package com.paichai.healthhelper.user.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.paichai.healthhelper.R;
import com.paichai.healthhelper.user.api.ApiClient;
import com.paichai.healthhelper.user.api.UserApi;
import com.paichai.healthhelper.user.model.LoginRequest;
import com.paichai.healthhelper.user.model.LoginResponse;
import com.paichai.healthhelper.user.ui.main.TrainerMainActivity;
import com.paichai.healthhelper.user.ui.main.UserMainActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);

        userApi = ApiClient.getUserApi();

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pw    = etPassword.getText().toString().trim();

            if (email.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // API 호출
            userApi.login(new LoginRequest(email, pw))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                String role = response.body().getRoleId();
                                String token = response.body().getToken();

                                // 토큰 저장 (예: SharedPreferences)
                                saveToken(token, role);

                                // role 에 따라 화면 분기
                                if ("TRAINER".equals(role)) {
                                    Intent intent = new Intent(LoginActivity.this, TrainerMainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                                    startActivity(intent);
                                }
                                finish();
                            } else {
                                showToast("로그인 실패: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,
                                    "통신 오류: " + t.getMessage(),
                                    Toast.LENGTH_LONG).show();

                            // 2) Logcat에 출력
                            Log.e("LoginError", "네트워크 요청 실패", t);
                        }
                    });
        });
    }

    private void saveToken(String token, String role) {
        getSharedPreferences("prefs", MODE_PRIVATE)
                .edit()
                .putString("AUTH_TOKEN", token)
                .putString("ROLE_ID", role)
                .apply();
    }

    // 로그인 실패시 오류 메세지
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
