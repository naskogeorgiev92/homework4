package com.softuni.homework4.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    private final String TAG = getClass().getSimpleName();
    private final IBinder binder = new MusicBinder();
    int length;
    private MediaPlayer player;
    private boolean isPaused = false;

    @Override
    public void onCreate() {
        Log.d(TAG, "Music Service onCreate called.");

    }

    public void startPlaying(int resourceId) {
        AssetFileDescriptor afd = getApplicationContext().getResources().openRawResourceFd(resourceId);

        if (player != null && player.isPlaying() && !isPaused) {
            player.stop();
            player.reset();
            player.release();
        }

        if (player != null && isPaused) {
            player.seekTo(length);
            player.start();
            isPaused = false;
            return;
        }

        try {
            player = new MediaPlayer();
            player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    public void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
            isPaused = true;
            length = player.getCurrentPosition();
        }
    }

    public void fastForward() {
        if (player != null) {
            player.seekTo(player.getCurrentPosition() + 5000);
        }
    }

    public void rewind() {
        if (player != null) {
            player.seekTo(player.getCurrentPosition() - 5000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
