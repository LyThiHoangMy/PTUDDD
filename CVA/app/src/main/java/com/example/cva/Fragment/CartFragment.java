package com.example.cva.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cva.Adapter.BillAdapter;
import com.example.cva.Model.Bill;
import com.example.cva.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mView;

    private RecyclerView rcvListBill;
    private RecyclerView.Adapter adapterBill;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        rcvListBill = mView.findViewById(R.id.rcvListBill);

        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("20/12/2023","SB111","120.000"));
        bills.add(new Bill("21/12/2023","SB501","80.000"));
        bills.add(new Bill("24/12/2023","SB701","100.000"));

        adapterBill = new BillAdapter(bills);
        rcvListBill.setAdapter(adapterBill);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvListBill.setLayoutManager(linearLayoutManager);

        return mView;
    }
}