package com.sam.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BootCompleteReceiver extends BroadcastReceiver {

    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent serviceIntent = new Intent(context, CallRecorder.class);
        context.startService(serviceIntent);
    }

}
