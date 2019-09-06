package com.izyver.mr.stikman.game.stickman;

import com.izyver.mr.stikman.stick.core.DynamicStick;
import com.izyver.mr.stikman.stick.core.InteractionStick;

public class Stickman extends DynamicStick {

    public final String name;
    private final StickmanWrapper wrapper;

    float[]
            chest = new float[2],
            elbowLeft = new float[2],
            elbowRight = new float[2],
            wristLeft = new float[2],
            wristRight = new float[2],
            pelvis = new float[2],
            kneeLeft = new float[2],
            kneeRight = new float[2],
            ankleLeft = new float[2],
            ankleRight = new float[2];

    public Stickman(String name) {
        this.wrapper = new StickmanWrapper(this);
        this.name = name;
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public float[] getPoints() {
        float[] allLines = new float[36];
        //adding left hand to array
        System.arraycopy(chest, 0, allLines, 0, 2);
        System.arraycopy(elbowLeft, 0, allLines, 2, 2);
        System.arraycopy(elbowLeft, 0, allLines, 4, 2);
        System.arraycopy(wristLeft, 0, allLines, 6, 2);

        //adding right hand to array
        System.arraycopy(chest, 0, allLines, 8, 2);
        System.arraycopy(elbowRight, 0, allLines, 10, 2);
        System.arraycopy(elbowRight, 0, allLines, 12, 2);
        System.arraycopy(wristRight, 0, allLines, 14, 2);

        //adding body to array
        System.arraycopy(chest, 0, allLines, 16, 2);
        System.arraycopy(pelvis, 0, allLines, 18, 2);

        //adding left foot to array
        System.arraycopy(pelvis, 0, allLines, 20, 2);
        System.arraycopy(kneeLeft, 0, allLines, 22, 2);
        System.arraycopy(kneeLeft, 0, allLines, 24, 2);
        System.arraycopy(ankleLeft, 0, allLines, 26, 2);

        //adding right foot to array
        System.arraycopy(pelvis, 0, allLines, 28, 2);
        System.arraycopy(kneeRight, 0, allLines, 30, 2);
        System.arraycopy(kneeRight, 0, allLines, 32, 2);
        System.arraycopy(ankleRight, 0, allLines, 34, 2);

        return allLines;
    }

    @Override
    public void onCollisionOccurred(InteractionStick collisionStick) {

    }

    @Override
    public String getTag() {
        return name;
    }

    Stickman setChest(float[] chest) {
        this.chest = chest;
        return this;
    }

    Stickman setElbowLeft(float[] elbowLeft) {
        this.elbowLeft = elbowLeft;
        return this;
    }

    Stickman setElbowRight(float[] elbowRight) {
        this.elbowRight = elbowRight;
        return this;
    }

    Stickman setWristLeft(float[] wristLeft) {
        this.wristLeft = wristLeft;
        return this;
    }

    Stickman setWristRight(float[] wristRight) {
        this.wristRight = wristRight;
        return this;
    }

    Stickman setPelvis(float[] pelvis) {
        this.pelvis = pelvis;
        return this;
    }

    Stickman setKneeLeft(float[] kneeLeft) {
        this.kneeLeft = kneeLeft;
        return this;
    }

    Stickman setKneeRight(float[] kneeRight) {
        this.kneeRight = kneeRight;
        return this;
    }

    Stickman setAnkleLeft(float[] ankleLeft) {
        this.ankleLeft = ankleLeft;
        return this;
    }

    Stickman setAnkleRight(float[] ankleRight) {
        this.ankleRight = ankleRight;
        return this;
    }
}
