package com.izyver.mr.stikman.stick.core;

public abstract class DynamicStick implements InteractionStick {
    @Override
    public final StickType getType() {
        return StickType.DYNAMIC;
    }
}
