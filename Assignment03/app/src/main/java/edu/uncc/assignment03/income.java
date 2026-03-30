
package edu.uncc.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class income extends AppCompatActivity {
    public String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Button cancel = findViewById(R.id.incomeCancel);
        Button submit = findViewById(R.id.incomeSubmit);
        SeekBar range = findViewById(R.id.seekBar);
        TextView incomeVal = findViewById(R.id.incVal);

        range.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress){
                    case 0: val = "<25K";
                        break;
                    case 1: val = "<$25K to <$50K";
                        break;
                    case 2: val = "$50K to <$100K";
                        break;
                    case 3: val = "$100K to <$200K";
                        break;
                    case 4: val = ">$200K";
                        break;
                }
                incomeVal.setText(String.valueOf(val));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(income.this, DemographicInfo.class);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}