package com.softuni.homework4.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.softuni.homework4.activities.BatteryActivity;

import java.util.ArrayList;
import java.util.Date;

public class BatteryService extends Service {

    public static final String ACTION = "com.softuni.homework4.services.BatteryService";
    final String TAG = this.getClass().getSimpleName();
    private BatteryReceiver receiver;
    private static IServiceCommunication mListener;
    private ArrayList<Integer> levels;

    static int currentLevel, lastChange;

    @Override
    public void onCreate() {
        super.onCreate();
        levels = new ArrayList<>();
        registerBatteryReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBatteryReceiver();
        Log.d(TAG, "BatteryService called onDestroy.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Date date = new Date(System.currentTimeMillis());
        Log.d(TAG, "Current time is " + date.toString());
        getApplicationContext().sendBroadcast(new Intent(ACTION));
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void registerBatteryReceiver() {
        if (receiver == null)
            receiver = new BatteryReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    super.onReceive(context, intent);

                    if (intent.getAction().equals(BatteryService.ACTION)) {
                        // The intent comes from Battery Service
                        if (levels.isEmpty())
                            return; // There hasn't been any measurements yet

                        lastChange = levels.get(0) - levels.get(levels.size() - 1);
                        Log.d(TAG, "The change in battery is " + lastChange);
                        mListener.onBatteryChangeRequested(lastChange);
                        levels.clear();
                    } else {
                        // The intent comes from Android system
                        currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                        if(currentLevel!=0) levels.add(currentLevel);
                        Log.d(TAG, "Current battery percentage is " + levels.get(levels.size() - 1));
                    }
                }
            };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BatteryActivity.ACTION);
        filter.addAction(BatteryService.ACTION);
        filter.addAction(BatteryReceiver.ACTION);
        registerReceiver(receiver, filter);
    }

    void unregisterBatteryReceiver() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    public static int getLastChange() {
        return lastChange;
    }

    public static void initializeCallback(IServiceCommunication listener) {
        mListener = listener;
    }
}
