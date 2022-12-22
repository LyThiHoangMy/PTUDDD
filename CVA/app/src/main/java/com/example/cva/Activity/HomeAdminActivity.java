package com.example.cva.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.cva.Fragment.FragmentChangeAd;
import com.example.cva.Fragment.FragmentHomeAd;
import com.example.cva.Fragment.FragmentNewsAd;
import com.example.cva.Fragment.FragmentProfileAd;
import com.example.cva.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class HomeAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int REQUEST_PICK_PHOTO = 100;
    FirebaseAuth mAuth;
    FirebaseUser currenUser;
    private ImageView img_admin_tool;
    private TextView name_admin_tool, email_admin_tool;


    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CHANGE = 3;
    private static final int FRAGMENT_NEWS =2 ;
    private static final int FRAGMENT_PROFILE = 1;



    private DrawerLayout mDrawerLayout;
    public static final int MY_REQUEST_CODE = 10;
    final private FragmentProfileAd fragmentProfileAd = new FragmentProfileAd();

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== RESULT_OK){
                Intent intent = result.getData();
                if(intent == null){
                    return;
                }
                Uri uri = intent.getData();
                fragmentProfileAd.setUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri );
                    fragmentProfileAd.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        Toolbar toolbar = findViewById(R.id.toolbarAdmin);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currenUser = mAuth.getCurrentUser();

        mDrawerLayout = findViewById(R.id.drawerLayoutAdmin);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        //updateLayoutNavAdmin();
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation_view_admin);
        View headerView = navigation.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.name_admin_tool);
        TextView navEmail = headerView.findViewById(R.id.email_admin_tool);
        ImageView navImg = headerView.findViewById(R.id.img_admin_tool);
        navEmail.setText(currenUser.getEmail());

    }
    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        //updateLayoutNavAdmin();
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation_view_admin);
        View headerView = navigation.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.name_admin_tool);
        TextView navEmail = headerView.findViewById(R.id.email_admin_tool);
        ImageView navImg = headerView.findViewById(R.id.img_admin_tool);
        navEmail.setText(currenUser.getEmail());

        if(name == null){
            navName.setVisibility(View.GONE);
        }else{
            navName.setVisibility(View.VISIBLE);
            navName.setText(name);
        }
        navEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.avt).into(navImg);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayoutAdmin);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new FragmentHomeAd()).commit();
        }else if (id == R.id.nav_profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, fragmentProfileAd).commit();
        } else if (id == R.id.nav_news) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new FragmentNewsAd()).commit();

        } else if (id == R.id.nav_change) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new FragmentChangeAd()).commit();

        } else if (id == R.id.nav_logout) {
                FirebaseAuth.getInstance().signOut();
                Intent loginAdminActivity = new Intent(getApplicationContext(), LoginAdminActivity.class);
                startActivity(loginAdminActivity);
                finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
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


}