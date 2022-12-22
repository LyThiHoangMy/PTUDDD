package com.example.cva.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cva.Adapter.DAOPitch;
import com.example.cva.Model.PitchModelAd;
import com.example.cva.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FragmentHomeAd extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mView;

    EditText des_addPitch, name_addPitch, fee_addPitch, start_addPitch;
    Button bt_addPitch, btn_update, btn_remove;

    private DatabaseReference rootDatabaseRef;

    public FragmentHomeAd() {
        // Required empty public constructor
    }

    public static FragmentHomeAd newInstance(String param1, String param2) {
        FragmentHomeAd fragment = new FragmentHomeAd();
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
        mView = inflater.inflate(R.layout.fragment_home_ad, container, false);

       des_addPitch = mView.findViewById(R.id.des_addPitch);
       name_addPitch = mView.findViewById(R.id.name_addPitch);
       fee_addPitch = mView.findViewById(R.id.fee_addPitch);
       start_addPitch = mView.findViewById(R.id.start_addPitch);
       bt_addPitch = mView.findViewById(R.id.bt_addPitch);
       btn_update = mView.findViewById(R.id.btn_update);
       btn_remove = mView.findViewById(R.id.btn_remove);

       rootDatabaseRef = FirebaseDatabase.getInstance().getReference();

       addPitch();
       updatePitch();
       removePitch();

       return mView;
    }

    private void removePitch() {
        DAOPitch dao = new DAOPitch();
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.remove("-NJsxcHpgMwGRfjTj6m8").addOnSuccessListener(suc ->{
                    Toast.makeText(getActivity(), "Remove pitch successfully!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void updatePitch() {
        DAOPitch dao = new DAOPitch();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("des",des_addPitch.getText().toString());
                hashMap.put("name", name_addPitch.getText().toString());
                hashMap.put("fee",fee_addPitch.getText().toString());
                hashMap.put("star", start_addPitch.getText().toString());

                dao.update("-NJsxcHpgMwGRfjTj6m8", hashMap).addOnSuccessListener(suc ->{
                    Toast.makeText(getActivity(), "Update pitch successfully!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void addPitch() {
        DAOPitch dao = new DAOPitch();
        bt_addPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PitchModelAd pit = new PitchModelAd(des_addPitch.getText().toString(),
                        name_addPitch.getText().toString(),
                        fee_addPitch.getText().toString(),
                        start_addPitch.getText().toString());

                dao.add(pit).addOnSuccessListener(suc ->{
                    Toast.makeText(getActivity(), "Add new pitch successfully!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->{
                    Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}