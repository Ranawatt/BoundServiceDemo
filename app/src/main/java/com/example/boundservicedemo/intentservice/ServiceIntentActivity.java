package com.example.boundservicedemo.intentservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boundservicedemo.R;

public class ServiceIntentActivity extends AppCompatActivity {

    private EditText editTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_intent);
        editTextInput = findViewById(R.id.etTextInput);
    }

    public void startService(View view) {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleIntentService.class);
        serviceIntent.putExtra("inputExtra", input);
//        startService(serviceIntent);
        ContextCompat.startForegroundService(this,serviceIntent);
    }

    public void stopService(View view) {
    }
}