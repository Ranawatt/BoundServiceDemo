package com.example.boundservicedemo.intentservice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.boundservicedemo.R;

import static com.example.boundservicedemo.foregroundservice.App.CHANNEL_ID;

class ExampleIntentService extends IntentService {

    public static final String TAG = "ExampleINTENTService";
    public ExampleIntentService() {
        super("ExampleIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Notification notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("New message")
                    .setContentText("Message Has been DELIVERED")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"OnHandleIntent");
        String input = intent.getStringExtra("inputExtra");

        for (int i=0;i<10;i++){
            Log.d(TAG, input+" - "+ i);
            SystemClock.sleep(1000);
        }
    }
}
