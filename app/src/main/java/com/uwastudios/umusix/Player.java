package com.uwastudios.umusix;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements View.OnClickListener{
    static MediaPlayer mp;
    ArrayList<File> mySongs;
    SeekBar seekBar;
    Uri u;
    Thread updateSeekbar;
    int position;
    ImageButton btnPlay,btnNext,btnPrev,btnNext2,btnPrev2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext2 = (ImageButton) findViewById(R.id.btnNext2);
        btnPrev2 = (ImageButton) findViewById(R.id.btnPrev2);

        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnNext2.setOnClickListener(this);
        btnPrev2.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        updateSeekbar = new Thread(){
            public  void run(){
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
                while(currentPosition< totalDuration){
                    try{
                        sleep(500);
                        currentPosition = mp.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        if(mp!=null){
            mp.stop();
            mp.release();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        int position = b.getInt("pos",0);

        u = Uri.parse(mySongs.get(position).toString());
        mp = MediaPlayer.create(getApplicationContext(),u);
        mp.start();
        seekBar.setMax(mp.getDuration());

        updateSeekbar.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar seekBar, int progress, boolean formUser){

            }

            public void onStartTrackingTouch(SeekBar seekBar){

            }

            public void onStopTrackingTouch(SeekBar seekBar){
                mp.seekTo(seekBar.getProgress());
            }
        });
    }
    
    public void onClick(View v){
        int id = v.getId();
    switch (id) {
        case R.id.btnPlay:
            if (mp.isPlaying()) {
                btnPlay.setImageResource((R.drawable.ic_play_circle_outline_white_48dp));
                mp.pause();
            } else {
            mp.start();
            btnPlay.setImageResource((R.drawable.ic_pause_circle_outline_white_48dp));
            }
                break;

        case R.id.btnNext:
                    mp.seekTo(mp.getCurrentPosition()+5000);
                    break;
        case R.id.btnPrev:
                    mp.seekTo(mp.getCurrentPosition()-5000);
                    break;
        case R.id.btnNext2:
            mp.stop();
            mp.release();
            position =(position+1)%mySongs.size();
            u = Uri.parse(mySongs.get(position).toString());
            mp = MediaPlayer.create(getApplicationContext(),u);
            mp.start();
            seekBar.setMax(mp.getDuration());
            break;

        case R.id.btnPrev2:
            mp.stop();
            mp.release();
            position =(position -1<0)? mySongs.size() -1: position-1;
//            if(position-1 <0){
//                position = mySongs.size()-1;
//            }else{
//                position = position-1;
//            }
            u = Uri.parse(mySongs.get(position).toString());
            mp = MediaPlayer.create(getApplicationContext(),u);
            mp.start();
            seekBar.setMax(mp.getDuration());
            break;


        }
    }


}
