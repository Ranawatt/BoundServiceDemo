package com.example.boundservicedemo.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.boundservicedemo.R;

public class BroadcastActivity extends AppCompatActivity {

    private ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

//        created intent filter in onCreate() for customReceiver then destroyed in onDestroy()
//        To receive custom broadcast, created another project AppBroadcastSender
        IntentFilter filter =  new IntentFilter("com.example.boundservicedemo.EXAMPLE_ACTION");
        registerReceiver(exampleBroadcastReceiver,filter);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        IntentFilter filter =  new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(exampleBroadcastReceiver,filter);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(exampleBroadcastReceiver);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exampleBroadcastReceiver);
    }
}