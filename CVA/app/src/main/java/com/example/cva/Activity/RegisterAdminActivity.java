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
import android.widget.ImageView;
import android.widget.ProgressBar;
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
                Intent login = new Intent(RegisterAdminActivity.this, LoginActivity.class);
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
                    //phòng trường hợp thông tin đăng ký không hợp lệ thì sẽ hiện ra lỗi để ng dùng biết họ không đăng ký được.
                    showMessage("Please complete all register information");
                    bt_register.setVisibility(View.VISIBLE);
                }
                else{
                    //trường hợp mọi thứ đều ok và bắt đầu tạo tài khoản cho người dùng.
                    CreateUserAccount(email, name, password1);
                }
            }
        });

    }

    private void CreateUserAccount(String email, String name, String password1) {
        //hàm tạo tài khoản với chỉ mỗi email và password
        mAuth.createUserWithEmailAndPassword(email,password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //nếu tạo tài khoản thành công
                    showMessage("Account of user create successfully!");
                    //sau khi tạo tài khoản thành công thì cần update infor lên database
                    updateUserInfo(name, mAuth.getCurrentUser());
                    

                }
                else{
                    //nếu tạo tài khoản false
                    showMessage("Account of user create failed!"+ task.getException().getMessage());
                    bt_register.setVisibility(View.VISIBLE);
                }
            }
        });
    }
//update user name
    private void updateUserInfo(String name, FirebaseUser currentUser) {

    }

    private void showMessage(String please_complete_all_register_information) {

    }
}