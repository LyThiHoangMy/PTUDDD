package com.example.cva.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cva.Adapter.CategoryAdapter;
import com.example.cva.Adapter.PitchAdapter;
import com.example.cva.Model.CategoryModel;
import com.example.cva.Model.PitchModel;
import com.example.cva.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView mailUser;
    CircleImageView avatar;

    View mView;

    private RecyclerView rcvCategory, rcvPopular;
    private RecyclerView.Adapter adapter, adapter2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        mailUser = mView.findViewById(R.id.tvHelloUser);
        mailUser.setText("Hi " + currentUser.getEmail());
        avatar = mView.findViewById(R.id.avatar);

        rcvCategory = mView.findViewById(R.id.recyclerCategory);
        rcvPopular = mView.findViewById(R.id.rcvPopular);

        rcvCategory();
        rcvPopular();

        return mView;
    }

    private void rcvPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvPopular.setLayoutManager(linearLayoutManager);

        ArrayList<PitchModel> pitchList = new ArrayList<>();
        pitchList.add(new PitchModel("SB501","pitch5","Name: Pitch 501 (5 person). Capacity: 5 people. Date: 18/12/2022. Time: 10:00 - 11:00","4.5", "80.000"));
        pitchList.add(new PitchModel("SB502","pitch5","Name: Pitch 501 (5 person). Capacity: 5 people. Date: 18/12/2022. Time: 08:50 - 09:50","4.3", "80.000"));
        pitchList.add(new PitchModel("SB503","pitch5","Name: Pitch 501 (5 person). Capacity: 5 people. Date: 19/12/2022. Time: 10:00 - 11:00","4.0", "80.000"));
        pitchList.add(new PitchModel("SB701","pitch7","Name: Pitch 701 (7 person). Capacity: 7 people. Date: 20/12/2022. Time: 08:50 - 09:50","4.0", "100.000"));
        pitchList.add(new PitchModel("SB111","pitch11","Name: Pitch 111 (11 person). Capacity: 11 people. Date: 16/12/2022. Time: 06:30 - 07:30","4.5", "120.000"));

        adapter2 = new PitchAdapter(pitchList);
        rcvPopular.setAdapter(adapter2);
    }

    private void rcvCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("Pitch 5", "filter_5"));
        categoryList.add(new CategoryModel("Pitch 7", "filter_7"));
        categoryList.add(new CategoryModel("Pitch 11", "filter_11"));

        adapter = new CategoryAdapter(categoryList);
        rcvCategory.setAdapter(adapter);
    }
}