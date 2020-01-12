package com.example.boundservicedemo;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MainViewModel() {
    }

    private static final String TAG = "MainViewModel.class";

    private MutableLiveData<Boolean> mIsProgressUpdating = new MutableLiveData<>();

    private MutableLiveData<MyService.MyBinder> mBinder = new MutableLiveData<>();

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected : connectedToService");
            MyService.MyBinder myBinder = (MyService.MyBinder)iBinder;
            mBinder.postValue(myBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            mBinder.postValue(null);
        }
    };
    public LiveData<Boolean> getIsProgressUpdating(){
        return  mIsProgressUpdating;
    }

    public LiveData<MyService.MyBinder> getBinder(){
        return mBinder;
    }

    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }

    public void setIsUpdating(Boolean isUpdating){
        mIsProgressUpdating.postValue(isUpdating);
    }
}
