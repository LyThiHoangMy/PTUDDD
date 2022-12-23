package com.example.cva.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cva.Adapter.NotificationAdapter;
import com.example.cva.Adapter.PitchAdAdapter;
import com.example.cva.Model.PitchModelAd;
import com.example.cva.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChangeAd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChangeAd extends Fragment {

    View mView;
    private RecyclerView rcvList;
    private RecyclerView.Adapter adapterList;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentChangeAd() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChangeAd.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChangeAd newInstance(String param1, String param2) {
        FragmentChangeAd fragment = new FragmentChangeAd();
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
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_change_ad, container, false);
        rcvList = mView.findViewById(R.id.rcvListPitch);

        List<PitchModelAd> list = new ArrayList<>();
        list.add(new PitchModelAd("Pitch 11 person", "150000","4.5","SB111"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","4.8","SB071"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","5","SB072"));
        list.add(new PitchModelAd("Pitch 5 person", "100000","4.7","SB051"));
        list.add(new PitchModelAd("Pitch 11 person", "150000","4.8","SB112"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","5","SB073"));
        list.add(new PitchModelAd("Pitch 5 person", "100000","5","SB052"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","4.9","SB074"));
        list.add(new PitchModelAd("Pitch 11 person", "150000","4.2","SB113"));
        list.add(new PitchModelAd("Pitch 11 person", "150000","4.8","SB114"));
        list.add(new PitchModelAd("Pitch 5 person", "100000","5","SB053"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","4.6","SB075"));
        list.add(new PitchModelAd("Pitch 5 person", "100000","4.5","SB054"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","4.9","SB076"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","4.1","SB077"));
        list.add(new PitchModelAd("Pitch 11 person", "150000","4.2","SB115"));
        list.add(new PitchModelAd("Pitch 11 person", "150000","5","SB116"));
        list.add(new PitchModelAd("Pitch 7 person", "120000","5","SB078"));

        adapterList = new PitchAdAdapter(list);
        rcvList.setAdapter(adapterList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvList.setLayoutManager(linearLayoutManager);
        return mView;
    }
}

