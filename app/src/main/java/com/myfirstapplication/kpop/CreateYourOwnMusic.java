package com.myfirstapplication.kpop;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class CreateYourOwnMusic extends AppCompatActivity {

    MediaPlayer kick,snare,hats,water;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_music);
        kick=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.kick);
        water=MediaPlayer.create(CreateYourOwnMusic.this,R.raw.water);
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
    public void playWater(View view)
    {
        water.start();
    }
    public void palyKick(View view)
    {
        kick.start();
    }
}
