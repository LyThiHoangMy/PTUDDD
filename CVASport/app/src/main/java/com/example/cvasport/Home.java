package com.example.cvasport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvasport.Common.Common;
import com.example.cvasport.Fragment.DrinkableFragment;
import com.example.cvasport.Fragment.PitchFragment;
import com.example.cvasport.Fragment.SignOutFragment;
import com.example.cvasport.Model.PitchList;
import com.example.cvasport.ViewHolder.PitchListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private static final int FRAGMENT_PITCH = 0;
    private static final int FRAGMENT_DRINKABLE = 1;
    private static final int FRAGMENT_SIGNOUT = 2;

    private int currentFragment = FRAGMENT_PITCH;

    FirebaseDatabase database;
    DatabaseReference pitchList;

    TextView txtUsername;

    RecyclerView recyclerView_home;
    RecyclerView.LayoutManager layoutManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        pitchList = database.getReference("Pitch");

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new PitchFragment());
        navigationView.getMenu().findItem(R.id.nav_pitch).setChecked(true);

        //Set name for user
        View headerView = navigationView.getHeaderView(0);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtUsername.setText(Common.currentAccount.getUsername());

        //Load pitchList
        recyclerView_home = (RecyclerView) findViewById(R.id.rcvPitch);
        recyclerView_home.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_home.setLayoutManager(layoutManager);

        //loadHome();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_pitch)
        {
            if(currentFragment != FRAGMENT_PITCH)
            {
                replaceFragment(new PitchFragment());
                currentFragment = FRAGMENT_PITCH;
            }
        }
        else if(id == R.id.nav_water)
        {
            if(currentFragment != FRAGMENT_DRINKABLE)
            {
                replaceFragment(new DrinkableFragment());
                currentFragment = FRAGMENT_DRINKABLE;
            }
        }
        else if(id == R.id.nav_signout)
        {
            if(currentFragment != FRAGMENT_SIGNOUT)
            {
                replaceFragment(new SignOutFragment());
                currentFragment = FRAGMENT_SIGNOUT;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_screen,fragment);
        transaction.commit();
    }
}
