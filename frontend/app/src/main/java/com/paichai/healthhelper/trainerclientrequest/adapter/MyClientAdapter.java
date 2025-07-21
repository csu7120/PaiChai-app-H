package com.paichai.healthhelper.trainerclientrequest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paichai.healthhelper.R;
import com.paichai.healthhelper.trainerclientrequest.model.TrainerClientRequestResponse;

import java.util.List;

public class MyClientAdapter extends RecyclerView.Adapter<MyClientAdapter.ClientViewHolder> {

    private List<TrainerClientRequestResponse> clientList;

    public MyClientAdapter(List<TrainerClientRequestResponse> clientList) {
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_client, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        TrainerClientRequestResponse client = clientList.get(position);
        holder.tvName.setText(client.getClientName());

    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvClientName);

        }
    }
}
