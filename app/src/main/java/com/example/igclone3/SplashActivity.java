package com.example.igclone3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();
    }

    private void splash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //tujuan passing data
                //tujuan pindah activity
                //Intent(classAwal, classTujuan)
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}