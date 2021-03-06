package com.campbellapps.christiancampbell.restaurant;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class SpashScreen extends AppCompatActivity {ProgressBar pgr;
    int progress = 0;
    MediaPlayer mp;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.restaurant);
        mp.setLooping(true);
        mp.isLooping();
        mp.start();
        Thread myThread = new Thread(){
            @Override
            public void run() {

            }
        };
        myThread.start();
        pgr=(ProgressBar)findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    progress+=1;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            pgr.setProgress(progress);
                            if(progress==pgr.getMax()){
                                pgr.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}