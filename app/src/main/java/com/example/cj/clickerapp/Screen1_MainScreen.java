package com.example.cj.clickerapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Screen1_MainScreen extends AppCompatActivity {

    private final int DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1__main_screen);
    }

    //Goes to the next screen
    protected void onStart(){
        super.onStart();
        Handler handler = new Handler();
        handler.postDelayed(runner, DELAY);
    }

    private final Runnable runner = new Runnable(){
        @Override
        public void run(){
            nextScreen();
        }
    };

    //Goes to the next screen and prevents user from going back once loaded
    private void nextScreen(){
        //Intent i = new Intent(this, Screen2_LogIn.class);
        Intent i = new Intent(this, Screen2_LogIn.class);
        this.startActivity(i);
        finish();
    }
}
