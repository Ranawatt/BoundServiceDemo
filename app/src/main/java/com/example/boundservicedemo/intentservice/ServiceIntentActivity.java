package com.example.boundservicedemo.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.boundservicedemo.R;

public class ServiceIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_intent);
    }

    public void startService(View view) {
    }

    public void stopService(View view) {
    }
}