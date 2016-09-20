package com.softuni.homework4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nasko.georgiev on 8.9.2016 г..
 */

public class SpotifyAdapter extends RecyclerView.Adapter<SpotifyAdapter.SpotifyViewHolder> {

    private ArrayList<Song> mData;

    public SpotifyAdapter(ArrayList<Song> data) {
        this.mData = data;
    }

    @Override
    public SpotifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spotify_recycler, parent, false);
        SpotifyViewHolder vh = new SpotifyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(SpotifyViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvAuthor.setText(mData.get(position).getAuthor());
        holder.tvAlbum.setText(mData.get(position).getAlbum());
        if(position % 2 == 0 ) {
            holder.tvExplicit.setVisibility(View.GONE);
            holder.tvDownload.setVisibility(View.VISIBLE);
        } else {
            holder.tvExplicit.setVisibility(View.VISIBLE);
            holder.tvDownload.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class SpotifyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvAuthor, tvAlbum, tvDownload, tvExplicit;

        public SpotifyViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_song_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvAlbum = (TextView) itemView.findViewById(R.id.tv_album);
            tvDownload = (TextView) itemView.findViewById(R.id.tv_download);
            tvExplicit = (TextView) itemView.findViewById(R.id.tv_explicit);
        }
    }
}
