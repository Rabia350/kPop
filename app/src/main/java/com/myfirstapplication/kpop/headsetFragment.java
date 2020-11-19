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
 * Use the {@link headsetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class headsetFragment extends Fragment {
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

    public headsetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment headsetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static headsetFragment newInstance(String param1, String param2) {
        headsetFragment fragment = new headsetFragment();
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
        View view = inflater.inflate(R.layout.fragment_headset, container, false);
        songList = view.findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Jab koi bat", "Atif Aslam", R.raw.jab));
        arrayList.add(new Music("Ap baithy hain", "Zamad Baig", R.raw.ap));
        arrayList.add(new Music("Mashup", "Arijit Singh & Atif Aslam", R.raw.mashup));

        adapter = new CustomMusicAdapter(getActivity(), R.layout.custom_music_item, arrayList);
        songList.setAdapter(adapter);

        return view;
    }
}
