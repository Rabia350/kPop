package com.myfirstapplication.kpop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivety extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activety);

        viewPager = findViewById(R.id.viewPager);
        introadapter adapter = new introadapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    }

