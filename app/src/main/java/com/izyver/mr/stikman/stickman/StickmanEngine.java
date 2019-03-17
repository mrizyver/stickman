package com.izyver.mr.stikman.stickman;

public class StickmanEngine {

    /**
     * @param width - the width of the field where stickman can move
     * @param height - the height of the field where stickman can move
     */
    private float width, height, stickmanHeight;
    private static final float
            RATIO_FOOTS = 3,
            RATIO_BODY = 3,
            RATIO_HEAD = 2,
            RATIO_ANKLE_DISTANCE = 2,
            RATIO_WRIST_DISTANCE = 2;
    private Stickman stickman;
    private final static int X = 0, Y = 1;
    public StickmanEngine(int width, int height, int stickmanHeight) {
        this.width = width;
        this.height = height;
        this.stickmanHeight = stickmanHeight;
        stickman = new Stickman();
        createStickman(stickman, stickmanHeight);
    }

    public Stickman moveToLeft() {
        return stickman;
    }

    private void createStickman(Stickman stickman, int stickmanHeight) {
        float fullRatioValue = RATIO_BODY + RATIO_FOOTS + RATIO_HEAD;
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
        ankleLeft[X] = 0;
        ankleLeft[Y] = 0;

        ankleRight[X] = stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue);
        ankleRight[Y] = 0;

        wristLeft[X] = 0;
        wristLeft[Y] = stickmanHeight / 3;

        wristRight[X] = stickmanHeight * (RATIO_WRIST_DISTANCE / fullRatioValue);
        wristRight[Y] = wristLeft[Y];

        pelvis[X] = stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue) / 2;
        pelvis[Y] = stickmanHeight * (RATIO_FOOTS / fullRatioValue);

        chest[X] = pelvis[X];
        chest[Y] = stickmanHeight * (RATIO_BODY / fullRatioValue) + pelvis[Y];

        kneeLeft[X] = (pelvis[X] + ankleLeft[X]) / 2;
        kneeLeft[Y] = (pelvis[Y] + ankleLeft[Y]) / 2;

        kneeRight[X] = (pelvis[X] + ankleRight[X]) / 2;
        kneeRight[Y] = (pelvis[Y] + ankleRight[Y]) /2 ;

        elbowLeft[X] = (chest[X] + wristLeft[X]) / 2;
        elbowLeft[Y] = (chest[Y] + wristLeft[Y]) / 2;

        elbowRight[X] = (chest[X] + wristRight[X]) / 2;
        elbowRight[Y] = (chest[Y] + wristRight[Y]) / 2;

        stickman.setAnkleLeft(ankleLeft)
                .setAnkleRight(ankleRight)
                .setKneeLeft(kneeLeft)
                .setKneeRight(kneeRight)
                .setPelvis(pelvis)
                .setWristLeft(wristLeft)
                .setWristRight(wristRight)
                .setElbowLeft(elbowLeft)
                .setElbowRight(elbowRight)
                .setChest(chest);
    }
}
