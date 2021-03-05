package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;



public class headsetFragment extends Fragment {


    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;
    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_headset, container, false);

        songList = view.findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Jab koi bat", "Atif Aslam", R.raw.jab));
        arrayList.add(new Music("Ap baithy hain", "Zamad Baig", R.raw.ap));
        arrayList.add(new Music("Baari", "Bilal Saeed & Momina Mustehsan", R.raw.baari));
        arrayList.add(new Music("Har Zulm Tera Yaad Hy", "Sajjad Ali", R.raw.harzulm));
        arrayList.add(new Music("Laila O Laila", "Ali Zafar & Urooj Fatima", R.raw.lailaolaila));
        arrayList.add(new Music("Tajdar e Haram", "Atif Aslam", R.raw.tajdareharam));
        arrayList.add(new Music("Pyar bhare do sharmile nain", "Mehdi Hassan", R.raw.pyarbare));
        arrayList.add(new Music("Akele Na Jana", "Ahmed Rushdi", R.raw.akelenajana));
        arrayList.add(new Music("Afreen Afreen", "rahat & Momina Mustehsan", R.raw.afreen));


        adapter = new CustomMusicAdapter(getActivity(), R.layout.custom_music_item, arrayList);
        songList.setAdapter(adapter);

        // get the reference of FrameLayout and TabLayout
        simpleFrameLayout = (FrameLayout) view.findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) view.findViewById(R.id.simpleTabLayout);
        // Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Pakistan"); // set the Text for the first Tab
        firstTab.setIcon(R.drawable.pakistan); // set an icon for the
        // first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout
        // Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("India"); // set the Text for the second Tab
        secondTab.setIcon(R.drawable.india); // set an icon for the second tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout
        // Create a new Tab named "Third"
        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("English"); // set the Text for the first Tab
        thirdTab.setIcon(R.drawable.english); // set an icon for the first tab
        tabLayout.addTab(thirdTab); // add  the tab at in the TabLayout
        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new PakistanFragment();
                        break;
                    case 1:
                        fragment = new IndiaFragment();
                        break;
                    case 2:
                        fragment = new englishFragment();
                        break;
                }
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
