package com.softuni.homework4.services;

/**
 * Created by nasko.georgiev on 21.9.2016 г..
 */
public interface IServiceCommunication {
    void onBatteryChangeRequested(int change);
    void onServiceCustomInvocation();
}
