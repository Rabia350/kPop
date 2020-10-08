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

        final ListView list = getActivity().findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/", "https://www.tutorialspoint.com/java/images/java-mini-logo.jpg"));
        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", "https://www.tutorialspoint.com/python/images/python-mini.jpg"));
        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", "https://www.tutorialspoint.com/javascript/images/javascript-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", "https://www.tutorialspoint.com/cprogramming/images/c-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", "https://www.tutorialspoint.com/cplusplus/images/cpp-mini-logo.jpg"));
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", "https://www.tutorialspoint.com/android/images/android-mini-logo.jpg"));
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), arrayList);
        list.setAdapter(customAdapter);

        return view;
    }
}
