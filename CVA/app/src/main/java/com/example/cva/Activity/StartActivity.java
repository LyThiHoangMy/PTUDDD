package com.example.cva.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cva.R;

public class StartActivity extends AppCompatActivity {

    Button btnStart, btnStartAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStartAdmin = findViewById(R.id.btnStartAdmin);
        btnStartAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerAdmin = new Intent(StartActivity.this, RegisterAdminActivity.class);
                startActivity(registerAdmin);
            }
        });

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }
}