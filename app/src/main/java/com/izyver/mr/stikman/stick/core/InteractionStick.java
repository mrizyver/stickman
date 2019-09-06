package com.izyver.mr.stikman.stick.core;

public interface InteractionStick extends StickObject {
    StickType getType();

    void onCollisionOccurred(InteractionStick collisionStick);

    String getTag();

    void environmentResized(int width, int height);
}
