package com.softuni.homework4;


public class Song {
    private String mTitle;
    private String mAuthor;
    private String mAlbum;
    private int resourceId;

    public Song(String mTitle, String mAuthor, String mAlbum, int resourceId) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAlbum = mAlbum;
        this.resourceId = resourceId;
    }

    public String getAlbum() {

        return mAlbum;
    }

    public String getAuthor() {
        return mAuthor;
    }


    public String getTitle() {
        return mTitle;
    }


    public int getResourceId() {
        return resourceId;
    }

}
