package com.example.cvasport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cvasport.Common.Common;
import com.example.cvasport.Model.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText inputPhone, inputPass;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Mapping();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_account = database.getReference("Account");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait ...");
                mDialog.show();

                table_account.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(inputPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Account account = snapshot.child(inputPhone.getText().toString()).getValue(Account.class);
                            if (account.getPassword().equals(inputPass.getText().toString())) {
                                {
                                    Toast.makeText(SignIn.this, "Welcome!!!", Toast.LENGTH_SHORT).show();
                                    Intent home = new Intent(SignIn.this, Home.class);
                                    Common.currentAccount = account;
                                    startActivity(home);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(SignIn.this, "Incorrect password!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Account not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void Mapping() {
        inputPhone = (EditText) findViewById(R.id.inputPhoneNumber);
        inputPass = (EditText) findViewById(R.id.inputPassword);
        btnSignIn = (Button) findViewById(R.id.btnLogIn);
    }
}