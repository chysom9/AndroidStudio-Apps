package edu.uncc.bofademo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EricaActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private TextView ericaTextView;
    private EditText userInputEditText;
    private EditText newUserInputEditText;
    private FloatingActionButton sendButton;
    private FloatingActionButton newSendButton;
    private FloatingActionButton initialSpeechButton;
    private FloatingActionButton newSpeechButton;
    private int questionIndex = 0;
    private Map<String, String> formData = new HashMap<>();
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private boolean isInitialResponse = true; // Flag to differentiate between stages

    private HistoryAdapter historyAdapter;
    private List<QuestionAnswerPair> historyList = new ArrayList<>();

    private ConstraintLayout section1;
    private ConstraintLayout section2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erica_screen);

        ericaTextView = findViewById(R.id.ericaTextView);
        userInputEditText = findViewById(R.id.userInputEditText);
        newUserInputEditText = findViewById(R.id.newUserInputEditText);
        sendButton = findViewById(R.id.sendButton);
        newSendButton = findViewById(R.id.newSendButton);
        initialSpeechButton = findViewById(R.id.initialSpeechButton);
        newSpeechButton = findViewById(R.id.speechButton);
        RecyclerView historyRecyclerView = findViewById(R.id.historyRecyclerView);
        section1 = findViewById(R.id.section1);
        section2 = findViewById(R.id.section2);

        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(historyList);
        historyRecyclerView.setAdapter(historyAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInitialResponse();
            }
        });

        newSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUserResponse();
            }
        });

        initialSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInitialResponse = true;
                startListening();
            }
        });

        newSpeechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInitialResponse = false;
                startListening();
            }
        });

        userInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    handleInitialResponse();
                    return true;
                }
                return false;
            }
        });

        newUserInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    handleUserResponse();
                    return true;
                }
                return false;
            }
        });

        setupSpeechRecognizer();
    }

    private void handleInitialResponse() {
        String response = userInputEditText.getText().toString();
        if (response.isEmpty()) {
            Toast.makeText(this, "Please provide a response", Toast.LENGTH_SHORT).show();
        } else {
            userInputEditText.setText("");
            transitionToNextSection();
        }
    }

    private void transitionToNextSection() {
        section1.setVisibility(View.GONE);
        section2.setVisibility(View.VISIBLE);
        askQuestion();
    }

    private void askQuestion() {
        String[] questions = {
                "Gotcha, I can help you apply for a credit card. Lets start with your full name?",
                "Great, what's your date of birth?",
                "Got it. What's your address?",
                "Thanks! What's your phone number?",
                "What's your email address?",
                "Finally, what's your annual income?"
        };
        ericaTextView.setText(questions[questionIndex]);
    }

    private void handleUserResponse() {
        String[] keys = {"Full Name", "Date of Birth", "Address", "Phone Number", "Email", "Annual Income"};

        String response = newUserInputEditText.getText().toString();
        if (response.isEmpty()) {
            Toast.makeText(this, "Please put in a meaningful value", Toast.LENGTH_SHORT).show();
        } else {
            formData.put(keys[questionIndex], response);
            historyList.add(new QuestionAnswerPair(ericaTextView.getText().toString(), response));
            historyAdapter.notifyItemInserted(historyList.size() - 1);

            newUserInputEditText.setText("");
            questionIndex++;

            if (questionIndex < keys.length) {
                askQuestion();
            } else {
                previewForm();
            }
        }
    }

    private void previewForm() {
        Intent intent = new Intent(EricaActivity.this, FormPreviewActivity.class);
        intent.putExtra("formData", (Serializable) formData);
        startActivity(intent);
    }

    private void setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                String errorMessage;
                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        errorMessage = "Audio recording error";
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        errorMessage = "Client-side error";
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        errorMessage = "Insufficient permissions";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        errorMessage = "Network error";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        errorMessage = "Network timeout";
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                        errorMessage = "No match found";
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        errorMessage = "Recognizer busy";
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                        errorMessage = "Server error";
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        errorMessage = "No speech input";
                        break;
                    default:
                        errorMessage = "Unknown error";
                        break;
                }
                ericaTextView.setText("Error: " + errorMessage);
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    if (isInitialResponse) {
                        userInputEditText.setText(matches.get(0));
                        handleInitialResponse();
                    } else {
                        newUserInputEditText.setText(matches.get(0));
                        handleUserResponse();
                    }
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });
    }

    private void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            speechRecognizer.startListening(speechRecognizerIntent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    private void startListeningForInitialResponse() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            isInitialResponse = true;
            speechRecognizer.startListening(speechRecognizerIntent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isInitialResponse) {
                    startListeningForInitialResponse();
                } else {
                    startListening();
                }
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}


