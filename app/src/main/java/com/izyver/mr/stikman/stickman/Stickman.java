package com.izyver.mr.stikman.stickman;

public class Stickman {
    private final static int X = 0, Y = 1;

    public float[]
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

    public Stickman setChest(float[] chest) {
        this.chest = chest;
        return this;
    }

    public Stickman setElbowLeft(float[] elbowLeft) {
        this.elbowLeft = elbowLeft;
        return this;
    }

    public Stickman setElbowRight(float[] elbowRight) {
        this.elbowRight = elbowRight;
        return this;
    }

    public Stickman setWristLeft(float[] wristLeft) {
        this.wristLeft = wristLeft;
        return this;
    }

    public Stickman setWristRight(float[] wristRight) {
        this.wristRight = wristRight;
        return this;
    }

    public Stickman setPelvis(float[] pelvis) {
        this.pelvis = pelvis;
        return this;
    }

    public Stickman setKneeLeft(float[] kneeLeft) {
        this.kneeLeft = kneeLeft;
        return this;
    }

    public Stickman setKneeRight(float[] kneeRight) {
        this.kneeRight = kneeRight;
        return this;
    }

    public Stickman setAnkleLeft(float[] ankleLeft) {
        this.ankleLeft = ankleLeft;
        return this;
    }

    public Stickman setAnkleRight(float[] ankleRight) {
        this.ankleRight = ankleRight;
        return this;
    }

    /**
     * Adding all points to a single array, for drawing lines in Canvas.drawLines(...)
     * @return
     */
    public float[] getAllLines() {
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
}
