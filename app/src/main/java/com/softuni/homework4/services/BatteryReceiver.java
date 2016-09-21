package com.softuni.homework4.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

import com.softuni.homework4.activities.BatteryActivity;

import java.util.ArrayList;

public class BatteryReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.softuni.homework4.services.BatteryReceiver";
    final String TAG = "BatteryReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {

    }


    // Works except when you minimize
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equalsIgnoreCase(BatteryReceiver.ACTION))
//            return; // The intent comes from ourselves;
//
//        if (intent.getAction().equals(BatteryService.ACTION)) {
//            // The intent comes from Battery Service
//            if (levels.isEmpty())
//                return; // There hasn't been any measurements yet
//
//            int changeInLevel = levels.get(0) - levels.get(levels.size() - 1);
//            Intent i = new Intent(ACTION);
//            if(changeInLevel > 0)
//                i.putExtra("BatteryChange", changeInLevel);
//            context.sendBroadcast(i);
//            Log.d(TAG, "The change in battery is " + changeInLevel);
//            levels.clear();
//        } else {
//            // The intent comes from Android system
//            levels.add(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1));
//            Log.d(TAG, "Current battery percentage is " + levels.get(levels.size() - 1));
//        }
//    }
}
