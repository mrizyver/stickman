package com.izyver.mr.stikman.game;

import com.izyver.mr.stikman.stick.core.InteractionStick;
import com.izyver.mr.stikman.stick.World;

import java.util.HashMap;
import java.util.Map;

public class GameWorld extends World {

    private final Map<String, InteractionStick> stickMap = new HashMap<>();

    @Override
    public void addStick(InteractionStick stick) {
        stickMap.put(stick.getTag(), stick);
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
