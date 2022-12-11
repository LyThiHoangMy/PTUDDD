package com.example.cvasport.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvasport.Interface.ItemClickListener;
import com.example.cvasport.R;

public class PitchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public PitchListViewHolder(View itemView) {
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.list_name);
        imageView = (ImageView) itemView.findViewById(R.id.list_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAbsoluteAdapterPosition(),false);
    }
}
