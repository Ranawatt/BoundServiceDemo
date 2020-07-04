package com.example.boundservicedemo.foregroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boundservicedemo.R;

public class ForegroundActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);
        editText = findViewById(R.id.etTextInput);
    }

    public void startService(View view) {
        String input = editText.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra",input);
//        startService(serviceIntent);
        ContextCompat.startForegroundService(this,serviceIntent);

    }

    public void stopService(View view) {
        Intent  serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}