package com.example.cva.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterAdminActivity extends AppCompatActivity {
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;

    private EditText usernameAd, emailadmin, pass1,pass2;
    private Button bt_register, bt_login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        usernameAd = findViewById(R.id.usernameAd);
        emailadmin = findViewById(R.id.emailadmin);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        bt_register = findViewById(R.id.bt_register);
        bt_login = findViewById(R.id.bt_login);

        mAuth = FirebaseAuth.getInstance();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterAdminActivity.this, LoginAdminActivity.class);
                startActivity(login);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bt_register.setVisibility(View.INVISIBLE);
                final String email = emailadmin.getText().toString();
                final String password1 = pass1.getText().toString();
                final String password2 = pass2.getText().toString();
                final String name = usernameAd.getText().toString();

                if(name.isEmpty()||email.isEmpty()||password1.isEmpty()||!password1.equals(password2)){
                    //ph??ng tr?????ng h???p th??ng tin ????ng k?? kh??ng h???p l???
                    // th?? s??? hi???n ra l???i ????? ng d??ng bi???t h??? kh??ng ????ng k?? ???????c.
                    showMessage("Please complete all register information");
                    bt_register.setVisibility(View.VISIBLE);
                }
                else{
                    //tr?????ng h???p m???i th??? ?????u ok v?? b???t ?????u t???o t??i kho???n cho ng?????i d??ng.
                    CreateUserAccount(email, name, password1);
                }
            }
        });

    }

    private void CreateUserAccount(String email, String name, String password1) {
        //h??m t???o t??i kho???n v???i ch??? m???i email v?? password
        mAuth.createUserWithEmailAndPassword(email,password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //n???u t???o t??i kho???n th??nh c??ng
                    showMessage("Account of user create successfully!");
                    //sau khi t???o t??i kho???n th??nh c??ng th?? c???n update infor l??n database
                    updateUserInfo(name, mAuth.getCurrentUser());
                }
                else{
                    //n???u t???o t??i kho???n false
                    showMessage("Account of user create failed!"+ task.getException().getMessage());
                    bt_register.setVisibility(View.VISIBLE);
                }
            }
        });
    }
//update user name
    private void updateUserInfo(String name, FirebaseUser currentUser) {
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("user_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            showMessage("Register successfully!");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), HomeAdminActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String please_complete_all_register_information) {
        Toast.makeText(getApplicationContext(), "please_verify_all_field", Toast.LENGTH_SHORT).show();

    }
}