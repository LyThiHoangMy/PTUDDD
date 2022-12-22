package com.example.cva.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.cva.Adapter.ViewPagerAdapter;
import com.example.cva.Fragment.ProfileFragment;
import com.example.cva.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;

    //****************Profile Fragment****************
    private static final int REQUEST_PICK_PHOTO = 200;
    FirebaseAuth mAuth;
    FirebaseUser currenUser;
    public static final int MY_REQUEST_CODE = 10;
    final private ProfileFragment profileFragment = new ProfileFragment();

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== RESULT_OK){
                        Intent intent = result.getData();
                        if(intent == null){
                            return;
                        }
                        Uri mUri = intent.getData();
                        profileFragment.setUri(mUri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mUri );
                            profileFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            });

    //****************Profile Fragment****************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);

        mAuth = FirebaseAuth.getInstance();
        currenUser = mAuth.getCurrentUser();

        setUpViewPager();

        mNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId())
            {
                case R.id.button_home:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.button_profile:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.button_cart:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.button_support:
                    mViewPager.setCurrentItem(3);
                    break;
                case R.id.button_setting:
                    mViewPager.setCurrentItem(4);
                    break;
            }
            return true;
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.button_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.button_profile).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.button_cart).setChecked(true);
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.button_support).setChecked(true);
                        break;
                    case 4:
                        mNavigationView.getMenu().findItem(R.id.button_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    public void openGallery() {

        Intent intent;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else{
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));    }

    public void showUserInformation() {
    }
}