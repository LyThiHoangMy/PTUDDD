package com.example.cva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cva.Adapter.CategoryAdapter;
import com.example.cva.Adapter.PopularAdapter;
import com.example.cva.Model.CategoryModel;
import com.example.cva.Model.PitchModel;
import com.example.cva.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView mailUser;

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView rcvCategory, rcvPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mailUser = findViewById(R.id.tvHelloUser);
        mailUser.setText("Hi " + currentUser.getEmail());

        rcvCategory();
        rcvPopular();
    }

    private void rcvPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvPopular = findViewById(R.id.rcvPopular);
        rcvPopular.setLayoutManager(linearLayoutManager);

        ArrayList<PitchModel> pitchList = new ArrayList<>();
        pitchList.add(new PitchModel("SB501","pitch5","Capacity: 5 people",80.000, 5, 20));
        pitchList.add(new PitchModel("SB502","pitch5","Capacity: 5 people",80.000, 4, 18));
        pitchList.add(new PitchModel("SB503","pitch5","Capacity: 5 people",80.000, 3, 16));
        pitchList.add(new PitchModel("SB701","pitch7","Capacity: 7 people",100.000, 2, 14));
        pitchList.add(new PitchModel("SB111","pitch11","Capacity: 11 people",120.000, 1, 12));

        adapter2 = new PopularAdapter(pitchList);
        rcvPopular.setAdapter(adapter2);
    }

    private void rcvCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCategory = findViewById(R.id.recyclerCategory);
        rcvCategory.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("Pitch 5", "filter_5"));
        categoryList.add(new CategoryModel("Pitch 7", "filter_7"));
        categoryList.add(new CategoryModel("Pitch 11", "filter_11"));

        adapter = new CategoryAdapter(categoryList);
        rcvCategory.setAdapter(adapter);
    }
}