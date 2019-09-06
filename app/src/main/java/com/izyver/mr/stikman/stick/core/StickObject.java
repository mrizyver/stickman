package com.izyver.mr.stikman.stick.core;

public interface StickObject {
    void update(long deltaTime);

    @StickLines
    float[] getPoints();
}
