package com.softuni.homework4.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.softuni.homework4.R;
import com.softuni.homework4.Song;
import com.softuni.homework4.SpotifyAdapter;
import com.softuni.homework4.interfaces.IRecyclerViewSelected;
import com.softuni.homework4.services.MusicService;

import java.util.ArrayList;

public class SpotifyActivity extends Activity implements View.OnClickListener, IRecyclerViewSelected {

    RecyclerView mRecyclerView;
    ImageView ivMusicPlay, ivMusicPause, ivMusicForward, ivMusicRewind;
    MusicService mService;
    boolean mBound = false;
    Song currentSong;
    private ArrayList<Song> data;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);

        initializeData();
        initializeViews();

    }

    private void initializeViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.spotify_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SpotifyActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SpotifyAdapter mAdapter = new SpotifyAdapter(data, SpotifyActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        ivMusicPlay = (ImageView) findViewById(R.id.music_play);
        ivMusicPause = (ImageView) findViewById(R.id.music_pause);
        ivMusicForward = (ImageView) findViewById(R.id.music_forward);
        ivMusicRewind = (ImageView) findViewById(R.id.music_rewind);

        ivMusicPlay.setOnClickListener(this);
        ivMusicPause.setOnClickListener(this);
        ivMusicForward.setOnClickListener(this);
        ivMusicRewind.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent musicServiceIntent = new Intent(this, MusicService.class);
        bindService(musicServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
        //startService(musicServiceIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private void initializeData() {
        data = new ArrayList<>();
        data.add(0, new Song("Around the world (La la la la la)", "ATC", "All for one", R.raw.all_around_the_workd));
        data.add(1, new Song("Beautiful Life", "Ace of Base", "The Bridge", R.raw.beautiful_life));
        data.add(2, new Song("Captain Jack", "Captain Jack", "Party Warriors", R.raw.captain_jack));
        data.add(3, new Song("2 Brothers on the 4th floor ", "Dreams (Will come alive) ", "Single", R.raw.dreams));
        data.add(4, new Song("Ace of Base", "All That She Wants", "All That She Wants", R.raw.she_wants));
        data.add(5, new Song("Amaranthe", "The Nexus", "The Nexus", R.raw.the_nexus));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.music_play)
            mService.startPlaying(currentSong.getResourceId());
        if (v.getId() == R.id.music_pause)
            mService.pause();
        if (v.getId() == R.id.music_forward)
            mService.fastForward();
        if (v.getId() == R.id.music_rewind)
            mService.rewind();
    }

    @Override
    public void getCurrentSelection(View v, int position) {
        currentSong = data.get(position);
        mService.startPlaying(currentSong.getResourceId());
    }
}