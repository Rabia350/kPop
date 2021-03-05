package com.myfirstapplication.kpop;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;


public class ListFragment extends Fragment implements audioListAdapter.onItemListClick{


    private ConstraintLayout playersheet;
    private BottomSheetBehavior bottomsheetbehaviour;
    private RecyclerView audiolist;
    private File[] allfiles;
    private audioListAdapter AudioListAdapter;
    private MediaPlayer mediaPlayer= null;
    private Boolean isPlaying=false;
    private File fileToPlay;
    private ImageButton playBtn;
    private TextView playerHeader;
    private TextView playerFilename;
    private SeekBar playerSeekbar;
    private Handler seekbarHandler;
    private Runnable updateSeekbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playersheet = view.findViewById(R.id.player_sheet);
        audiolist = view.findViewById(R.id.audio_list_view);

        playBtn=view.findViewById(R.id.player_play_btn);
        playerHeader=view.findViewById(R.id.player_header_title);
        playerFilename=view.findViewById(R.id.player_fileName);
        playerSeekbar=view.findViewById(R.id.player_seekbar);


        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        allfiles = directory.listFiles();

//        AudioListAdapter = new audioListAdapter(allfiles,this);
//
//        audiolist.setHasFixedSize(true);
//        audiolist.setLayoutManager(new LinearLayoutManager(getContext()));
//        audiolist.setAdapter(AudioListAdapter);



        bottomsheetbehaviour = BottomSheetBehavior.from(playersheet);


        bottomsheetbehaviour.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomsheetbehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);


                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v){
                if (isPlaying) {
                    pauseAudio();
                }
                else {
                    if(fileToPlay != null){
                        resumeAudio();
                    }
                }
            }
        });
        playerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseAudio();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                resumeAudio();
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onclickListner(File file, int position) {
        fileToPlay = file;
        if (isPlaying) {
            stopAudio();
            playAudio(fileToPlay);
        }
        else {

            playAudio(fileToPlay);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void pauseAudio(){
        mediaPlayer.pause();
        playBtn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.pause_btn,null));
        isPlaying=false;
        seekbarHandler.removeCallbacks(updateSeekbar);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void resumeAudio(){
        mediaPlayer.start();
        playBtn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.play_btn,null));
        isPlaying=true;
        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar,0);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void stopAudio() {
        playBtn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.pause_btn,null));

        playerHeader.setText("Stopped");
        isPlaying=false;
        mediaPlayer.stop();
        seekbarHandler.removeCallbacks(updateSeekbar);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void playAudio(File fileToPlay) {
        // play the audio
        mediaPlayer=new MediaPlayer();
        bottomsheetbehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
        try {
            mediaPlayer.setDataSource(fileToPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playBtn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.play_btn,null));
        playerFilename.setText(fileToPlay.getName());
        playerHeader.setText("Playing");
        isPlaying=true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopAudio();
                playerHeader.setText("Finished");
            }
        });
        playerSeekbar.setMax(mediaPlayer.getDuration());
        seekbarHandler= new Handler();
        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar,0);
    }

    private void updateRunnable() {

        updateSeekbar=new Runnable() {
            @Override
            public void run() {
                playerSeekbar.setProgress(mediaPlayer.getCurrentPosition());
                seekbarHandler.postDelayed(this,500);

            }
        };

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
        super.onStop();
        stopAudio();
    }
}
