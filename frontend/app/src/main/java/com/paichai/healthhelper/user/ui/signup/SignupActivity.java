package com.paichai.healthhelper.user.ui.signup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.paichai.healthhelper.R;
import com.paichai.healthhelper.user.api.ApiClient;
import com.paichai.healthhelper.user.api.UserApi;
import com.paichai.healthhelper.user.model.SignupRequest;
import com.paichai.healthhelper.user.model.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etName, etPhone;
    private Button btnSignup;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName     = findViewById(R.id.etName);
        etPhone    = findViewById(R.id.etPhone);
        btnSignup  = findViewById(R.id.btnSignup);

        userApi = ApiClient.getUserApi();

        btnSignup.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pw    = etPassword.getText().toString().trim();
            String name  = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (email.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호는 필수입니다", Toast.LENGTH_SHORT).show();
                return;
            }

            SignupRequest req = new SignupRequest(email, pw, name, phone);
            userApi.register(req)
                    .enqueue(new Callback<SignupResponse>() {
                        @Override
                        public void onResponse(Call<SignupResponse> call, Response<SignupResponse> res) {
                            if (res.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                finish(); // 가입 후 뒤로
                            } else {
                                Toast.makeText(SignupActivity.this, "실패: "+res.code(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<SignupResponse> call, Throwable t) {
                            Toast.makeText(SignupActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
