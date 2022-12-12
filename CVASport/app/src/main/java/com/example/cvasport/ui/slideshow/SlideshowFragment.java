package com.example.cvasport.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cvasport.R;
import com.example.cvasport.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    public SlideshowFragment() {}

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }
}