package com.softuni.homework4.interfaces;

/**
 * Created by nasko on 25.09.16.
 */

public interface IMusicController {
    void startPlaying(String songName);
    void stopPlaying();
    void fastForward();
    void reverse();
}
