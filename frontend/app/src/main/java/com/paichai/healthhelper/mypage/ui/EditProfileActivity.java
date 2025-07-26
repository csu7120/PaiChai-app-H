//인바디 등록/내 정보 수정용 화면

package com.paichai.healthhelper.mypage.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.paichai.healthhelper.R;

public class EditProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toast.makeText(this, "내 정보 수정 화면입니다.", Toast.LENGTH_SHORT).show();

        // 추후 여기에 서버 데이터 조회 후 TextView, EditText 바인딩 가능
    }
}
