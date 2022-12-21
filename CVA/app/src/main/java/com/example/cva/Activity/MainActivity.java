package com.example.cva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cva.Adapter.ViewPagerAdapter;
import com.example.cva.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);

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
}