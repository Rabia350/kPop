package com.myfirstapplication.kpop;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.stfalcon.bottomtablayout.BottomTabLayout;

public class BottomTabs extends AppCompatActivity {

    BottomTabLayout bottomTabLayout;
    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);
        container = (FrameLayout)findViewById(R.id.container); //Container for fragments

        //Setup button tab layout
        bottomTabLayout = (BottomTabLayout) findViewById(R.id.bottomTabLayout);
        //set button text style
        bottomTabLayout.setButtonTextStyle(R.style.TabButtonTextStyle);
        // set buttons from menu resource
        bottomTabLayout.setItems(R.menu.navigation);
        //set on selected tab listener.
        bottomTabLayout.setListener(new BottomTabLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switchFragment(id);
            }
        });
        //set button that will be select on start activity
        bottomTabLayout.setSelectedTab(R.id.person);
        //enable indicator
        bottomTabLayout.setIndicatorVisible(false);
        //indicator height
//        bottomTabLayout.setIndicatorHeight(5);
        //indicator color
//        bottomTabLayout.setIndicatorColor(R.color.green);
        //indicator line color
//        bottomTabLayout.setIndicatorLineColor(R.color.dark);
    }
    /**
     * Show fragment in container
     * @param id Menu item res id
     */
    public void switchFragment(int id) {
        Fragment fragment = null;
        switch (id) {
//            case R.id.home:
//                fragment = new homeFragment();
//                break;
            case R.id.person:
                fragment =  new personFragment();
                break;
            case R.id.headset:
                fragment =  new headsetFragment();
                break;
            case R.id.mic:
                fragment =  new micFragment();
                break;
            case R.id.notification:
                fragment =  new notificationFragment();
                break;

        }
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }
    }
    @Override
    public void onBackPressed() {

    }

    }

