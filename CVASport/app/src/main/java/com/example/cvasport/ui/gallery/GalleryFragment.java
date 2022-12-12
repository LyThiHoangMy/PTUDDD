package com.example.cvasport.ui.gallery;

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
import com.example.cvasport.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private RecyclerView drinkableList;
    private View DrinkableFragmentView;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DrinkableFragmentView = inflater.inflate(R.layout.fragment_gallery, container, false);

        drinkableList = (RecyclerView) DrinkableFragmentView.findViewById(R.id.drinkable_rv);
        drinkableList.setLayoutManager(new LinearLayoutManager(getContext()));

        return DrinkableFragmentView;
    }
}