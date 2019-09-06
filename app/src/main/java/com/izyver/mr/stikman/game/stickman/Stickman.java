package com.izyver.mr.stikman.game.stickman;

import com.izyver.mr.stikman.stick.core.DynamicStick;
import com.izyver.mr.stikman.stick.core.InteractionStick;

public class Stickman extends DynamicStick {

    private final StickmanWrapper wrapper;

    public Stickman() {
        wrapper = new StickmanWrapper(this);
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public float[] getPoints() {
        return new float[0];
    }

    @Override
    public void onCollisionOccurred(InteractionStick collisionStick) {

    }
}
