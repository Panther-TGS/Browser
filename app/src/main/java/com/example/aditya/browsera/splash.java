package com.example.aditya.browsera;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class splash extends AppCompatActivity {

    private VideoView videoView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        videoView = (VideoView) findViewById(R.id.videoView);
        button = findViewById(R.id.button);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.adinix;
        Uri uri = Uri.parse(path);

        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(splash.this,MainActivity.class));
                finish();
            }
        });

    }
}
