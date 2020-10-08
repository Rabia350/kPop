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

        final ListView list = view.findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData("Tum Hi Ana", "https://www.youtube.com/watch?v=o4t7EhH5vLs", "https://en.wikipedia.org/wiki/Tum_Hi_Aana#/media/File:Tum_Hi_Aana.jpg"));
        arrayList.add(new SubjectData("Ap Baithy hain", "https://www.youtube.com/watch?v=kxuI06hQG8k", "https://www.google.com/search?q=ap+baithy+hain+only+image&tbm=isch&ved=2ahUKEwieo_2LoaXsAhW0gHMKHWbGCskQ2-cCegQIABAA&oq=ap+baithy+hain+only+image&gs_lcp=CgNpbWcQA1CvjgVY9tkFYLvcBWgDcAB4AIAB-wSIAagfkgEJMi05LjIuMC4ymAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=Iyl_X96FNLSBzgPmjKvIDA&bih=657&biw=1366&rlz=1C1EJFA_enPK797PK806#imgrc=jicocGfU8PMwcM"));
        arrayList.add(new SubjectData("Let me Love", "https://www.youtube.com/watch?v=VP5ODDPUpcE", "https://en.wikipedia.org/wiki/Let_Me_Love_You_(DJ_Snake_song)#/media/File:DJSnakeLetMeLoveYou.jpg"));
        arrayList.add(new SubjectData("MashUp 2019", "https://www.youtube.com/watch?v=Zdo5WW7DZZY", "https://www.google.com/search?q=mashup+arijit+and+atif&tbm=isch&ved=2ahUKEwiXm8uVo6XsAhVPgRoKHdINC-0Q2-cCegQIABAA&oq=mashup+arijit+and+atif&gs_lcp=CgNpbWcQAzoCCAA6BggAEAUQHjoGCAAQCBAeOgQIABAYUNwrWJVjYIVlaABwAHgAgAHzAogB-h6SAQYyLTEwLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=UCt_X5ezNc-CatKbrOgO&bih=657&biw=1366&rlz=1C1EJFA_enPK797PK806#imgrc=sM5dWJDqP8FDvM"));
        arrayList.add(new SubjectData("Baby Baby", "https://www.youtube.com/watch?v=1a5SWpp9Wfg", "https://www.google.com/search?q=baby+baby+song&tbm=isch&ved=2ahUKEwicl8Gco6XsAhUQfBoKHcB4BM0Q2-cCegQIABAA&oq=baby+baby+song&gs_lcp=CgNpbWcQAzICCAAyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADICCAA6BQgAELEDOgcIABCxAxBDOgQIABBDOgQIABAeOgYIABAFEB5QytMIWJudCWCSqAloAHAAeACAAdUFiAGPP5IBCTMtMS4zLjcuM5gBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=Xyt_X9zrF5D4acDxkegM&bih=657&biw=1366&rlz=1C1EJFA_enPK797PK806#imgrc=5z6tV0dUGTQVCM"));
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), arrayList);
        list.setAdapter(customAdapter);

        return view;
    }
}
