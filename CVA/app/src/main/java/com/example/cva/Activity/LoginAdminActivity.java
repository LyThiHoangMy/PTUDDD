package com.example.cva.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAdminActivity extends AppCompatActivity {
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    private EditText email, login2;
    private Button bt_Login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        email = findViewById(R.id.email);
        login2 = findViewById(R.id.login2);
        bt_Login = findViewById(R.id.bt_Login);
        mAuth = FirebaseAuth.getInstance();

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_Login.setVisibility(View.INVISIBLE);

                final String mail = email.getText().toString();
                final String password = login2.getText().toString();
                if(mail.isEmpty()|| password.isEmpty()){
                    showMessage("Please complete all login information!");
                    bt_Login.setVisibility(View.VISIBLE);
                }
                else{
                    LogIn(mail, password);
                }
            }
        });
    }

    private void LogIn(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    bt_Login.setVisibility(View.INVISIBLE);
                    updateUI();
                }
                else{
                    showMessage(task.getException().getMessage());
                    bt_Login.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), HomeAdminActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(), "please_verify_all_field", Toast.LENGTH_SHORT).show();
    }

}