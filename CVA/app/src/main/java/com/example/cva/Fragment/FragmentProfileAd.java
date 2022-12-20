package com.example.cva.Fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.cva.Activity.HomeAdminActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cva.Activity.HomeAdminActivity;
import com.example.cva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FragmentProfileAd extends Fragment {

    private View mView;
    ImageView img_avt_profile;
    EditText full_name_admin_profile, email_admin_profile;
    Button bt_update_admin_profile;

    Uri mUri;
    private HomeAdminActivity mHomeAdminActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_ad, container, false);

        initUi();
        mHomeAdminActivity = (HomeAdminActivity) getActivity();
        setAdminInformation();
        initListener();

        return mView;
    }

    private void initListener() {
        img_avt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

//        bt_update_admin_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickUpdateProfile();
//            }
//        });
    }

    private void onClickRequestPermission() {
        if(mHomeAdminActivity == null){
            return;
        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M) {
            mHomeAdminActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mHomeAdminActivity.openGallery();
        } else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permission, MY_REQUEST_CODE);
        }

    }

    private void setAdminInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        full_name_admin_profile.setText(user.getDisplayName());
        email_admin_profile.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.avt).into(img_avt_profile);

    }

    private void initUi() {
        img_avt_profile = mView.findViewById(R.id.img_avt_profile);
        full_name_admin_profile = mView.findViewById(R.id.full_name_admin_profile);
        email_admin_profile = mView.findViewById(R.id.email_admin_profile);
        bt_update_admin_profile = mView.findViewById(R.id.bt_update_admin_profile);
    }

    public void setBitmapImageView(Bitmap bitmapImageView){
        img_avt_profile.setImageBitmap(bitmapImageView);
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }


//    private void onClickUpdateProfile() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user == null){
//            return;
//        }
//        String full_name = full_name_admin_profile.getText().toString().trim();
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName(full_name)
//                .setPhotoUri(mUri)
//                .build();
//
//        user.updateProfile(profileUpdates)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getActivity(),"update profile success", Toast.LENGTH_SHORT).show();
//                            //mHomeAdminActivity.showUserInformation();
//                        }
//                    }
//                });
//    }

}