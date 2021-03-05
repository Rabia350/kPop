package com.myfirstapplication.kpop;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RecordFragment extends Fragment implements View.OnClickListener {


    private static final int PERRMISSION_CODE = 21 ;
    private NavController navcontroller;
    private ImageButton listBtn;
    private ImageButton recordBtn;
    int songID;
    private boolean isrecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private MediaRecorder mediarecorder;
    private  String recordFile;
    private Chronometer timer;
    private TextView filenameText;
    private Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        if (getArguments()!=null){
            songID = getArguments().getInt("songID");
        }
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CreateYourOwnMusic.class));
            }
        });
        checkpermissions();
        return view;
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