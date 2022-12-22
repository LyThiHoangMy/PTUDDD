package com.example.cva.Fragment;

import static com.example.cva.Activity.HomeAdminActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cva.Activity.LoginActivity;
import com.example.cva.Activity.MainActivity;
import com.example.cva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileFragment extends Fragment {
    private View mView;
    ImageView avatarProfile;
    EditText lastname, firstname, mail;
    TextView textLogout, btnEdit, btnChangePass;

    private Uri uri;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        initUi();
        mainActivity = (MainActivity) getActivity();
        setUserInformation();
        initListener();

        textLogout = mView.findViewById(R.id.textLogout);

        logOut();
        return mView;
    }

    private void initListener() {
        avatarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();
            }
        });
    }

    private void onClickRequestPermission() {
        if(mainActivity == null){
            return;
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        firstname.setText(user.getDisplayName());
        lastname.setText(user.getDisplayName());
        mail.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.avt).into(avatarProfile);
    }

    private void initUi() {
        avatarProfile = mView.findViewById(R.id.avatarProfile);
        firstname = mView.findViewById(R.id.firstname);
        lastname = mView.findViewById(R.id.lastname);
        mail = mView.findViewById(R.id.mail);
        btnEdit = mView.findViewById(R.id.btnEdit);
        btnChangePass = mView.findViewById(R.id.btnChangePass);

    }

    public void setBitmapImageView(Bitmap bitmapImageView){
        avatarProfile.setImageBitmap(bitmapImageView);
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }



    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String lastName = lastname.getText().toString().trim();
        String firstName = firstname.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(lastName).setDisplayName(firstName)
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"update profile success", Toast.LENGTH_SHORT).show();
                            //mainActivity.showUserInformation();
                        }
                    }
                });

    }


    private void logOut() {
        textLogout.setOnClickListener((view) ->{
            FirebaseAuth.getInstance().signOut();
            Intent login = new Intent(getActivity(), LoginActivity.class);
            startActivity(login);
            getActivity().finish();
        });
    }
}