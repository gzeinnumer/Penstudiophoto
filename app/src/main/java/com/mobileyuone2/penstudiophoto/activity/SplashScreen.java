package com.mobileyuone2.penstudiophoto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileyuone2.penstudiophoto.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    TextView tv_splash;
    ImageView img_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tv_splash = findViewById(R.id.tv_splash);
        img_splash = findViewById(R.id.img_splash);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv_splash.startAnimation(myanim);
        img_splash.startAnimation(myanim);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }
}
