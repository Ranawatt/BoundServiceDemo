package com.example.boundservicedemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

class ExampleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
//            Toast.makeText(context,"Boot Completed",Toast.LENGTH_SHORT).show();
//        }

//        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
//            Toast.makeText(context,"Connectivity Changed", Toast.LENGTH_SHORT).show();
//            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
//            if (noConnectivity){
//                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
//            }
//        }

//        TODO For creating CustomBroadcast

        if ("com.example.boundservicedemo.EXAMPLE_ACTION".equals(intent.getAction())){
            String receivedText = intent.getStringExtra("com.example.boundservicedemo.EXTRA_TEXT");
            Toast.makeText(context, receivedText,Toast.LENGTH_LONG).show();
        }
    }
}
