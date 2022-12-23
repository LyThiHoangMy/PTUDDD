package com.example.cva.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cva.Fragment.FragmentChangeAd;
import com.example.cva.Model.PitchModelAd;
import com.example.cva.R;

import java.util.ArrayList;
import java.util.List;

public class PitchAdAdapter extends RecyclerView.Adapter<PitchAdAdapter.ViewHolder> {
    List<PitchModelAd> pitchModels;

    public PitchAdAdapter(List<PitchModelAd> pitchModels) {
        this.pitchModels = pitchModels;
    }

    @NonNull
    @Override
    public PitchAdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PitchAdAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listpitch_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.des.setText(pitchModels.get(position).getDes());
        holder.name.setText(pitchModels.get(position).getName());
        holder.fee.setText(pitchModels.get(position).getFee());
        holder.star.setText(pitchModels.get(position).getStar());

    }

    @Override
    public int getItemCount() {
        return pitchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView des, fee, star, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameList);
            des = itemView.findViewById(R.id.desList);
            star = itemView.findViewById(R.id.starList);
            fee = itemView.findViewById(R.id.feeList);

        }
    }
}

