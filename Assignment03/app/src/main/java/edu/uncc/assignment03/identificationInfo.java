package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class identificationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_info);
        Button next = findViewById(R.id.IdentInfoButton);
        EditText name = findViewById(R.id.identInputName);
        EditText email = findViewById(R.id.identInputEmail);
        RadioGroup rdIdent = findViewById(R.id.rdIdent);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || rdIdent.getCheckedRadioButtonId() == -1){
                    Toast.makeText(identificationInfo.this, "Please input a valid response !!", Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent = new Intent(identificationInfo.this, DemographicInfo.class);
                    startActivity(intent);
                }

            }
        });
    }
}