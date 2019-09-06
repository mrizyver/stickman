package com.izyver.mr.stikman.stick;

import com.izyver.mr.stikman.stick.core.InteractionStick;
import com.izyver.mr.stikman.stick.core.StickObject;

public abstract class World implements StickObject {
    public abstract void addStick(InteractionStick stick);

    public abstract InteractionStick getStickByName(String tag);

    public abstract void resize(int wight, int height);
}
