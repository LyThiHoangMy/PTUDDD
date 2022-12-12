package com.example.cvasport.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvasport.R;
import com.example.cvasport.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private RecyclerView pitchList;
    private View PitchFragmentView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PitchFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        pitchList = (RecyclerView) PitchFragmentView.findViewById(R.id.pitch_rv);
        pitchList.setLayoutManager(new LinearLayoutManager(getContext()));

        return PitchFragmentView;
    }
}