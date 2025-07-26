package com.paichai.healthhelper.mypage.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.paichai.healthhelper.R;
import com.paichai.healthhelper.mypage.api.MypageApi;
import com.paichai.healthhelper.mypage.model.*;
import com.paichai.healthhelper.mypage.ui.adapter.ReviewAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageFragment extends Fragment {

    private ImageView imageProfile;
    private TextView textName, textFollower, textInbody, textPtSession;
    private RecyclerView recyclerReviews;
    private MypageApi mypageApi;
    private String token = "Bearer your_access_token";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mypage, container, false);

        // View 연결
        imageProfile = view.findViewById(R.id.image_profile);
        textName = view.findViewById(R.id.text_name);
        textFollower = view.findViewById(R.id.text_follower);
        textInbody = view.findViewById(R.id.text_inbody);
        textPtSession = view.findViewById(R.id.text_pt_session);
        recyclerReviews = view.findViewById(R.id.recycler_reviews);

        recyclerReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        // Retrofit 세팅
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // 로컬 Spring 서버로 요청 전송
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mypageApi = retrofit.create(MypageApi.class);

        loadProfile();
        loadInbody();
        loadPtSession();
        loadReviews();

        return view;
    }

    private void loadProfile() {
        mypageApi.getProfile(token).enqueue(new Callback<MypageProfileResponse>() {
            @Override
            public void onResponse(Call<MypageProfileResponse> call, Response<MypageProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MypageProfileResponse profile = response.body();
                    textName.setText(profile.getName());
                    textFollower.setText("팔로워: " + profile.getFollowerCount() + "명");
                    Glide.with(requireContext()).load(profile.getProfileUrl()).into(imageProfile);
                }
            }

            @Override
            public void onFailure(Call<MypageProfileResponse> call, Throwable t) {
                Toast.makeText(getContext(), "프로필 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadInbody() {
        mypageApi.getLatestInbody(token).enqueue(new Callback<InbodyResponse>() {
            @Override
            public void onResponse(Call<InbodyResponse> call, Response<InbodyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InbodyResponse inbody = response.body();
                    textInbody.setText("체중: " + inbody.getWeight() + "kg / 체지방: " + inbody.getFatRatio() + "%");
                }
            }

            @Override
            public void onFailure(Call<InbodyResponse> call, Throwable t) {
                Toast.makeText(getContext(), "인바디 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPtSession() {
        mypageApi.getOngoingPt(token).enqueue(new Callback<PtSessionResponse>() {
            @Override
            public void onResponse(Call<PtSessionResponse> call, Response<PtSessionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PtSessionResponse pt = response.body();
                    textPtSession.setText(pt.getTrainerName() + " 트레이너와 " + pt.getScheduledAt() + " 예약, 상태: " + pt.getStatus());
                }
            }

            @Override
            public void onFailure(Call<PtSessionResponse> call, Throwable t) {
                Toast.makeText(getContext(), "PT 세션 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadReviews() {
        mypageApi.getMyReviews(token).enqueue(new Callback<List<MyReviewResponse>>() {
            @Override
            public void onResponse(Call<List<MyReviewResponse>> call, Response<List<MyReviewResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MyReviewResponse> reviews = response.body();
                    ReviewAdapter adapter = new ReviewAdapter(reviews, getContext());
                    recyclerReviews.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyReviewResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "리뷰 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
