package com.example.cva.Adapter;

import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cva.Model.PitchModel;
import com.example.cva.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PitchAdapter extends FirebaseRecyclerAdapter<PitchModel, PitchAdapter.PitchViewHolder> {

    private List<PitchModel> listPitch;
    private ClickAddPitch clickAddPitch;
    FirebaseDatabase database;

    public interface ClickAddPitch {
        void onClickAddPitch(ImageView imgAdd, PitchModel pitch);
    }

    public PitchAdapter(@NonNull FirebaseRecyclerOptions<PitchModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PitchViewHolder holder, int position, @NonNull PitchModel model) {
        holder.tvName.setText(model.getName());
        holder.tvPrice.setText(model.getPrice());
        holder.tvDate.setText(model.getDate());
        holder.tvTime.setText(model.getTime());
        holder.imgCheck.setSelected(true);
    }

    @NonNull
    @Override
    public PitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_pitch, parent, false);
        return new PitchViewHolder(view);
    }

    public class PitchViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgCheck;
        TextView tvName, tvPrice, tvDate, tvTime;

        public PitchViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCheck = itemView.findViewById(R.id.imgCheck);
            tvName = itemView.findViewById(R.id.tvPitchName);
            tvPrice = itemView.findViewById(R.id.tvPitchPrice);
            tvDate = itemView.findViewById(R.id.tvPitchDate);
            tvTime = itemView.findViewById(R.id.tvPitchTime);
        }
    }
}
