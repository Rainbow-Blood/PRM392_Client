package com.example.prm392_client.model.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;

import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationHolder> {
    List<Invitation> list;

    public InvitationAdapter(List<Invitation> list){
        this.list = list;
    }
    @NonNull
    @Override
    public InvitationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invitation_request, parent, false);
        return new InvitationHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InvitationHolder holder, int position) {
        holder.tv_sender.setText(list.get(position).getFromID());
        holder.tv_content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InvitationHolder extends RecyclerView.ViewHolder {
        TextView tv_sender, tv_content;
        Button btn_accept, btn_reject;
        public InvitationHolder(@NonNull View v) {
            super(v);
            tv_sender = v.findViewById(R.id.tv_frq_sender);
            tv_content = v.findViewById(R.id.tv_frq_content);
            btn_accept = v.findViewById(R.id.frq_btn_accept);
            btn_reject = v.findViewById(R.id.frq_btn_reject);
        }
    }
}
