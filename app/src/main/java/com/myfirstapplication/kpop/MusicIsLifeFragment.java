package com.myfirstapplication.kpop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MusicIsLifeFragment extends Fragment {

    TextView back;
    ViewPager viewPager;
    private Button button;
    public MusicIsLifeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_is_life, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        back = view.findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);

            }
        });

        button = view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicIsLifeFragment.this,BottomTabs.class);
                startActivity(intent);
            }
        });
        return view;

    }

}
