package com.myfirstapplication.kpop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class HomeActivety extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activety);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

    }
}
