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

        tv_bmi = findViewById(R.id.tv_bmi);
        tv_bmires = findViewById(R.id.tv_bmires);
        et_weight = findViewById(R.id.et_weight);
        et_feet = findViewById(R.id.et_feet);
        et_inches = findViewById(R.id.et_inches);
        calc_btn = findViewById(R.id.calc_btn);

        tv_bmi.setText("");
        tv_bmires.setText("");

        calc_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String tempLen1 = et_weight.getText().toString();

                String tempLen2 = et_feet.getText().toString();

                String tempLen3 = et_inches.getText().toString();

                if(tempLen1.equals("") || !isNumeric(tempLen1)){
                    et_weight.setError("Please Enter a Appropriate Value");
                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                } else {
                    weight = Float.parseFloat(tempLen1);
                }
                if(tempLen2.equals("") || !isNumeric(tempLen2)){
                    et_feet.setError("Please Enter a Appropriate Value");
                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                } else {
                    feet = Integer.parseInt(tempLen2);
                }
                if(tempLen3.equals("") || !isNumeric(tempLen3)){
                    et_inches.setError("Please Enter a Appropriate Value");
                    tv_bmi.setText(getString(R.string.validval));
                    tv_bmires.setText("");
                } else {
                    inches = Integer.parseInt(tempLen3);
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

                Toast.makeText(getApplicationContext(),"BMI Calculated",Toast.LENGTH_SHORT).show();

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
