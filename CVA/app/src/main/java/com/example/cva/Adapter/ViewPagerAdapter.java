package com.example.cva.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cva.Fragment.CartFragment;
import com.example.cva.Fragment.HomeFragment;
import com.example.cva.Fragment.ProfileFragment;
import com.example.cva.Fragment.SettingFragment;
import com.example.cva.Fragment.SupportFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //final private ProfileFragment profileFragment = new ProfileFragment();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new ProfileFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new SupportFragment();
            case 4:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
