package com.izyver.mr.stikman.stickman;

import static com.izyver.mr.stikman.stickman.LeadingLed.LEFT;
import static com.izyver.mr.stikman.stickman.LeadingLed.RIGHT;

public class StickmanEngine {

    /** The ratio of different part of the body to stickman growth */
    private static final float
            RATIO_FOOTS = 3,
            RATIO_BODY = 3,
            RATIO_HEAD = 2,
            RATIO_ANKLE_DISTANCE = 2,
            RATIO_WRIST_DISTANCE = 2;

    private final static int FRAME_TIME = 17;
    private final static int MAX_FRAME_TIME = 40;
    private final static int X = 0, Y = 1;
    /**
     * The width of the field where stickman can move
     * The height of the field where stickman can move
     */
    private float width, height, stickmanHeight;

    /** The stickmanHeightPerSecond is the time that a stickman spends for stickmanHeight */
    private float stickmanHeightPerSecond = 1.2f;
    private float speed;
    private float stepWidth;

    /** The lastAppeal - is the time when the last frame is received */
    private long lastAppeal;

    private LeadingLed leadingLed = RIGHT;



    private Stickman stickman;


    public StickmanEngine(int width, int height, int stickmanHeight) {
        this.width = width;
        this.height = height;
        this.stickmanHeight = stickmanHeight;
        this.speed = stickmanHeight / stickmanHeightPerSecond;
        this.stickman = new Stickman();
        createStickman(stickman, stickmanHeight);
        this.stepWidth = stickman.ankleRight[X] - stickman.ankleLeft[X];
    }

    public Stickman moveToLeft() {
        long nowTime = System.currentTimeMillis();
        double currentTimeFrame = nowTime - lastAppeal;
        if (currentTimeFrame > MAX_FRAME_TIME){
            lastAppeal = nowTime;
            return stickman;
        }
        double distance = speed * (currentTimeFrame / 1000.0);
        stickman.pelvis[X] -= distance;
        stickman.chest[X] -= distance;
        stickman.wristLeft[X] -= distance;
        stickman.wristRight[X] -= distance;
        straightenArms(stickman);

        switch (leadingLed){
            case RIGHT:
                stickman.ankleLeft[X] += distance + (distance * 0.25);
                if ((stickman.ankleLeft[X] - stickman.pelvis[X]) >= (stepWidth / 2)){
                    leadingLed = LEFT;
                }
                break;
            case LEFT:
                stickman.ankleRight[X] += distance + (distance * 0.25);
                if ((stickman.ankleRight[X] - stickman.pelvis[X]) >= (stepWidth / 2)){
                    leadingLed = RIGHT;
                }
                break;
        }
        straightenFoots(stickman);
        return stickman;
    }

    public Stickman goToRight() {
        double distance = calculateMoveDistance();
        if (distance == 0) return stickman;
        stickman.pelvis[X] += distance;
        stickman.chest[X] += distance;
        stickman.wristLeft[X] += distance;
        stickman.wristRight[X] += distance;
        straightenArms(stickman);

        switch (leadingLed){
            case RIGHT:
                stickman.ankleLeft[X] += distance * 2;
                if ((stickman.ankleLeft[X] - stickman.pelvis[X]) >= (stepWidth / 2)){
                    leadingLed = LEFT;
                }
                break;
            case LEFT:
                stickman.ankleRight[X] += distance * 2;
                if ((stickman.ankleRight[X] - stickman.pelvis[X]) >= (stepWidth / 2)){
                    leadingLed = RIGHT;
                }
                break;
        }
        straightenFoots(stickman);
        return stickman;
    }

    private double calculateMoveDistance(){
        long nowTime = System.currentTimeMillis();
        double currentTimeFrame = nowTime - lastAppeal;
        if (currentTimeFrame > MAX_FRAME_TIME){
            lastAppeal = nowTime;
            return 0;
        }
        return speed * (currentTimeFrame / 1000.0);
    }

    private void straightenArms(Stickman stickman) {
        stickman.elbowLeft[X] = (stickman.chest[X] + stickman.wristLeft[X]) / 2;
        stickman.elbowLeft[Y] = (stickman.chest[Y] + stickman.wristLeft[Y]) / 2;

        stickman.elbowRight[X] = (stickman.chest[X] + stickman.wristRight[X]) / 2;
        stickman.elbowRight[Y] = (stickman.chest[Y] + stickman.wristRight[Y]) / 2;
    }

    private void straightenFoots(Stickman stickman) {
        stickman.kneeLeft[Y] = (stickman.pelvis[Y] + stickman.ankleLeft[Y]) / 2;
        stickman.kneeLeft[X] = (stickman.pelvis[X] + stickman.ankleLeft[X]) / 2;

        stickman.kneeRight[X] = (stickman.pelvis[X] + stickman.ankleRight[X]) / 2;
        stickman.kneeRight[Y] = (stickman.pelvis[Y] + stickman.ankleRight[Y]) /2 ;
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
        ankleLeft[Y] = height;

        ankleRight[X] = stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue);
        ankleRight[Y] = height;

        wristLeft[X] = 0;
        wristLeft[Y] = height - (stickmanHeight / 3);

        wristRight[X] = stickmanHeight * (RATIO_WRIST_DISTANCE / fullRatioValue);
        wristRight[Y] = wristLeft[Y];

        pelvis[X] = stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue) / 2;
        pelvis[Y] = height - (stickmanHeight * (RATIO_FOOTS / fullRatioValue));

        chest[X] = pelvis[X];
        chest[Y] =  pelvis[Y] - (stickmanHeight * (RATIO_BODY / fullRatioValue));

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
