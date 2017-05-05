package com.example.cj.clickerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Since project was changed to use a navigation drawer (hamburger menu) class is not used
 * May be reworked into program next semester
 *
 * @author CJ Bland
 * @version 1.0
 */
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
