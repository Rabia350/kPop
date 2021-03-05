package com.myfirstapplication.kpop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class englishFragment extends Fragment {


    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songList;
<<<<<<< HEAD
=======
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etSearch;
    public englishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment englishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static englishFragment newInstance(String param1, String param2) {
        englishFragment fragment = new englishFragment();
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
>>>>>>> f8a7504c314027e184c1ad221b2d9c8f2249ecf5

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english, container, false);
        songList = view.findViewById(R.id.songList);
        etSearch = view.findViewById(R.id.etSearch);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("Marshmello", "Anne-Marie", R.raw.marshmello));
        arrayList.add(new Music("Let me down slowly", "Alec Benjamin", R.raw.letmedown));
        arrayList.add(new Music("Pefect", "Ed Sheeran", R.raw.perfect));
        arrayList.add(new Music("Closer", "The Chainsmokers", R.raw.closer));
        arrayList.add(new Music("Señorita", "Shawn Mendes, Camila Cabello", R.raw.senorita));
        arrayList.add(new Music("Let Me Love You", "DJ Snake ft. Justin Bieber", R.raw.letmeloveyou));

        // Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                Log.e("sdsds",s.toString());
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        adapter = new CustomMusicAdapter(getActivity(), R.layout.custom_music_item, arrayList);
        songList.setAdapter(adapter);

        return view;
    }
}