package com.paichai.healthhelper.mypage.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.paichai.healthhelper.R;

public class MypageActivity extends AppCompatActivity {

    private TextView textName, textFollower;
    private ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        textName = findViewById(R.id.text_name);
        textFollower = findViewById(R.id.text_follower);
        imageProfile = findViewById(R.id.image_profile);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String name = prefs.getString("USER_NAME", "이름 없음");
        String profileUrl = prefs.getString("PROFILE_URL", null);
        int followerCount = prefs.getInt("FOLLOWER_COUNT", 0);

        textName.setText(name);
        textFollower.setText("팔로워: " + followerCount + "명");

        if (profileUrl != null) {
            Glide.with(this).load(profileUrl).into(imageProfile);
        }
    }
}
