package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemographicInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic_info);
        Button education = findViewById(R.id.select1);
        Button marital = findViewById(R.id.select2);
        Button living = findViewById(R.id.select3);
        Button income = findViewById(R.id.select4);
        Button next = findViewById(R.id.demoNext);
        TextView educationVal = findViewById(R.id.educationVal);
        TextView maritalVal = findViewById(R.id.maritalVal);
        TextView livingVal = findViewById(R.id.livingVal);


        //onclick attribute for the select button

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemographicInfo.this, education_level.class );
                startActivity(intent);
            }
        });
        marital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemographicInfo.this, maritalStatus.class );
                startActivity(intent);
            }
        });
        living.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemographicInfo.this, livingStatus.class );
                startActivity(intent);
            }
        });
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemographicInfo.this, income.class );
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemographicInfo.this, profile.class );
                startActivity(intent);
            }
        });
        // Recieve the value back from the Previous Screens
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("education")){
            educationVal.setText(getIntent().getStringExtra("education"));
        }
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("marital")){
            maritalVal.setText(getIntent().getStringExtra("marital"));
        }
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("living")){
            livingVal.setText(getIntent().getStringExtra("living"));
        }


    }
}