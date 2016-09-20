package com.softuni.homework4.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.softuni.homework4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchTaskTwoActivity(View view) {
        startActivity(new Intent(this, BatteryActivity.class));
    }

    public void launchTaskOneActivity(View view) {
        startActivity(new Intent(this, SpotifyActivity.class));
    }
}
