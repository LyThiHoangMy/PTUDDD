package com.example.cva.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cva.Adapter.PitchAdapter;
import com.example.cva.Model.PitchModel;
import com.example.cva.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PitchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView rcvPitch;
    PitchAdapter adapter;

    public PitchFragment() {

    }

    public static PitchFragment newInstance(String param1, String param2) {
        PitchFragment fragment = new PitchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pitch, container, false);
        rcvPitch = (RecyclerView) view.findViewById(R.id.rcvPitchFrag);
        rcvPitch.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<PitchModel> options =
                new FirebaseRecyclerOptions.Builder<PitchModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pitch"),PitchModel.class)
                        .build();

        adapter = new PitchAdapter(options);
        rcvPitch.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Pitch");
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}