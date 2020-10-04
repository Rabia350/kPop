package com.myfirstapplication.kpop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomTabs extends AppCompatActivity {

    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        toolbar = getSupportActionBar();
        // load the store fragment by default
        assert toolbar != null;
        toolbar.setTitle("Home");
        loadFragment(new homeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    toolbar.setTitle("Shop");
                    fragment = new homeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.headset:
                    toolbar.setTitle("Headset");
                    fragment = new headsetFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.mic:
                    toolbar.setTitle("Mic");
                    fragment = new micFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.notification:
                    toolbar.setTitle("Notification");
                    fragment = new notificationFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.person:
                    toolbar.setTitle("Person");
                    fragment = new personFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.group:
                    toolbar.setTitle("Group");
                    fragment = new groupFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    }

