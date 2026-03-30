package edu.uncc.bofademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

import java.util.Map;

public class FormPreviewActivity extends AppCompatActivity {
    private Button submit;
    private TextView formDataTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_preview);

        submit = findViewById(R.id.submitButton);

        formDataTextView = findViewById(R.id.formDataTextView);
        Map<String, String> formData = (Map<String, String>) getIntent().getSerializableExtra("formData");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(FormPreviewActivity.this, Summary.class);
                startActivity(a);

            }
        });
        StringBuilder formDataText = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            formDataText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        formDataTextView.setText(formDataText.toString());

    }
}