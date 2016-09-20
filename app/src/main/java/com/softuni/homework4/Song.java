package com.softuni.homework4;


public class Song {
    private String mTitle;
    private String mAuthor;
    private String mAlbum;

    public Song(String mTitle, String mAuthor, String mAlbum) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAlbum = mAlbum;
    }

    public String getAlbum() {

        return mAlbum;
    }

    public void setAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
