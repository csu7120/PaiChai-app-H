//내가 쓴 글/좋아요 목록 표시용 어댑터

package com.paichai.healthhelper.mypage.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.paichai.healthhelper.R;
import com.paichai.healthhelper.mypage.model.MyReviewResponse;
import com.paichai.healthhelper.mypage.ui.ReviewDetailActivity;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<MyReviewResponse> reviewList;
    private Context context;

    public ReviewAdapter(List<MyReviewResponse> reviewList, Context context) {
        this.reviewList = reviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyReviewResponse review = reviewList.get(position);

        holder.textTitle.setText(review.getTitle());
        holder.textComment.setText(review.getComment());
        holder.textRating.setText("평점: " + review.getRating() + "점");
        holder.textDate.setText("작성일: " + review.getReviewedAt());

        // 클릭 시 상세보기 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReviewDetailActivity.class);
            intent.putExtra("title", review.getTitle());
            intent.putExtra("comment", review.getComment());
            intent.putExtra("rating", review.getRating());
            intent.putExtra("date", review.getReviewedAt());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return reviewList != null ? reviewList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textComment, textRating, textDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_routine_title);
            textComment = itemView.findViewById(R.id.text_comment);
            textRating = itemView.findViewById(R.id.text_rating);
            textDate = itemView.findViewById(R.id.text_date);
        }
    }
}
