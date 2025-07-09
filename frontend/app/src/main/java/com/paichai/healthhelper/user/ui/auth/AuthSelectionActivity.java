package com.paichai.healthhelper.user.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.user.ui.login.LoginActivity;
import com.paichai.healthhelper.user.ui.signup.SignupActivity;

public class AuthSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_selection);

        Button btnLogin = findViewById(R.id.btnGoLogin);
        Button btnSignup = findViewById(R.id.btnGoSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthSelectionActivity.this, LoginActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthSelectionActivity.this, SignupActivity.class));
            }
        });
    }
}
