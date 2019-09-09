package com.example.homework1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    private TextView tv_bmi;
    private TextView tv_bmires;
    private Button calc_btn;
    private EditText et_weight;
    private EditText et_feet;
    private EditText et_inches;

    private float weight = 0;
    private int feet = 0;
    private int inches = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("BMI Calculator");

        //Get all view fields into variables
        tv_bmi = findViewById(R.id.tv_bmi);
        tv_bmires = findViewById(R.id.tv_bmires);
        et_weight = findViewById(R.id.et_weight);
        et_feet = findViewById(R.id.et_feet);
        et_inches = findViewById(R.id.et_inches);
        calc_btn = findViewById(R.id.calc_btn);


        //Set OnCLick event listener on 'calc_btn'
        calc_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                tv_bmi.setText("");
                tv_bmires.setText("");

                int showResults = 0;    //showResults is used to determine if the result is to be displayed or not. If all input fields are valid show results, else not.

                String tempWeight = et_weight.getText().toString();

                String tempFeet = et_feet.getText().toString();

                String tempInch = et_inches.getText().toString();

                if(tempWeight.equals("") || !isNumeric(tempWeight)){

                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                    et_weight.setError("Please Enter a Appropriate Value");
                    showResults = -1;

                } else {
                    weight = Float.parseFloat(tempWeight);
                    showResults++;
                }
                if(tempFeet.equals("") || !isNumeric(tempFeet)){
                    et_feet.setError("Please Enter a Appropriate Value");
                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                    showResults = -1;
                } else {
                    try{
                        feet = Integer.parseInt(tempFeet);
                        showResults++;
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Invalid inputs.",Toast.LENGTH_SHORT).show();
                        showResults = -1;
                    }

                }
                if(tempInch.equals("") || !isNumeric(tempInch)){
                    et_inches.setError("Please Enter a Appropriate Value");
                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                    showResults = -1;
                } else {
                    try{
                        inches = Integer.parseInt(tempInch);
                        showResults++;
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Invalid inputs.",Toast.LENGTH_SHORT).show();
                        showResults = -1;
                    }

                }

                double finalHeight = inches + (feet * 12);

                double bmi = (weight / Math.pow(finalHeight, 2)) * 703;

                tv_bmi.setText(String.format("%s%s", getString(R.string.dmi), bmi));

                if(bmi < 18.5) {
                    tv_bmires.setText(getString(R.string.underweight));
                } else if(bmi >= 18.5 && bmi <= 24.9) {
                    tv_bmires.setText(getString(R.string.normalweight));
                } else if(bmi >= 25 && bmi <= 29.9) {
                    tv_bmires.setText(getString(R.string.overweight));
                } else {
                    tv_bmires.setText(getString(R.string.obese));
                }

                //Hide keyboard after the results are displayed
                try{
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                }catch (Exception e)
                {

                }

                //Show toaster if all fields are valid.
                if(showResults>=2)
                {
                    Toast.makeText(getApplicationContext(),"BMI Calculated",Toast.LENGTH_SHORT).show();
                }
                else    //Else remove all previous result text
                {
                    tv_bmi.setText("");
                    tv_bmires.setText("");
                }


            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
