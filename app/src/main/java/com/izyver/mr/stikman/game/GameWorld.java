package com.izyver.mr.stikman.game;

import com.izyver.mr.stikman.stick.core.InteractionStick;
import com.izyver.mr.stikman.stick.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameWorld extends World {

    private final Map<String, InteractionStick> stickMap;
    private final Collection<InteractionStick> sticks;

    public GameWorld() {
        this.stickMap = new HashMap<>();
        this.sticks = stickMap.values();
    }

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
        for (InteractionStick stick : sticks) {
            stick.environmentResized(wight, height);
        }
    }

    @Override
    public void update(long deltaTime) {
        for (InteractionStick stick : sticks) {
            stick.update(deltaTime);
        }
    }

    @Override
    public float[] getPoints() {
        int size = 0, position = 0;
        Stack<float[]> stack = new Stack<>();
        for (InteractionStick stick : sticks) {
            float[] points = stick.getPoints();
            size += points.length;
            stack.push(points);
        }

        float[] allPoints = new float[size];

        while (!stack.isEmpty()){
            float[] points = stack.pop();
            System.arraycopy(points, 0, allPoints, position, points.length);
            position += points.length;
        }

        return allPoints;
    }
}
