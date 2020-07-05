package com.example.boundservicedemo.jobintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

class ExampleJobIntentService extends JobIntentService {
    public static final String TAG="ExampleJobIntentService";

    static void enqueueWork(Context context, Intent work){
        enqueueWork(context,ExampleJobIntentService.class,123, work);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreated");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork");
        String input = intent.getStringExtra("inputExtra");

        for(int i=0;i<10;i++){
            Log.d(TAG, input +" - "+ i);
            if (isStopped()){ return;}
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
