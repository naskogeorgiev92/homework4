package com.softuni.homework4.activities;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softuni.homework4.R;
import com.softuni.homework4.services.BatteryService;
import com.softuni.homework4.services.IServiceCommunication;

public class BatteryActivity extends AppCompatActivity implements IServiceCommunication {

    public final static String ACTION = "android.intent.action.BATTERY_CHANGED";
    TextView tvStatus;
    Context ctx = BatteryActivity.this;
    int batteryChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        tvStatus = (TextView) findViewById(R.id.tv_battery_status);

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (!BatteryService.class.getName().equals(service.service.getClassName()))
                setupService();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onPostResume();
        batteryChange = BatteryService.getLastChange();
    }

    void setupService() {
        Intent intent = new Intent(ctx, BatteryService.class);
        PendingIntent pendingIntent = PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 60 * 1000, 60 * 1000, pendingIntent);
        startService(intent);

        BatteryService.initializeCallback(BatteryActivity.this);
    }

    public void registerForBroadcast(View view) {
    }

    public void unregisterForBroadcast(View view) {
    }


    @Override
    public void onBatteryChangeRequested(int change) {
        batteryChange = change;
        tvStatus.setText(getString(R.string.battery_dropped_with) + batteryChange + "%");
    }

    @Override
    public void onServiceCustomInvocation() {

    }
}
