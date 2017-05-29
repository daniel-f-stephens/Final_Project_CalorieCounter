package com.example.danielstephens.caloriecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class PageOne extends AppCompatActivity {

    TextView totalText;

    Button btnPageOne;

    Spinner mainBreakfastSpinner, mainLunchSpinner, mainDinnerSpinner,
            sideBreakfastSpinner, sideLunchSpinner, sideDinnerSpinner,
            breakfastBeverageSpinner, lunchBeverageSpinner, dinnerBeverageSpinner;

    ArrayAdapter <CharSequence> breakfast_adapter, lunch_adapter, dinner_adapter,
                                sBreakfast_adapter, sLunch_adapter, sDinner_adapter,
                                breakfastBeverage_adapter, lunchBeverage_adapter, dinnerBeverage_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_one);

        totalText = (TextView)(findViewById(R.id.txtTotal));

        btnPageOne = (Button)(findViewById(R.id.btnSave_pg1));



        Button btnUpdate = (Button)(findViewById(R.id.btnUpdate));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bMain = mainBreakfastSpinner.getSelectedItem().toString();
                String lMain = mainLunchSpinner.getSelectedItem().toString();
                String dMain = mainDinnerSpinner.getSelectedItem().toString();
                String bSide = sideBreakfastSpinner.getSelectedItem().toString();
                String lSide = sideLunchSpinner.getSelectedItem().toString();
                String dSide = sideDinnerSpinner.getSelectedItem().toString();
                String bBeverage = breakfastBeverageSpinner.getSelectedItem().toString();
                String lBeverage = lunchBeverageSpinner.getSelectedItem().toString();
                String dBeverage = dinnerBeverageSpinner.getSelectedItem().toString();
                int display = foodCalc(bMain, lMain, dMain, bSide, lSide, dSide, bBeverage, lBeverage, dBeverage);

                boolean allOk = true;

                if (!bMain.equals("Select Food")||!lMain.equals("Select Food")||!dMain.equals("Select Food")) {
                    allOk = true;
                }
                else if (bMain.equals("Select Food")||lMain.equals("Select Food")||dMain.equals("Select Food")) {
                    allOk = false;
                }
                else if (!bSide.equals("Select Side")||!lSide.equals("Select Side")||!dSide.equals("Select Side")) {
                    allOk = true;
                }
                else if (bSide.equals("Select Side")||lSide.equals("Select Side")||dSide.equals("Select Side")) {
                    allOk = false;
                }
                else if (!bBeverage.equals("Select Beverage")||!lBeverage.equals("Select Beverage")||!dBeverage.equals("Select Beverage")) {
                    allOk = true;
                }
                else if (bBeverage.equals("Select Beverage")||lBeverage.equals("Select Beverage")||dBeverage.equals("Select Beverage")) {
                    allOk = false;
                }
                if (allOk) {

                    int number = display;
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);

                }
            }
        });


        //main meal spinner creators
        mainBreakfastSpinner = (Spinner) findViewById(R.id.breakfastSpinner);
        mainBreakfastSpinner.setEnabled(false);
        breakfast_adapter = ArrayAdapter.createFromResource(this,R.array.breakfast_main, android.R.layout.simple_spinner_item);
        breakfast_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainLunchSpinner = (Spinner) findViewById(R.id.lunchSpinner);
        mainLunchSpinner.setEnabled(false);
        lunch_adapter = ArrayAdapter.createFromResource(this,R.array.lunch_main, android.R.layout.simple_spinner_item);
        lunch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainDinnerSpinner = (Spinner) findViewById(R.id.dinnerSpinner);
        mainDinnerSpinner.setEnabled(false);
        dinner_adapter = ArrayAdapter.createFromResource(this,R.array.dinner_main, android.R.layout.simple_spinner_item);
        dinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //side meal spinner initiates
        sideBreakfastSpinner = (Spinner)(findViewById(R.id.sideBreakfastSpinner));
        sideBreakfastSpinner.setEnabled(false);
        sBreakfast_adapter = ArrayAdapter.createFromResource(this, R.array.breakfast_sides, android.R.layout.simple_spinner_item);
        sBreakfast_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sideLunchSpinner = (Spinner)(findViewById(R.id.sideLunchSpinner));
        sideLunchSpinner.setEnabled(false);
        sLunch_adapter = ArrayAdapter.createFromResource(this, R.array.lunch_sides, android.R.layout.simple_spinner_item);
        sLunch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sideDinnerSpinner = (Spinner)(findViewById(R.id.sideDinnerSpinner));
        sideDinnerSpinner.setEnabled(false);
        sDinner_adapter = ArrayAdapter.createFromResource(this, R.array.dinner_sides, android.R.layout.simple_spinner_item);
        sDinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //beverage spinner initiates
        breakfastBeverageSpinner = (Spinner)(findViewById(R.id.breakfastBeverageSpinner));
        breakfastBeverageSpinner.setEnabled(false);
        breakfastBeverage_adapter = ArrayAdapter.createFromResource(this, R.array.breakfast_beverages, android.R.layout.simple_spinner_item);
        breakfastBeverage_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lunchBeverageSpinner = (Spinner)(findViewById(R.id.lunchBeverageSpinner));
        lunchBeverageSpinner.setEnabled(false);
        lunchBeverage_adapter = ArrayAdapter.createFromResource(this, R.array.lunch_beverages, android.R.layout.simple_spinner_item);
        lunchBeverage_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dinnerBeverageSpinner = (Spinner)(findViewById(R.id.dinnerBeverageSpinner));
        dinnerBeverageSpinner.setEnabled(false);
        dinnerBeverage_adapter = ArrayAdapter.createFromResource(this, R.array.dinner_beverages, android.R.layout.simple_spinner_item);
        dinnerBeverage_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainBreakfastSpinner.setAdapter(breakfast_adapter);
        mainBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mainLunchSpinner.setAdapter(lunch_adapter);
        mainLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mainDinnerSpinner.setAdapter(dinner_adapter);
        mainDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //side meal methods
        sideBreakfastSpinner.setAdapter(sBreakfast_adapter);
        sideBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sideLunchSpinner.setAdapter(sLunch_adapter);
        sideLunchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sideDinnerSpinner.setAdapter(sDinner_adapter);
        sideDinnerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //beverage methods
        breakfastBeverageSpinner.setAdapter(breakfastBeverage_adapter);
        breakfastBeverageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lunchBeverageSpinner.setAdapter(lunchBeverage_adapter);
        lunchBeverageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dinnerBeverageSpinner.setAdapter(dinnerBeverage_adapter);
        dinnerBeverageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void selectFood(View view){

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.btnBreakfast:

                if(checked) {

                    mainBreakfastSpinner.setEnabled(true);
                    sideBreakfastSpinner.setEnabled(true);
                    breakfastBeverageSpinner.setEnabled(true);

                    mainBreakfastSpinner.setVisibility(View.VISIBLE);
                    mainLunchSpinner.setVisibility(View.INVISIBLE);
                    mainDinnerSpinner.setVisibility(View.INVISIBLE);

                    sideBreakfastSpinner.setVisibility(View.VISIBLE);
                    sideLunchSpinner.setVisibility(View.INVISIBLE);
                    sideDinnerSpinner.setVisibility(View.INVISIBLE);

                    breakfastBeverageSpinner.setVisibility(View.VISIBLE);
                    lunchBeverageSpinner.setVisibility(View.INVISIBLE);
                    dinnerBeverageSpinner.setVisibility(View.INVISIBLE);



                }
                else{
                    mainBreakfastSpinner.setEnabled(false);
                    sideBreakfastSpinner.setEnabled(false);
                    breakfastBeverageSpinner.setEnabled(false);
                }
                break;

            case R.id.btnLunch:
                if(checked) {
                    mainLunchSpinner.setEnabled(true);
                    sideLunchSpinner.setEnabled(true);
                    lunchBeverageSpinner.setEnabled(true);

                    mainBreakfastSpinner.setVisibility(View.INVISIBLE);
                    mainLunchSpinner.setVisibility(View.VISIBLE);
                    mainDinnerSpinner.setVisibility(View.INVISIBLE);

                    sideBreakfastSpinner.setVisibility(View.INVISIBLE);
                    sideLunchSpinner.setVisibility(View.VISIBLE);
                    sideDinnerSpinner.setVisibility(View.INVISIBLE);

                    breakfastBeverageSpinner.setVisibility(View.INVISIBLE);
                    lunchBeverageSpinner.setVisibility(View.VISIBLE);
                    dinnerBeverageSpinner.setVisibility(View.INVISIBLE);
                }
                else{
                    mainLunchSpinner.setEnabled(false);
                    sideLunchSpinner.setEnabled(false);
                    lunchBeverageSpinner.setEnabled(false);
                }
                break;

            case R.id.btnDinner:
                if(checked) {
                    mainDinnerSpinner.setEnabled(true);
                    sideDinnerSpinner.setEnabled(true);
                    dinnerBeverageSpinner.setEnabled(true);

                    mainBreakfastSpinner.setVisibility(View.INVISIBLE);
                    mainLunchSpinner.setVisibility(View.INVISIBLE);
                    mainDinnerSpinner.setVisibility(View.VISIBLE);

                    sideBreakfastSpinner.setVisibility(View.INVISIBLE);
                    sideLunchSpinner.setVisibility(View.INVISIBLE);
                    sideDinnerSpinner.setVisibility(View.VISIBLE);

                    breakfastBeverageSpinner.setVisibility(View.INVISIBLE);
                    lunchBeverageSpinner.setVisibility(View.INVISIBLE);
                    dinnerBeverageSpinner.setVisibility(View.VISIBLE);
                }
                else{
                    mainDinnerSpinner.setEnabled(false);
                    sideDinnerSpinner.setEnabled(false);
                    dinnerBeverageSpinner.setEnabled(false);
                }
                break;
        }

    }

