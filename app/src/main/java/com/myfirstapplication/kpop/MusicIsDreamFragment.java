package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MusicIsDreamFragment extends Fragment {

    TextView next;
    ViewPager viewPager;
    public MusicIsDreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_is_dream, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        next = view.findViewById(R.id.text3);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);

            }
        });
        return view;
    }
}
