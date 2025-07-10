package com.paichai.healthhelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.paichai.healthhelper.user.ui.auth.AuthSelectionActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1) 앱 실행 시 무조건 로그인/회원가입 화면으로 이동
        startActivity(new Intent(this, AuthSelectionActivity.class));
        // 2) MainActivity 는 종료
        finish();
    }
}