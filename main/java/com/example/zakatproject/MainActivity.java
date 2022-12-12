package com.example.zakatproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnKira, btnTetap;
    EditText textW, textV;
    TextView total1, total2, total3;
    RadioGroup radioGroup;
    RadioButton rdK, rdW;
    float GoldWeight, GoldValue, GoldType, tot1, tot2, tot3;

    SharedPreferences sharedPref1;
    SharedPreferences sharedPref2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKira = (Button) findViewById(R.id.btnKira);
        btnTetap = (Button) findViewById(R.id.btnTetap);
        textW = (EditText) findViewById(R.id.textW);
        textV = (EditText) findViewById(R.id.textV);
        total1 = (TextView) findViewById(R.id.total1);
        total2 = (TextView) findViewById(R.id.total2);
        total3 = (TextView) findViewById(R.id.total3);
        rdK = (RadioButton) findViewById(R.id.rdK);
        rdW = (RadioButton) findViewById((R.id.rdW));
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        boolean rdKChecked = rdK.isChecked();
        boolean rdWChecked = rdW.isChecked();

        //load();
        btnKira.setOnClickListener(this);
        btnTetap.setOnClickListener(this);

        //GoldWeight = 0;
        //GoldValue = 0;
        //GoldType = 0;

        sharedPref1 = this.getSharedPreferences("GoldWeight", Context.MODE_PRIVATE);
        sharedPref2 = this.getSharedPreferences("GoldValue", Context.MODE_PRIVATE);

        float GoldWeight = sharedPref1.getFloat("GoldWeight", 0);
        float GoldValue = sharedPref2.getFloat("GoldValue", 0);

        textW.setText(""+GoldWeight);
        textV.setText(""+GoldValue);


    }

    @Override
   public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnKira:
                calculate();
                break;
            case R.id.btnTetap:
                reset();
                resettu();
                break;
        }

    }

    public void calculate(){

        try {
            GoldWeight = Float.parseFloat(textW.getText().toString());

            if (rdK.isChecked())
                GoldType= 85;
            else if (rdW.isChecked())
                GoldType = 200;
                GoldValue = Float.parseFloat(textV.getText().toString());

            tot1 = GoldWeight * GoldValue;
            tot2 = (GoldWeight - GoldType) * GoldValue;
            tot3 = (float)(tot2 * 0.025);


            total1.setText("total gold value is RM "+String.format("%.2f",tot1));
            total2.setText("total zakat payable is RM "+String.format("%.2f",tot2));
            total3.setText("total zakat is RM "+String.format("%.2f",tot3));

            SharedPreferences.Editor editor1 = sharedPref1.edit();
            editor1.putFloat("GoldWeight", GoldWeight);
            editor1.apply();

            SharedPreferences.Editor editor2 = sharedPref2.edit();
            editor2.putFloat("GoldValue", GoldValue);
            editor2.apply();


        }catch (java.lang.NumberFormatException nfe) {
                Toast.makeText(this, "Input Missing!", Toast.LENGTH_SHORT).show();
            }
        }

        public void reset(){

        textW.setText("");
        textV.setText("");

        //total1.setText("total gold value");
        //total2.setText("total zakat payable");
        //total3.setText("total zakat");


        }
        public void resettu(){
        rdK.setChecked(false);
        rdW.setChecked(false);
            //radioGroup.clearCheck();
        }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //public void load() {
      //  sharedPref1 = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
      //  GoldWeight = sharedPref1.getFloat("weight", 0); //load data weight
      //  textW.setText(" " + GoldWeight);


       // sharedPref2 = this.getSharedPreferences("gold", Context.MODE_PRIVATE);
       // GoldValue = sharedPref2.getFloat("gold", 0); //load data gold value
        //textV.setText(" " + GoldValue);

    //}

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                Intent intent1 = new Intent(this, About.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

   }