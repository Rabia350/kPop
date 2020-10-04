package com.myfirstapplication.kpop;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class introadapter extends FragmentPagerAdapter {
    public introadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MusicIsDreamFragment();
            case 1:
                return new MusicIsMeditatingFragment();
            case 2:
                return new MusicIsLifeFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
