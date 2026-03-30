package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class maritalStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marital_status);

        Button cancel = findViewById(R.id.maritalCancel);
        Button submit = findViewById(R.id.maritalSubmit);
        RadioGroup rdMarital = findViewById(R.id.radioGroupMarital);


        //create onclick listeners

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(maritalStatus.this, DemographicInfo.class);
                startActivity(n);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedID = rdMarital.getCheckedRadioButtonId();
                RadioButton clicked = findViewById(clickedID);
                String buttonText = clicked.getText().toString();
                if(rdMarital.getCheckedRadioButtonId() != -1){
                    Intent n = new Intent(maritalStatus.this, DemographicInfo.class);
                    n.putExtra("marital", buttonText);
                    startActivity(n);
                }
                else{
                    Toast.makeText(maritalStatus.this, "Please Select an Option !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}