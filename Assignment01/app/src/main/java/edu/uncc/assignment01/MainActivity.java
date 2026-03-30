package edu.uncc.assignment01;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup percent = findViewById(R.id.radioGroup);
        RadioButton defaultRadioButton = (RadioButton) percent.getChildAt(0);
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        TextView custom = findViewById(R.id.textView8);
        TextView Discount = findViewById(R.id.textView5);
        TextView FinalPrice = findViewById(R.id.textView7);
        EditText Price = findViewById(R.id.itemPrice);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: cha");
                custom.setText("" + progress + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onProgressChanged: cha");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
                Log.d(TAG, "onProgressChanged: cha");

            }
        });

        Button reset = findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Reset");
                //clear the Item Price field
                Price.getText().clear();
                //Change the percentage to be a default of 10
                defaultRadioButton.setChecked(true);
                //change the custom sale % amount
                seekBar1.setProgress(25);
                //Reset the Discount and the final price
                Discount.setText("0.00");
                FinalPrice.setText("0.00");
            }

        });
        Button calculate = findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Calculate");

                // Validate the entered price
                if (Price.getText().toString().isEmpty()) {
                     Toast.makeText(MainActivity.this, "Enter a valid weight !!", Toast.LENGTH_SHORT).show(); ;
                       return;
                }

                 if(((RadioButton) percent.getChildAt(0)).isChecked()){
                    Discount.setText( String.valueOf(Double.parseDouble(Price.getText().toString())*0.10));
                    FinalPrice.setText(String.valueOf(Double.parseDouble(Price.getText().toString())-Double.parseDouble(Discount.getText().toString())));
                 }
                else if(((RadioButton) percent.getChildAt(1)).isChecked()){
                    Discount.setText(String.valueOf(Double.parseDouble(Price.getText().toString())* 0.15));
                     FinalPrice.setText(String.valueOf(Double.parseDouble(Price.getText().toString())-Double.parseDouble(Discount.getText().toString())));
                }
                else if(((RadioButton) percent.getChildAt(2)).isChecked()){
                    Discount.setText(String.valueOf(Double.parseDouble(Price.getText().toString())*0.18));
                     FinalPrice.setText(String.valueOf(Double.parseDouble(Price.getText().toString())-Double.parseDouble(Discount.getText().toString())));
                }
                else if(((RadioButton) percent.getChildAt(3)).isChecked()){
                     Discount.setText(String.valueOf(Double.parseDouble(Price.getText().toString())* (seekBar1.getProgress()/(double)100) ));
                     FinalPrice.setText(String.valueOf(Double.parseDouble(Price.getText().toString())-Double.parseDouble(Discount.getText().toString())));
                 }

            }
        });

    }


    }