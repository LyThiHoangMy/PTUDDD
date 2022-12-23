package com.example.cva.Adapter;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cva.Model.notification;
import com.example.cva.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    List<notification> listItem;

    public NotificationAdapter(List<notification> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        holder.nameUser.setText(listItem.get(position).getNameUser());
        holder.time.setText(listItem.get(position).getTime());
        holder.date.setText(listItem.get(position).getDate());
        holder.namePitch.setText(listItem.get(position).getNamePitch());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView nameUser, time, date, namePitch;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.name_userNotification);
            time = itemView.findViewById(R.id.time_notification);
            date = itemView.findViewById(R.id.date_notification);
            namePitch = itemView.findViewById(R.id.pitch_notification);
        }

    }
}
