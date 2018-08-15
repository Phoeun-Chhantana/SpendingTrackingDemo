package com.example.chhan.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    private static final int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startMainActivity);
                finish();
            }
        },DELAY_MILLIS);

    }
}
