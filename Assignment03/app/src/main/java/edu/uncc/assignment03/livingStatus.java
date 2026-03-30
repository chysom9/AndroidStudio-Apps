package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class livingStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_status);


        Button cancel = findViewById(R.id.livingCancel);
        Button submit = findViewById(R.id.livingSubmit);
        RadioGroup rdLiving = findViewById(R.id.radioGroupLiving);


        //create onclick listeners

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(livingStatus.this, DemographicInfo.class);
                startActivity(n);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedID = rdLiving.getCheckedRadioButtonId();
                RadioButton clicked = findViewById(clickedID);
                String buttonText = clicked.getText().toString();
                if(rdLiving.getCheckedRadioButtonId() != -1){
                    Intent n = new Intent(livingStatus.this, DemographicInfo.class);
                    n.putExtra("living", buttonText);

                }
                else{
                    Toast.makeText(livingStatus.this, "Please Select an Option !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
