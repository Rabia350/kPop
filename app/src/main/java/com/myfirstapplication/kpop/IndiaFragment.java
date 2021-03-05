package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class IndiaFragment extends Fragment {

    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_india, container, false);
        songList = view.findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Lag Jaa Gale", "Lata Mangeshkar", R.raw.lagjagaly));
        arrayList.add(new Music("Phir Bhi Tumko Chahunga", "Zamad Baig", R.raw.phirb));
        arrayList.add(new Music("Mashup", "Arijit Singh", R.raw.mashup));
        arrayList.add(new Music("Jo Tu Mera Humdard Hai", "Arijit Singh", R.raw.hamdard));
        arrayList.add(new Music("Zara zara behekta hai", "ADITYA BHARDWAJ", R.raw.zarazara));
        arrayList.add(new Music("ye jism hai to kya", "Ali Azmat", R.raw.yejism));


        adapter = new CustomMusicAdapter(getActivity(), R.layout.custom_music_item, arrayList);
        songList.setAdapter(adapter);

        return view;
    }
}