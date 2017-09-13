package com.example.cj.clickerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RSDriverException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
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

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The main screen, currently uses a view flipper to show different xml screens, but will eventually
 * be the screen after the list view of classes so user can hit back without going to main login screen
 */
public class Clicker_App extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String dbUrl = "jdbc:postgresql://ec2-54-243-197-180.compute-1.amazonaws.com:5432/d1poesa08bdj5b?sslmode=require&user=frbsrpyunfrpxp&password=539eac8b14c3b138584be23b272bf1eb4b89f00afc459695c969666773f27871";

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

    /*My on click method, may be changed into multiple methods at some point, a separate one to
    handle view flipper and one to handle normal button presses
    */
    public void onClick(View v){

        //Flipper Buttons
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
        else if(v.getId() == R.id.button2) {
            vf.setDisplayedChild(3);
            findViewById(R.id.fab).setVisibility(View.GONE);
        }

        //Quiz buttons
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
        //Goes to next screen
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

        //Runs connection test

        else if(v.getId() == R.id.bConnTest) {


            Toast.makeText(getApplicationContext(), "Connection Test", Toast.LENGTH_SHORT).show();
            TextView connTest = (TextView) findViewById(R.id.tvConnTest);
            connTest.setText("Trying");

            checkJDBC();
/*
            try {

                //Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection conn = DriverManager.getConnection(dbUrl);
                connTest.append("1");


                Statement stmt = null;
                ResultSet rset = null;
                stmt = conn.createStatement();
                connTest.append("2");
                rset = stmt.executeQuery("SELECT * FROM class_table;");
                System.out.println("dept course_num sect_num online id");
                while (rset.next()) {
                    String newLine = "";

                    newLine += rset.getString(1) + "\t\t";
                    newLine += rset.getString(2) + "\t\t";
                    newLine += rset.getString(3) + "\t\t\t";
                    newLine += rset.getString(4) + "\t";
                    newLine += rset.getString(5) + "\n";

                    connTest.append(newLine);
                               }
            }
            catch(SQLException ex){
                //connTest.append("\nConnection Error");
                connTest.append("\nSQLException: " + ex.getMessage());
                connTest.append("\nSQLState: " + ex.getSQLState());
                connTest.append("\nVendorError: " + ex.getErrorCode());
            }
            catch(ClassNotFoundException cNFE){
                connTest.append("Class Not Found");
            }
            catch(Exception e){
                connTest.append("Bad");
            }
            finally{

            }
            */
        }
    }

    public void checkJDBC(){
        TextView connTest = (TextView) findViewById(R.id.tvConnTest);
        connTest.append("\nLooking for Driver");
        connTest.setMovementMethod(new ScrollingMovementMethod());
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("com.mysql.jdbc.Driver").newInstance(); //second driver, still doesn't work

            connTest.append("\nFound Driver");
        } catch (ClassNotFoundException e) {
            connTest.append("\nWhere is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String s = writer.toString();
            connTest.append("\n" + s);
           // e.printStackTrace();

        }
        catch (Exception ex){
            connTest.append("\nBad");
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
                    LoginManager.getInstance().logOut();
                    AccessToken.setCurrentAccessToken(null);
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

    protected void onStop(){
        super.onStop();

        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();
        AccessToken.setCurrentAccessToken(null);
    }
}
