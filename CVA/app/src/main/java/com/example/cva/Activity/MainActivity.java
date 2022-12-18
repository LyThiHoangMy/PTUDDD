package com.example.cva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cva.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        rcvListPitch();
    }

    private void rcvListPitch() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCategory = findViewById(R.id.recyclerCategory);
        rcvCategory.setLayoutManager(linearLayoutManager);
    }
}