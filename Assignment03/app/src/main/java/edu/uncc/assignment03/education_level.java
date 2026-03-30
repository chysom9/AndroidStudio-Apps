package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class education_level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_level);

        Button cancel = findViewById(R.id.education8);
        Button submit = findViewById(R.id.education9);
        RadioGroup education = findViewById(R.id.educationGroup);

        // onclick listeners
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedID = education.getCheckedRadioButtonId();
                RadioButton clicked = findViewById(clickedID);
                String buttonText = clicked.getText().toString();
              if(education.getCheckedRadioButtonId() != -1){
                  Intent n = new Intent(education_level.this, DemographicInfo.class);
                  n.putExtra("education", buttonText);
                  startActivity(n);
                  finish();
              }


                else{
                    Toast.makeText(education_level.this, "Enter a valid weight !!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}