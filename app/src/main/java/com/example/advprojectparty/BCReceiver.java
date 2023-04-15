package com.example.advprojectparty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BCReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("BCReceiver","Broadcast received");
        Toast t = Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT);
        t.show();
    }
}
