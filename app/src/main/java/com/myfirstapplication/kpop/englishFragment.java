package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class englishFragment extends Fragment {


    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english, container, false);
        songList = view.findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Marshmello", "Anne-Marie", R.raw.marshmello));
        arrayList.add(new Music("Let me down slowly", "Alec Benjamin", R.raw.letmedown));
        arrayList.add(new Music("Pefect", "Ed Sheeran", R.raw.perfect));
        arrayList.add(new Music("Closer", "The Chainsmokers", R.raw.closer));
        arrayList.add(new Music("Señorita", "Shawn Mendes, Camila Cabello", R.raw.senorita));
        arrayList.add(new Music("Let Me Love You", "DJ Snake ft. Justin Bieber", R.raw.letmeloveyou));


        adapter = new CustomMusicAdapter(getActivity(), R.layout.custom_music_item, arrayList);
        songList.setAdapter(adapter);

        return view;
    }
}