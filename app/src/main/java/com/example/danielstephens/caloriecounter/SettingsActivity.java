package com.example.danielstephens.caloriecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

public class SettingsActivity extends AppCompatActivity {
    TextView results, genderError, ageError, heightError, weightError, activityError, displayGender,
    displayHeight, displayAge, displayWeight, displayActivity;
    Button btnSave;
    EditText height, age, weight;
    Spinner genderSpinner, activitySpinner;
    ArrayAdapter<CharSequence> gender_adapter, activity_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        results = (TextView) (findViewById(R.id.testCalc));
        genderError = (TextView) (findViewById(R.id.genderError));
        ageError = (TextView) (findViewById(R.id.ageError));
        heightError = (TextView) (findViewById(R.id.heightError));
        weightError = (TextView) (findViewById(R.id.weightError));
        activityError = (TextView) (findViewById(R.id.activityError));
        btnSave = (Button) (findViewById(R.id.btnSave));
        height = (EditText) (findViewById(R.id.heightInput));
        age = (EditText) (findViewById(R.id.ageInput));
        weight = (EditText) (findViewById(R.id.weightInput));

        displayGender = (TextView) (findViewById(R.id.displayGender));
        displayHeight = (TextView) (findViewById(R.id.displayHeight));
        displayAge = (TextView) (findViewById(R.id.displayAge));
        displayWeight = (TextView) (findViewById(R.id.displayWeight));
        displayActivity = (TextView) (findViewById(R.id.displayActivity));

        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        gender_adapter = ArrayAdapter.createFromResource(this, R.array.gender_values, android.R.layout.simple_spinner_item);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(gender_adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        activitySpinner = (Spinner) findViewById(R.id.activitySpinner);
        activity_adapter = ArrayAdapter.createFromResource(this, R.array.activity_values, android.R.layout.simple_spinner_item);
        activity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activitySpinner.setAdapter(activity_adapter);
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public double calorieCalc(String gen, String act, String h, String a, String w) {
//        Women: BMR = 655 + (4.35 x weight in pounds) + (4.7 x height in inches) - (4.7 x age in years)
//        Men: BMR = 66 + (6.23 x weight in pounds) + (12.7 x height in inches) - (6.8 x age in years)
        double num = 0;
        gen = genderSpinner.getSelectedItem().toString();
        Log.i("gender", gen);
        act = activitySpinner.getSelectedItem().toString();
        Log.i("activity", act);
        h = height.getText().toString();
        Log.i("height", h);
        a = age.getText().toString();
        Log.i("age", a);
        w = weight.getText().toString();
        Log.i("weight", w);

        int height = Integer.parseInt(h);
        int age = Integer.parseInt(a);
        int weight = Integer.parseInt(w);


        if (gen.equals("Male")) {
            genderError.setText("");
            if (act.equals("Little to no Exercise")) {
                num = ((66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) * 1.2);
            }
            if (act.equals("Lightly active(1-3 days per week)")) {
                num = ((66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) * 1.375);
            }
            if (act.equals("Moderately active(3-5 days per week)")) {
                num = ((66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) * 1.55);
            }
            if (act.equals("Very active(6-7 days per week)")) {
                num = ((66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) * 1.725);
            }
            if (act.equals("Extra active(Very hard exercise or 2x training)")) {
                num = ((66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) * 1.9);
            }
        }
        if (gen.equals("Female")) {
            genderError.setText("");
            if (act.equals("Little to No Exercise)")) {
                num = ((655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * 1.2);
            }
            if (act.equals("Lightly active(1-3 days per week)")) {
                num = ((655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * 1.375);
            }
            if (act.equals("Moderately active(3-5 days per week)")) {
                num = ((655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * 1.55);
            }
            if (act.equals("Very active(6-7 days per week)")) {
                num = ((655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * 725);
            }
            if (act.equals("Extra active(Very hard exercise or 2x training)")) {
                num = ((655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * 1.9);
            }
        }
        return num;
    }

    public void sample(View v) {
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        String gentext = genderSpinner.getSelectedItem().toString();

        activitySpinner = (Spinner) findViewById(R.id.activitySpinner);
        String acttext = activitySpinner.getSelectedItem().toString();

        height = (EditText) (findViewById(R.id.heightInput));
        String heighttext = height.getText().toString();

        age = (EditText) (findViewById(R.id.ageInput));
        String agetext = age.getText().toString();

        weight = (EditText) (findViewById(R.id.weightInput));
        String wtext = weight.getText().toString();

        boolean allOk = true;

        if (!gentext.equals("Select Gender")) {
            genderError.setText("");
            allOk = true;

        } if (gentext.equals("Select Gender")) {
            genderError.setText("Please select a gender");
            allOk = false;

        } if (!heighttext.equals("")) {
            heightError.setText("");
            allOk = true;

        } if (heighttext.equals("")) {
            heightError.setText("Please enter your height");
            allOk = false;

        } if (!agetext.equals("")) {
            ageError.setText("");
            allOk = true;

        } if (agetext.equals("")) {
            ageError.setText("Please enter your age");
            allOk = false;

        } if (!wtext.equals("")) {
            weightError.setText("");
            allOk = true;

        } if (wtext.equals("")) {
            weightError.setText("Please enter your weight (judgement free zone)");
            allOk = false;

        } if (!acttext.equals("Select Activity")) {
            activityError.setText("");
            allOk = true;

        } else if (acttext.equals("Select Activity")) {
                activityError.setText("Please select an activity level");
                allOk = false;

        } if (allOk == true){
            double BRM = calorieCalc(gentext, acttext, heighttext, agetext, wtext);
            int calorie = (int) Math.round(BRM);

//            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//            intent.putExtra("calorie", 0);
//            startActivity(intent);

            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("gender", gentext);
            editor.putString("height", heighttext);
            editor.putString("age", agetext);
            editor.putString("weight", wtext);
            editor.putString("activity", acttext);
            editor.putInt("calorie", calorie);
            editor.apply();

            displayGender.setText("");
            displayHeight.setText("");
            displayAge.setText("");
            displayWeight.setText("");
            displayActivity.setText("");

            results.setText("You should eat about " + calorie + " calories every day");

            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void display(View v)
    {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String g = sharedPref.getString("gender", "");
        String h = sharedPref.getString("height", "");
        String age = sharedPref.getString("age", "");
        String w = sharedPref.getString("weight", "");
        String act = sharedPref.getString("activity", "");

        displayGender.setText("Gender: "+g);
        displayHeight.setText("Height: "+h+" inches");
        displayAge.setText("Age: "+age);
        displayWeight.setText("Weight: "+w+" lbs");
        displayActivity.setText("Activity level: "+act);
        results.setText("");
    }

//    public int getCalorieM(){
//        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        int calorie = sharedPref.getInt("calorie", 0);
//        return calorie;
//    }

    public void updateCal(View v){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("calorie", 0);
        startActivity(intent);
    }
}