//    public void foo(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        SharedPreferences sharedPref = getSharedPreferences("userFood", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//
//        switch (view.getId()) {
//            case R.id.btnBreakfast:
//                if (checked) {
//                    String breakfastMain = mainBreakfastSpinner.getSelectedItem().toString();
//                    String breakfastSide = sideBreakfastSpinner.getSelectedItem().toString();
//                    String breakfastBeverage = breakfastBeverageSpinner.getSelectedItem().toString();
//                    editor.putString("breakfast_main", breakfastMain);
//                    editor.putString("breakfast_side", breakfastSide);
//                    editor.putString("breakfast_beverage", breakfastBeverage);
//                }
//                else{
//
//                    }
//                    break;
//            case R.id.btnLunch:
//                if(checked) {
//                    String lunchMain = mainLunchSpinner.getSelectedItem().toString();
//                    String lunchSide = sideLunchSpinner.getSelectedItem().toString();
//                    String lunchBeverage = lunchBeverageSpinner.getSelectedItem().toString();
//                    editor.putString("lunch_main", lunchMain);
//                    editor.putString("lunch_side", lunchSide);
//                    editor.putString("lunch_beverage", lunchBeverage);
//                }
//                else;
//                break;
//            case R.id.btnDinner:
//                if (checked) {
//
//                    String dinnerMain = mainDinnerSpinner.getSelectedItem().toString();
//                    String dinnerSide = sideDinnerSpinner.getSelectedItem().toString();
//                    String dinnerBeverage = dinnerBeverageSpinner.getSelectedItem().toString();
//                    editor.putString("dinner_main", dinnerMain);
//                    editor.putString("dinner_side", dinnerSide);
//                    editor.putString("dinner_beverage", dinnerBeverage);
//                }
//                else;
//                break;
//        }
//        editor.apply();
//    }

    public void foodSave(View view) {

        SharedPreferences sharedPref = getSharedPreferences("userFood", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
//        String breakfastMain = mainBreakfastSpinner.getSelectedItem().toString();
//        String breakfastSide = sideBreakfastSpinner.getSelectedItem().toString();
//        String breakfastBeverage = breakfastBeverageSpinner.getSelectedItem().toString();
//        String lunchMain = mainLunchSpinner.getSelectedItem().toString();
//        String lunchSide = sideLunchSpinner.getSelectedItem().toString();
//        String lunchBeverage = lunchBeverageSpinner.getSelectedItem().toString();
//        String dinnerMain = mainDinnerSpinner.getSelectedItem().toString();
//        String dinnerSide = sideDinnerSpinner.getSelectedItem().toString();
//        String dinnerBeverage = dinnerBeverageSpinner.getSelectedItem().toString();

        String bMain = mainBreakfastSpinner.getSelectedItem().toString();
        String lMain = mainLunchSpinner.getSelectedItem().toString();
        String dMain = mainDinnerSpinner.getSelectedItem().toString();
        String bSide = sideBreakfastSpinner.getSelectedItem().toString();
        String lSide = sideLunchSpinner.getSelectedItem().toString();
        String dSide = sideDinnerSpinner.getSelectedItem().toString();
        String bBeverage = breakfastBeverageSpinner.getSelectedItem().toString();
        String lBeverage = lunchBeverageSpinner.getSelectedItem().toString();
        String dBeverage = dinnerBeverageSpinner.getSelectedItem().toString();
        int total = foodCalc(bMain, lMain, dMain, bSide, lSide, dSide, bBeverage, lBeverage, dBeverage);

//        editor.putString("breakfast_main", breakfastMain);
//        editor.putString("breakfast_side", breakfastSide);
//        editor.putString("breakfast_beverage", breakfastBeverage);
//        editor.putString("lunch_main", lunchMain);
//        editor.putString("lunch_side", lunchSide);
//        editor.putString("lunch_beverage", lunchBeverage);
//        editor.putString("dinner_main", dinnerMain);
//        editor.putString("dinner_side", dinnerSide);
//        editor.putString("dinner_beverage", dinnerBeverage);
//
        editor.putInt("total", total);

        editor.apply();


            if (!bMain.equals("") && !bSide.equals("") && !bBeverage.equals("")) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
//                String aa = sharedPref.getString("breakfast_main", "");
//                Toast.makeText(getApplicationContext(), aa, Toast.LENGTH_SHORT).show();
//                String ab = sharedPref.getString("breakfast_side", "");
//                Toast.makeText(getApplicationContext(), ab, Toast.LENGTH_SHORT).show();
//                String ac = sharedPref.getString("breakfast_beverage", "");
//                Toast.makeText(getApplicationContext(), ac, Toast.LENGTH_SHORT).show();
            }

                if (!lMain.equals("") && !lSide.equals("") && !lBeverage.equals("")) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
//                String ba = sharedPref.getString("lunch_main", "");
//                Toast.makeText(getApplicationContext(), ba, Toast.LENGTH_SHORT).show();
//                String bb = sharedPref.getString("lunch_side", "");
//                Toast.makeText(getApplicationContext(), bb, Toast.LENGTH_SHORT).show();
//                String bc = sharedPref.getString("lunch_beverage", "");
//                Toast.makeText(getApplicationContext(), bc, Toast.LENGTH_SHORT).show();
            }

            if (!dMain.equals("") && !dSide.equals("") && !dBeverage.equals("")) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
