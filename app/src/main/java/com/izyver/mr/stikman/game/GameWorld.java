package com.izyver.mr.stikman.game;

import com.izyver.mr.stikman.stick.core.InteractionStick;
import com.izyver.mr.stikman.stick.World;

public class GameWorld extends World {

    @Override
    public void addStick(InteractionStick stick) {

    }

    @Override
    public InteractionStick getStickByTag(String tag) {
        return null;
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public float[] getPoints() {
        return new float[0];
    }
}
