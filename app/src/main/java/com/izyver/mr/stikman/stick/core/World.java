package com.izyver.mr.stikman.stick.core;

public abstract class World implements StickObject {
    public abstract void addStick(InteractionStick stick);

    public abstract InteractionStick getStickByTag(String tag);
}
