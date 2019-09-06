package com.izyver.mr.stikman.stick.core;

public abstract class StatickStick implements InteractionStick {
    @Override
    public final StickType getType() {
        return StickType.STATIC;
    }

    @Override
    public void update(long deltaTime) {
        //it is static object
    }
}
