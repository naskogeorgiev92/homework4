package com.softuni.homework4.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.softuni.homework4.R;
import com.softuni.homework4.Song;
import com.softuni.homework4.SpotifyAdapter;

import java.util.ArrayList;

public class SpotifyActivity extends Activity {

    private RecyclerView mRecyclerView;
    private SpotifyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Song> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);

        initializeData();

        mRecyclerView = (RecyclerView) findViewById(R.id.spotify_recycler_view);
        mLayoutManager = new LinearLayoutManager(SpotifyActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SpotifyAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initializeData() {
        data = new ArrayList<>();
        data.add(0, new Song("Blow Your Mind (Mwah)", "Dua Lipa", "Blow Your Mind (Mwah)"));
        data.add(1, new Song("Carry Me", "Kygo, Julia Michaels", "Cloud Nine"));
        data.add(2, new Song("Suburbia", "Kilian & Jo, Erik Rapp", "Suburbia"));
        data.add(3, new Song("Closer", "The Chainsmokers Halsey", "Closer"));
        data.add(4, new Song("Fly Or Die", "N.E.R.D", "The Neptunes"));
        data.add(5, new Song("Let Me Love You", "DJ Snake, Justin Bieber", "Encore"));
        data.add(6, new Song("Heathens", "Twenty One Pilots", "Heathens"));
        data.add(7, new Song("One Dance", "Drake Wizkid Kyla", "Views"));
        data.add(8, new Song("In the Name of Love", "Marting Garrix, Bebe Rexa", "In the Name of Love"));
        data.add(9, new Song("Treat You Better", "Shawn Mendes", "Treat You Better"));
        data.add(10, new Song("Don't Let Me Down", "The Chainsmokers Halsey", "Don't Let Me Down"));
        data.add(11, new Song("Carry Me", "Kygo, Julia Michaels", "Cloud Nine"));
        data.add(12, new Song("Suburbia", "Kilian & Jo, Erik Rapp", "Suburbia"));
        data.add(13, new Song("Closer", "The Chainsmokers Halsey", "Closer"));
        data.add(14, new Song("Fly Or Die", "N.E.R.D", "The Neptunes"));
        data.add(15, new Song("Let Me Love You", "DJ Snake, Justin Bieber", "Encore"));
        data.add(16, new Song("Heathens", "Twenty One Pilots", "Heathens"));
        data.add(17, new Song("One Dance", "Drake Wizkid Kyla", "Views"));
        data.add(18, new Song("In the Name of Love", "Marting Garrix, Bebe Rexa", "In the Name of Love"));
        data.add(19, new Song("Treat You Better", "Shawn Mendes", "Treat You Better"));
        data.add(20, new Song("Don't Let Me Down", "The Chainsmokers Halsey", "Don't Let Me Down"));
    }
}