//                String ca = sharedPref.getString("dinner_main", "");
//                Toast.makeText(getApplicationContext(), ca, Toast.LENGTH_SHORT).show();
//                String cb = sharedPref.getString("dinner_side", "");
//                Toast.makeText(getApplicationContext(), cb, Toast.LENGTH_SHORT).show();
//                String cc = sharedPref.getString("dinner_beverage", "");
//                Toast.makeText(getApplicationContext(), cc, Toast.LENGTH_SHORT).show();
            }

    }

    public int foodCalc(String bMain, String lMain, String dMain,
                         String bSide, String lSide, String dSide,
                         String bBeverage, String lBeverage, String dBeverage){
        int Eggs = 90;
        int Toast = 75;
        int Cereal = 307;
        int Waffles = 82;
        int Protein = 262;
        int Chicken = 335;
        int Beef = 213;
        int Fish = 366;
        int Pizza = 570;
        int Pasta = 150;

        int Sausage = 229;
        int Bacon = 132;
        int Fruit = 70;
        int Hash = 470;
        int Salad = 150;
        int Bread = 77;
        int Veggie = 59;
        int Rice = 206;

        int Orange = 39;
        int Apple = 113;
        int Milk = 103;
        int Coffee = 1;
        int Soda = 150;
        int Tea = 2;
        int Water = 0;

        bMain = mainBreakfastSpinner.getSelectedItem().toString();
        lMain = mainLunchSpinner.getSelectedItem().toString();
        dMain = mainDinnerSpinner.getSelectedItem().toString();
        bSide = sideBreakfastSpinner.getSelectedItem().toString();
        lSide = sideLunchSpinner.getSelectedItem().toString();
        dSide = sideDinnerSpinner.getSelectedItem().toString();
        bBeverage = breakfastBeverageSpinner.getSelectedItem().toString();
        lBeverage = lunchBeverageSpinner.getSelectedItem().toString();
        dBeverage = dinnerBeverageSpinner.getSelectedItem().toString();

        int total = 0;
        String[] a = {"Eggs","Toast","Cereal","Waffles","Protein Shake","Chicken","Beef",
                "Fish","Pizza","Pasta","Sausage","Bacon","Fruit","Hash browns","Salad","Bread","Vegetables",
                "Rice","Orange juice","Apple juice","Milk","Coffee","Soda","Tea", "Water"};
        int[] b = {Eggs, Toast, Cereal, Waffles, Protein, Chicken, Beef, Fish, Pizza, Pasta, Sausage,
                Bacon, Fruit, Hash, Salad, Bread, Veggie, Rice, Orange, Apple, Milk, Coffee, Soda, Tea, Water};

        for(int i = 0; i < a.length; i ++){
            if (bMain.equals(a[i])){
                total += b[i];
            }if (lMain.equals(a[i])){
                total += b[i];
            }if (dMain.equals(a[i])){
                total += b[i];
            }if (bSide.equals(a[i])){
                total += b[i];
            }if (lSide.equals(a[i])){
                total += b[i];
            }if (dSide.equals(a[i])){
                total += b[i];
            }if (bBeverage.equals(a[i])){
                total += b[i];
            }if (lBeverage.equals(a[i])){
                total += b[i];
            }if (dBeverage.equals(a[i])){
                total += b[i];
            }
        }
//        if (bMain.equals("Eggs")){
//            total += Eggs;
//        }
//        if (bMain.equals("Toast")){
//            total += Toast;
//        }
//        if (bMain.equals("Cereal")){
//            total =+ Cereal;
//        }
//        if (bMain.equals("Waffles")){
//            total =+ Waffles;
//        }
//        if (bMain.equals("Protein Shake")){
//            total =+ Protein;
//        }
//        if (lMain.equals("Chicken")){
//            total =+ Chicken;
//        }
//        if (lMain.equals("Beef")){
//            total =+ Beef;
//        }
//        if (lMain.equals("Fish")){
//            total =+ Fish;
//        }
//        if (lMain.equals("Pizza")){
//            total =+ Pizza;
//        }
//        if (lMain.equals("Pasta")){
//            total =+ Pasta;
//        }
//        if (lMain.equals("Protein Shake")){
//            total =+ Protein;
//        }
//        if (dMain.equals("Chicken")){
//            total =+ Chicken;
//        }
//        if (dMain.equals("Beef")){
//            total =+ Beef;
//        }
//        if (dMain.equals("Fish")){
//            total =+ Fish;
//        }
//        if (dMain.equals("Pizza")){
//            total =+ Pizza;
//        }
//        if (dMain.equals("Pasta")){
//            total =+ Pasta;
//        }
//        if (dMain.equals("Protein Shake")){
//            total += Protein;
//        }

        return total;
    }



    public static void setDefaults(String key, String value, Context context) {
        String total = "total";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.putString("total", total);
        editor.commit();
    }


}
