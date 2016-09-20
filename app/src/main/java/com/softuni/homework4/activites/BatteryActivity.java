package com.softuni.homework4.activites;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softuni.homework4.BatteryReceiver;
import com.softuni.homework4.R;

import org.w3c.dom.Text;

public class BatteryActivity extends AppCompatActivity {

    private BatteryReceiver receiver;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        tvStatus = (TextView) findViewById(R.id.tv_battery_status);
    }

    public void registerForBroadcast(View view) {
        if (receiver == null)
            receiver = new BatteryReceiver();

        IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        Intent intent = registerReceiver(receiver, filter);
        String message = getString(R.string.current_battery_percentage) + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) + "%";
        tvStatus.setText(message);
    }

    public void unregisterForBroadcast(View view) {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
