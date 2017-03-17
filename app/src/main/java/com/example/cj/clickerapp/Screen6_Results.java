package com.example.cj.clickerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Screen6_Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6__results);
    }

    public void goBack(View v){
        Intent i = new Intent(this, Screen3_CourseSelection.class);
        this.startActivity(i);
    }
}
