package com.example.danielstephens.caloriecounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class PageTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_two);

        final Spinner questionOne, questionTwo;

        ArrayAdapter <CharSequence> questionOneAdapter, questionTwoAdapter;

        final ImageView redX = (ImageView)(findViewById(R.id.x_image));
        final TextView txtDontEat = (TextView)(findViewById(R.id.txtDontEat));
        final TextView txtQOne = (TextView)(findViewById(R.id.txtPageTwoQuestionOne));
        final TextView textView = (TextView)findViewById(R.id.txtPageTwoQuestionTwo);
        final TextView txtBreakfastMain = (TextView)(findViewById(R.id.txtBreakfastDisplayM));
        final TextView txtBreakfastSide = (TextView)(findViewById(R.id.txtBreakfastDisplayS));
        final TextView txtBreakfastBev = (TextView)(findViewById(R.id.txtBreakfastDisplayB));


        questionOne = (Spinner) findViewById(R.id.questionOneSpinner);
        questionOneAdapter = ArrayAdapter.createFromResource(this,R.array.page_two_question_one, android.R.layout.simple_spinner_item);
        questionOneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        questionTwo = (Spinner) findViewById(R.id.questionTwoSpinner);
        questionTwoAdapter = ArrayAdapter.createFromResource(this,R.array.page_two_question_two, android.R.layout.simple_spinner_item);
        questionTwoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        questionTwo.setAdapter(questionTwoAdapter);
        questionOne.setAdapter(questionOneAdapter);


        questionOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String qOne = questionOne.getSelectedItem().toString();

                if (!qOne.equals("Select Yes or No")) {
                    if (qOne.equals("No") || qOne.equals("I am not sure...")) {
                        redX.setVisibility(View.VISIBLE);
                        txtDontEat.setVisibility(View.VISIBLE);
                        questionOne.setVisibility(View.INVISIBLE);
                        txtQOne.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        txtBreakfastMain.setVisibility(View.INVISIBLE);
                        txtBreakfastSide.setVisibility(View.INVISIBLE);
                        txtBreakfastBev.setVisibility(View.INVISIBLE);

                    }
                }
                if (qOne.equals("YES")){
                    textView.setVisibility(View.VISIBLE);
                    questionTwo.setVisibility(View.VISIBLE);
                    questionTwo.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        questionTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String qTwo = questionTwo.getSelectedItem().toString();

                if (!qTwo.equals("Select Meal")){
                    if (qTwo.equals("Breakfast")){
                        txtBreakfastMain.setText("Eggs = 90\n" +
                                "\n"+
                                "Toast = 75\n" +
                                "\n"+
                                "Cereal = 307\n" +
                                "\n"+
                                "Waffles = 82\n" +
                                "\n"+
                                "Protein = 262");
                        txtBreakfastSide.setText("Sausage = 229\n" +
                                "\n"+
                                "Bacon = 132\n" +
                                "\n"+
                                "Fruit = 70\n" +
                                "\n"+
                                "Hash browns = 470");
                        txtBreakfastBev.setText("Orange juice = 39\n" +
                                "\n"+
                                "Apple juice = 113\n" +
                                "\n"+
                                "Milk = 103\n" +
                                "\n"+
                                "Coffee = 1\n" +
                                "\n"+
                                "Tea = 2\n" +
                                "\n"+
                                "Water = 0");
                    }
                    else if (qTwo.equals("Lunch")||qTwo.equals("Dinner")){
                        txtBreakfastMain.setText("");
                        txtBreakfastSide.setText("");
                        txtBreakfastBev.setText("");

                        txtBreakfastMain.setText("Chicken = 335\n" +
                                "\n"+
                                "Beef = 213\n" +
                                "\n"+
                                "Fish = 366\n" +
                                "\n"+
                                "Pizza = 570\n" +
                                "\n"+
                                "Pasta = 150");
                        txtBreakfastSide.setText("Salad = 150\n" +
                                "\n"+
                                "Bread = 77\n" +
                                "\n"+
                                "Veggie = 59\n" +
                                "\n"+
                                "Rice = 206");
                        txtBreakfastBev.setText("Coffee = 1\n" +
                                "\n"+
                                "Soda = 150\n" +
                                "\n"+
                                "Tea = 2\n" +
                                "\n"+
                                "Water = 0");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
