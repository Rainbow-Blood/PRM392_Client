package com.example.prm392_client.model.contact;

import static android.util.Log.WARN;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.ui.contacts.InvitationViewModel;

import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationHolder> {
    List<Invitation> list;
    final String memberId = "6914b4d60a6237d6c93a4c1d";
    private final InvitationViewModel viewModel;
    public InvitationAdapter(List<Invitation> list, InvitationViewModel viewModel){
        this.list = list;
        this.viewModel = viewModel;
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
        Log.d("BIND_DATA", "Item " + position +
                " Sender: " + list.get(position).getFromID() +
                ", Content: " + list.get(position).getContent());
//        holder.tv_sender.setText(list.get(position).getFromID());
        holder.tv_sender.setText("Lời mời kết bạn mới");
        holder.tv_content.setText(list.get(position).getContent());
        holder.btn_accept.setOnClickListener(v -> {
            InvitationDTO dto = new InvitationDTO(memberId, list.get(position).getFromID(), "");
            viewModel.rejectInvitation(dto);

            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        });

        holder.btn_reject.setOnClickListener(v -> {
            InvitationDTO dto = new InvitationDTO(memberId, list.get(position).getFromID(), "");

            viewModel.rejectInvitation(dto);

            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void updateData(List<Invitation> newInvitations) {
        this.list = newInvitations;
        notifyDataSetChanged();
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
