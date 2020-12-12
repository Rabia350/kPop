package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndiaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IndiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndiaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndiaFragment newInstance(String param1, String param2) {
        IndiaFragment fragment = new IndiaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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