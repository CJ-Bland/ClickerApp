package com.example.cj.clickerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Clicker_App extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean bool = false;
    public ViewFlipper vf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //in app bar test
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Will eventually add classes ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        vf = (ViewFlipper)findViewById(R.id.vf);
    }

    public void onClick(View v){
        if(v.getId() == R.id.button8){
            vf.setDisplayedChild(1);
            //findViewById(R.id.content).setVisibility(View.GONE);
            //findViewById(R.id.testQuiz).setVisibility(View.VISIBLE);
            findViewById(R.id.fab).setVisibility(View.GONE);
            //Toast.makeText(getApplicationContext(), "Switch to Second", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.button5){
            //findViewById(R.id.testQuiz).setVisibility(View.GONE);
            //findViewById(R.id.content).setVisibility(View.VISIBLE);
            vf.setDisplayedChild(0);
            findViewById(R.id.fab).setVisibility(View.VISIBLE);
            //Toast.makeText(getApplicationContext(), "Switch to First", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.buttonA){
            bool = true;
            Toast.makeText(getApplicationContext(), "A", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.buttonB){
            bool=false;
            Toast.makeText(getApplicationContext(), "B", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.buttonC){
            bool=false;
            Toast.makeText(getApplicationContext(), "C", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.buttonD){
            bool=false;
            Toast.makeText(getApplicationContext(), "D", Toast.LENGTH_SHORT).show();
        }

        else if(v.getId() == R.id.button7){
            vf.setDisplayedChild(2);
            findViewById(R.id.fab).setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Confirm", Toast.LENGTH_SHORT).show();
            TextView tv = (TextView) findViewById(R.id.answer);
            if(bool){
                tv.setText("You got it right!");
            }
            else if(!bool){
                tv.setText("You got it wrong :(");
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_class) {

            vf.setDisplayedChild(0);
            findViewById(R.id.fab).setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Switch to First", Toast.LENGTH_SHORT).show();

        }  else if (id == R.id.nav_logout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Do You Wish To Log Out?");

            // Set up the buttons
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Clicker_App.this, Screen2_LogIn.class);
                    startActivity(i);
                    finish();
                    // m_Text = input.getText().toString();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
