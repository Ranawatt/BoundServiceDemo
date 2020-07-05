package com.example.boundservicedemo.intentservice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.boundservicedemo.R;

import static com.example.boundservicedemo.foregroundservice.App.CHANNEL_ID;

public class ExampleIntentService extends IntentService {

    public static final String TAG = "ExampleINTENTService";
    private PowerManager.WakeLock wakeLock;
    public ExampleIntentService() {
        super("ExampleIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"ExampleApp : Wakelock");
        wakeLock.acquire(600000);
        Log.d(TAG,"Wakelock acquired");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Notification notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("New message")
                    .setContentText("Message Has been DELIVEREING...")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
            startForeground(1,notification);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"OnHandleIntent");
        String input = intent.getStringExtra("inputExtra");

        for (int i=0;i<100;i++){
            Log.d(TAG, input+" - "+ i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        wakeLock.release();
        Log.d(TAG,"Wakelock  released");
    }
}
