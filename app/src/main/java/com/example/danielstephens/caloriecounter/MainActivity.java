package com.example.danielstephens.caloriecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
static String TAG = "MainActivity";
//    SettingsActivity calorieObject = new SettingsActivity();

    EditText foodNumEdit;
//    TextView editInfo = (TextView)findViewById(R.id.txtEnterInfo);
//    ImageView arrow = (ImageView)findViewById(R.id.arrowImage);
    ProgressBar prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i(TAG, "App runs");



        Button btnGoToPageOne = (Button)(findViewById(R.id.btnGoToPageOne));
        btnGoToPageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Button 1 was clicked");

                Intent intentOne = new Intent(MainActivity.this, PageOne.class);
                startActivity(intentOne);
            }
        });

        Button btnGoToPageTwo = (Button)(findViewById(R.id.btnGoToPageTwo));
        btnGoToPageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Button 2 was clicked");

                Intent intentTwo = new Intent(MainActivity.this, PageTwo.class);
                startActivity(intentTwo);
            }
        });

        TextView textView = (TextView)(findViewById(R.id.testTextMain));
        int s;
        s = getIntent().getIntExtra("number",0);
        String sa = Integer.toString(s);
        textView.setText(sa);
        prg = (ProgressBar)(findViewById(R.id.calorieProgress));
//sad attempt of getting values to add onto each other
        int calorie = getIntent().getIntExtra("calorie", 2000);
        int tempI = 0 ;
        tempI += s;

        prg.setProgress(tempI);
        prg.setMax(calorie);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //joke generator 3000
                Random rand = new Random();
                String[] joke = {"What's the point of this button?",
                        "How many amnesiacs does it take to change a light bulb? Wait, what were we talking about?",
                        "This app was harder than I thought",
                        "What did the ear of corn say when all it's clothes fell off? Aw shucks!",
                        "Sigh",
                        "So a guy walks into a bar..."};
                int n = rand.nextInt(6);
                Snackbar.make(view, joke[n], Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intentSettings);

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
