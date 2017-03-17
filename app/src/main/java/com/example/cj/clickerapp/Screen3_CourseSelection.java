package com.example.cj.clickerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3_CourseSelection extends AppCompatActivity {

    private String m_Text = "";
    private String classes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3__course_selection);
    }

    public void addClass(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        String userName = getIntent().getStringExtra("userName");
        DBTools dbTools = new DBTools(this);
        User currentUser = dbTools.getUser(userName);
        currentUser.addClass(m_Text);

        String classList = toString(currentUser);

        ((TextView) findViewById(R.id.classList)).setText(classList);


    }

    public String toString(User user){

        for (int i = 0; i < user.classes.size(); i++){
            classes += user.classes.get(i) + ", ";
        }
        return classes;
    }

    /**
     * Generate the options menu.
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Respond to options selections.
     * @param item
     * @return
     */
    //==============================================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "You clicked on Settings",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Do You Wish To Log Out?");

               /* final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                builder.setView(input);*/

                // Set up the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Screen3_CourseSelection.this, Screen2_LogIn.class);
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
