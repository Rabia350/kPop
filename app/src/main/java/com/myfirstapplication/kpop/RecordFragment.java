package com.myfirstapplication.kpop;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment  implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PERRMISSION_CODE = 21 ;
    private NavController navcontroller;
    private ImageButton listBtn;
    private ImageButton recordBtn;
    private boolean isrecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private MediaRecorder mediarecorder;
    private  String recordFile;
    private Chronometer timer;
    private TextView filenameText;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
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
        return inflater.inflate(R.layout.fragment_record, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navcontroller = Navigation.findNavController(view);
        listBtn = view.findViewById(R.id.record_list_btn);
        recordBtn = view.findViewById(R.id.record_btn);
        timer= view.findViewById(R.id.record_timer);
        filenameText=view.findViewById(R.id.record_filename);
        listBtn.setOnClickListener(this);
        recordBtn.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.record_list_btn:
                if(isrecording){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            navcontroller.navigate(R.id.action_recordFragment_to_listFragment2);
                            isrecording=false;
                        }
                    });
                    alertDialog.setNegativeButton("CANCEL",null);
                    alertDialog.setTitle("Audio Still Recording.");
                    alertDialog.setMessage("Are You sure , You want to stop Recording?");
                    alertDialog.create().show();
                }else{
                    navcontroller.navigate(R.id.action_recordFragment_to_listFragment2);
                }

                break;

            case R.id.record_btn:
                if (isrecording) {
                    //stop recording
                    stopRecording();
                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.play_stop, null));


                    isrecording = false;
                } else {
                    //start recording
                    if (checkpermissions()) {
                        startRecording();
                        recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.recbtn, null));
                        isrecording = true;
                    }
                }


                break;
        }
    }

    private void stopRecording() {
        timer.stop();
        filenameText.setText("Recording Stopped, File Saved :" + recordFile);
        mediarecorder.stop();
        mediarecorder.release();
        mediarecorder=null;
    }

    private void startRecording() {
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        String recordPath = getActivity().getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat formaatter =new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now= new Date();
        recordFile="Recording_" +formaatter.format(now) +".3gp";
        filenameText.setText("Recording , File Name :" + recordFile);
        mediarecorder = new MediaRecorder();
        mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediarecorder.setOutputFile(recordPath + "/" + recordFile);
        mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediarecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediarecorder.start();

    }


    private boolean checkpermissions() {

        if (ActivityCompat.checkSelfPermission(getContext(), recordPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;

        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[] {recordPermission} ,PERRMISSION_CODE);
            return false;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (isrecording) {
            stopRecording();
        }
    }
}