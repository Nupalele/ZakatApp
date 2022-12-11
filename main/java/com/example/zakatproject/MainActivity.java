package com.example.zakatproject;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        btnKira.setOnClickListener(this);
        btnTetap.setOnClickListener(this);


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


        }catch (java.lang.NumberFormatException nfe) {
                Toast.makeText(this, "Input Missing!", Toast.LENGTH_SHORT).show();
            }
        }

        public void reset(){

        textW.setText("");
        textV.setText("");

        total1.setText("total gold value");
        total2.setText("total zakat payable");
        total3.setText("total zakat");


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