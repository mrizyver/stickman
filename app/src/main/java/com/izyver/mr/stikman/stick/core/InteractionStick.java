package com.izyver.mr.stikman.stick.core;

public interface InteractionStick extends StickObject {
    StickType getType();

    void onCollisionOccurred(InteractionStick collisionStick);
}
