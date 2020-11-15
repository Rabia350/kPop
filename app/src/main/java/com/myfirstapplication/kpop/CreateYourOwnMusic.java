package com.myfirstapplication.kpop;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class CreateYourOwnMusic extends AppCompatActivity {

    MediaPlayer kick,snare,hats,claps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_music);
        kick=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.kick);
        claps=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.clap);
        snare=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.snare);
        hats=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.hats);

    }
    public void playHats(View view)
    {
        hats.start();
    }
    public void playSnare(View view)
    {
        snare.start();
    }
    public void playClap(View view)
    {
        claps.start();
    }
    public void palyKick(View view)
    {
        kick.start();
    }
}
