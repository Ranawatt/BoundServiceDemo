package com.example.boundservicedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.class";
    private TextView textView;
    private ProgressBar progressBar;
    private Button button;

    private  MyService myService;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggelUpdate();
                startMyService();
            }
        });
        mainViewModel.getBinder().observe(this, new Observer<MyService.MyBinder>() {
            @Override
            public void onChanged(MyService.MyBinder myBinder) {
                if(myBinder != null){
                    Log.d(TAG, "onChanged : Service Connected");
                    myService = myBinder.getService();
                }else{
                    Log.d(TAG, "onChanged : Service Disconnected");
                    myService =  null;
                }
            }
        });

        mainViewModel.getIsProgressUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean aBoolean) {

                final  Handler handle= new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (aBoolean){
                            if(mainViewModel.getBinder().getValue()!= null){
                                if(myService.getProgress() == myService.getMaxValue()){
                                    mainViewModel.setIsUpdating(false);
                                }
                                progressBar.setProgress(myService.getProgress());
                                progressBar.setMax(myService.getMaxValue());
                                String progress = String.valueOf(100*myService.getProgress() / myService.getMaxValue())+"%";
                                textView.setText(progress);
                                handle.postDelayed(this, 100);
                            }else {
                                handle.removeCallbacks(this);
                            }
                        }
                    }
                };

                if (aBoolean){
                    button.setText("Pause");
                    handle.postDelayed(runnable,100);
                }else{
                    if (myService.getProgress() == myService.getMaxValue()){
                        button.setText("Restart");
                    }else{
                        button.setText("Start");
                    }
                }

            }
        });
    }

    private void toggelUpdate() {
        if(myService != null){
            if (myService.getProgress() >= myService.getMaxValue()){
                myService.getResetTask();
                button.setText("Start");
            }else{
                if(myService.getIsPaused()){
                    myService.unPausePretendLongRunningTasks();
                    mainViewModel.setIsUpdating(true);
                }else{
                    myService.pausePretendLongRunningTasks();
                    mainViewModel.setIsUpdating(false);
                }
            }
        }
    }

    public void startMyService(){
        Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
        startService(serviceIntent);

        bindService();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mainViewModel.getBinder()!= null){
            unbindService(mainViewModel.getServiceConnection());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMyService();
    }

    private void bindService(){
        Intent serviceIntent  =  new Intent(MainActivity.this, MyService.class);
        bindService(serviceIntent,mainViewModel.getServiceConnection(), Context.BIND_AUTO_CREATE);
    }
}
