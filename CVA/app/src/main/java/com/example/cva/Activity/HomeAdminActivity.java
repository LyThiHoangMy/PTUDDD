package com.example.cva.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toolbar;

import com.example.cva.Fragment.FragmentChangeAd;
import com.example.cva.Fragment.FragmentHomeAd;
import com.example.cva.Fragment.FragmentNewsAd;
import com.example.cva.Fragment.FragmentProfileAd;
import com.example.cva.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth mAuth;
    FirebaseUser currenUser;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        //updateNavHeader();
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation_view_admin);
        View headerView = navigation.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.name_admin_tool);
        TextView navEmail = headerView.findViewById(R.id.email_admin_tool);
        ImageView navImg = headerView.findViewById(R.id.img_admin_tool);

        navEmail.setText(currenUser.getEmail());

        //getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new PitchFragment()).commit();

        //replaceFragment(new FragmentHomeAdmin());
        //navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

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
                //replaceFragment(new FragmentHomeAdmin());
                //mCurrentFragment = FRAGMENT_HOME;
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new FragmentHomeAd()).commit();
        }else if (id == R.id.nav_profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameAdmin, new FragmentProfileAd()).commit();

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


//
//        private void replaceFragment(Fragment fragment){
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.content_frame, fragment);
//            transaction.commit();
//    }

}