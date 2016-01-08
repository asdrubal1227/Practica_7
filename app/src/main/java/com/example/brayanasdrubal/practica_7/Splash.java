package com.example.brayanasdrubal.practica_7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    private static final long SPLASH_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        /*this.getWidow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams.FLAG_FULLSCREEN;
        /*android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.hide();                                                   */

        setContentView(R.layout.activity_splash);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                int dato= prefs.getInt("entro",0);
                if (dato==0) {
                    Intent i = new Intent().setClass(Splash.this, Interfaz.class);
                    startActivity(i);
                    finish();
                } else if (dato==1){
                    Intent i = new Intent().setClass(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_DELAY);
    }
}
