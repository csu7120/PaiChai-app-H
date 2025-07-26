package com.paichai.healthhelper.user.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.paichai.healthhelper.R;
import com.paichai.healthhelper.common.api.ApiClient;
import com.paichai.healthhelper.user.api.UserApi;
import com.paichai.healthhelper.user.model.UserRequest;
import com.paichai.healthhelper.user.model.UserRequest;
import com.paichai.healthhelper.user.ui.login.LoginActivity;



import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText editEmail, editPassword, editName, editPhone;
    private RadioGroup radioGroupRole;
    private Button btnSignup;

    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        // 1. 레이아웃 연결
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        radioGroupRole = findViewById(R.id.radioGroupRole);
        btnSignup = findViewById(R.id.btnSignup);

        // 2. API Client 초기화
        userApi = ApiClient.getUserApi(SignupActivity.this);

        // 3. 회원가입 버튼 클릭 이벤트
        btnSignup.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        // 선택된 권한 가져오기
        String roleId = (radioGroupRole.getCheckedRadioButtonId() == R.id.radioTrainer) ? "TRAINER" : "USER";

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRequest request = new UserRequest(email, password, name, phone, roleId);

        userApi.register(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String msg = response.body().string();
                        Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(SignupActivity.this, "응답 파싱 오류", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "회원가입 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "서버 에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
