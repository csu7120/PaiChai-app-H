package com.paichai.healthhelper.user.ui.login;

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
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> res) {
                            if (res.isSuccessful() && res.body() != null) {
                                String token = res.body().getToken();
                                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                // TODO: SharedPreferences 저장 → 다음 화면으로
                            } else {
                                // ErrorBody 에서 message 꺼내기
                                String errorMsg = "로그인 실패";
                                try {
                                    // errorBody 를 JSON 으로 파싱
                                    String errJson = res.errorBody().string();
                                    JSONObject obj = new JSONObject(errJson);
                                    if (obj.has("message")) {
                                        errorMsg = obj.getString("message");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,
                                    "통신 오류: " + t.getMessage(),
                                    Toast.LENGTH_LONG).show();

                            // 2) Logcat 에 전체 스택트레이스 출력
                            Log.e("LoginError", "네트워크 요청 실패", t);
                        }
                    });
        });
    }
}
