package com.example.cvasport.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvasport.Model.Pitch;
import com.example.cvasport.R;
import com.example.cvasport.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private RecyclerView pitchList;
    private View PitchFragmentView;

    private DatabaseReference PitchRef;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PitchFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        PitchRef = FirebaseDatabase.getInstance().getReference().child("Pitch");

        pitchList = (RecyclerView) PitchFragmentView.findViewById(R.id.pitch_rv);
        pitchList.setLayoutManager(new LinearLayoutManager(getContext()));

        return PitchFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Pitch> options =
                new FirebaseRecyclerOptions.Builder<Pitch>()
                        .setQuery(PitchRef.child("Pitch"), Pitch.class)
                        .build();

        FirebaseRecyclerAdapter<Pitch, PitchViewHolder > adapter =
                new FirebaseRecyclerAdapter<Pitch, PitchViewHolder>(options) {
                    @NonNull
                    @Override
                    public PitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent, false);
                        PitchViewHolder holder = new PitchViewHolder(view);
                        return holder;
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull PitchViewHolder holder, int position, @NonNull Pitch model) {
                        holder.itemView.findViewById(R.id.bookPitch).setVisibility(View.VISIBLE);

                    }
                };
    }

    public class PitchViewHolder extends RecyclerView.ViewHolder{

        ImageView imPitch;
        TextView typePitch, pricePitch;
        Button bookPitch;

        public PitchViewHolder(@NonNull View itemView) {
            super(itemView);

            imPitch = itemView.findViewById(R.id.imagePitch);
            typePitch = itemView.findViewById(R.id.typePitch);
            pricePitch = itemView.findViewById(R.id.pricePitch);
            bookPitch = itemView.findViewById(R.id.bookPitch);
        }
    }
}