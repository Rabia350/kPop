package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class PakistanFragment extends Fragment {

    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pakistan, container, false);
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

        return view;
    }

}