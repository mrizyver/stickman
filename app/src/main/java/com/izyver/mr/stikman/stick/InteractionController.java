package com.izyver.mr.stikman.stick;

import com.izyver.mr.stikman.stick.core.InteractionStick;

public abstract class InteractionController<IS extends InteractionStick> implements Movable {

    protected IS stick;

    public InteractionController(IS interactionStick, World world) {
        this.stick  = interactionStick;
        world.addStick(interactionStick);
    }
}
