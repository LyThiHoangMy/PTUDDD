package com.example.cvasport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cvasport.Model.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    EditText inputName, inputPhone, inputPass;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = (EditText) findViewById(R.id.inputName);
        inputPhone = (EditText) findViewById(R.id.inputPhoneNB);
        inputPass = (EditText) findViewById(R.id.inputPass);
        btnSignUp = (Button) findViewById(R.id.btnLogUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_account = database.getReference("Account");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait ...");
                mDialog.show();

                table_account.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(inputPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone number already register!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                            Account account = new Account(inputName.getText().toString(),inputPass.getText().toString());
                            table_account.child(inputPhone.getText().toString()).setValue(account);
                            Toast.makeText(SignUp.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}