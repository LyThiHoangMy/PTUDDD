package com.example.cva.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cva.Adapter.BillAdapter;
import com.example.cva.Adapter.NotificationAdapter;
import com.example.cva.Model.Bill;
import com.example.cva.Model.notification;
import com.example.cva.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNewsAd extends Fragment {

    View mView;
    private RecyclerView crvListNotification;
    private RecyclerView.Adapter adapterNotification;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentNewsAd() {
        // Required empty public constructor
    }

    public static FragmentNewsAd newInstance(String param1, String param2) {
        FragmentNewsAd fragment = new FragmentNewsAd();
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
        mView = inflater.inflate(R.layout.fragment_news_ad, container, false);
        crvListNotification = mView.findViewById(R.id.rcvListNotification);


        List<notification> list = new ArrayList<>();
        list.add(new notification("Jeon Jungkook","10:00 - 12:00","12/12/2022 (thanh toán 50%)", "SB116"));
        list.add(new notification("Kim Taehyung","9:30 - 11:00","26/12/2022 (thanh toán 50%)", "SB015"));
        list.add(new notification("Kim Seokjin","10:00 - 12:00","26/12/2022 (thanh toán 100%)", "SB005"));
        list.add(new notification("Kim Namjoon","7:00 - 9:00","27/12/2022 (thanh toán 50%)", "SB106"));
        list.add(new notification("Jung Hoseok","10:00 - 12:00","27/12/2022 (thanh toán 50%)", "SB007"));
        list.add(new notification("Min Yoongi","10:00 - 12:00","27/12/2022 (thanh toán 100%)", "SB137"));
        list.add(new notification("Park Jimin","7:00 - 9:00","29/12/2022 (thanh toán 100%)", "SB011"));
        list.add(new notification("Ly Hoang My","10:00 - 12:00","3/1/2023 (thanh toán 50%)", "SB022"));
        list.add(new notification("Nguyen Thanh Hien","10:30 - 12:00","2/1/2023 (thanh toán 50%)", "SB009"));
        list.add(new notification("Lai Kuanlin","10:00 - 12:00","18/12/2022 (thanh toán 50%)", "SB116"));
        list.add(new notification("Tran Anh Dao","19:00 - 21:00","9/1/2023 (thanh toán 50%)", "SB117"));
        list.add(new notification("Nguyen My Ha","16:00 - 18:00","7/1/2022 (thanh toán 100%)", "SB006"));

        adapterNotification = new NotificationAdapter(list);
        crvListNotification.setAdapter(adapterNotification);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        crvListNotification.setLayoutManager(linearLayoutManager);

        return mView;

    }
}