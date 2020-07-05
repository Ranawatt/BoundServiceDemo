package com.example.boundservicedemo.jobintentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boundservicedemo.R;

public class ServicejobIntentActivity extends AppCompatActivity {

    private EditText editTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicejob_intent);
        editTextInput = findViewById(R.id.etTextInput);
    }

    public void startService(View view) {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this,ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra",input);
        startService(serviceIntent);
    }
}