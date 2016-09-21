package com.softuni.homework4.services;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer player;
    IBinder binder = new MusicServiceBinder();
    IServiceCommunication callback;

    @Override
    public void onCreate() {
        Log.d("Music Service", "Music Service onCreate called.");
        player = new MediaPlayer();
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void startPlaying(String dataSource) {
        try {
            player.setDataSource(dataSource);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPlaying() {
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Music Service", "Music Service onBind called.");
        return binder;
    }

    public void setServiceCallback(IServiceCommunication callback) {
        this.callback = callback;
        if(callback != null) {
            callback.onServiceCustomInvocation();
        }
    }

    public class MusicServiceBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
