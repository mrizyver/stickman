package com.izyver.mr.stikman.game;

import com.izyver.mr.stikman.stick.core.InteractionStick;
import com.izyver.mr.stikman.stick.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameWorld extends World {

    private final Map<String, InteractionStick> stickMap = new HashMap<>();

    @Override
    public void addStick(InteractionStick stick) {
        stickMap.put(stick.getTag(), stick);
    }

    @Override
    public InteractionStick getStickByName(String name) {
        return stickMap.get(name);
    }

    @Override
    public void resize(int wight, int height) {
        Collection<InteractionStick> values = stickMap.values();
        for (InteractionStick stick : values) {
            stick.environmentResized(wight, height);
        }
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public float[] getPoints() {
        return new float[0];
    }
}
