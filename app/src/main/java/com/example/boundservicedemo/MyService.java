package com.example.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {
    private static final String TAG = "MyService.class";

    private  IBinder mBinder = new MyBinder();
    private  Handler mHandler ;
    private  Integer mProgressValue, mMaxValue ;
    private  Boolean mIsPaused ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mProgressValue = 0;
        mIsPaused = true;
        mMaxValue = 5000;
    }

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }

    }

    public void startPretendLongRunningTasks(){
        final Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mProgressValue >= mMaxValue || mIsPaused){
                    Log.d(TAG,"run : removing callbacks");
                    mHandler.removeCallbacks(this);
                    pausePretendLongRunningTasks();
                }else {
                    Random random =  new Random();
                    int number = random.nextInt(100);
                    Log.d(TAG, "run:progress:"+mProgressValue);

                    mProgressValue += number;
                    mHandler.postDelayed(this, 100);
                }
            }
        };
        mHandler.postDelayed(mRunnable,100);
    }

    public void pausePretendLongRunningTasks() {
        mIsPaused = true;
    }

    public void unPausePretendLongRunningTasks(){
        mIsPaused = false;
        startPretendLongRunningTasks();
    }

    public Boolean getIsPaused(){
        return  mIsPaused;
    }

    public int getProgress(){
        return mProgressValue;
    }

    public int getMaxValue(){
        return mMaxValue;
    }

    public void getResetTask(){
        mProgressValue = 0;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }
}
