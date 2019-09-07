package com.izyver.mr.stikman.game.stickman;

import com.izyver.mr.stikman.stick.Movable;

import static com.izyver.mr.stikman.game.stickman.Stickman.X;
import static com.izyver.mr.stikman.game.stickman.Stickman.Y;
import static com.izyver.mr.stikman.game.stickman.StickmanEngine.LeadingLed.LEFT;
import static com.izyver.mr.stikman.game.stickman.StickmanEngine.LeadingLed.RIGHT;

public class StickmanEngine {

    /**
     * The ratio of different part of the body to stick growth
     */
    private static final float
            RATIO_FOOTS = 3,
            RATIO_BODY = 3,
            RATIO_HEAD = 2,
            RATIO_ANKLE_DISTANCE = 2,
            RATIO_WRIST_DISTANCE = 2;


    private final static int MAX_FRAME_TIME = 40;
    private final Stickman stick;
    /**
     * The width of the field where stick can move
     * The height of the field where stick can move
     */
    private float width, height, stickmanHeight;
    /**
     * The stickmanHeightPerSecond is the time that a stick spends for stickmanHeight
     */
    private float stickmanHeightPerSecond = 1.2f;
    private float speed;
    private float stepWidth;
    /**
     * The lastAppeal - is the time when the last frame is received
     */
    private LeadingLed leadingLed = LEFT;

    private StickmanMovement movement;

    StickmanEngine(Stickman stickman, int stickmanHeight) {
        createStickman(stickman, stickmanHeight);

        this.stick = stickman;
        this.stickmanHeight = stickmanHeight;
        this.speed = stickmanHeight / stickmanHeightPerSecond;
        this.stepWidth = stick.ankleRight[X] - stick.ankleLeft[X];
        this.movement = new StickmanMovement();
    }



    public Stickman goToLeft(long deltaTime) {
        double distance = calculateMoveDistance(deltaTime);
        if (distance == 0) return stick;
        stick.pelvis[X] -= distance;
        stick.chest[X] -= distance;
        stick.wristLeft[X] -= distance;
        stick.wristRight[X] -= distance;
        straightenArms(stick);

        switch (leadingLed) {
            case RIGHT:
                stick.ankleLeft[X] -= distance * 2;
                if ((stick.ankleRight[X] - stick.pelvis[X]) >= (stepWidth / 2)) {
                    leadingLed = LEFT;
                }
                break;
            case LEFT:
                stick.ankleRight[X] -= distance * 2;
                if ((stick.ankleLeft[X] - stick.pelvis[X]) >= (stepWidth / 2)) {
                    leadingLed = RIGHT;
                }
                break;
        }
        straightenFoots(stick);
        return stick;
    }

    public Stickman goToRight(long deltaTime) {
        double distance = calculateMoveDistance(deltaTime);
        if (distance == 0) return stick;
        stick.pelvis[X] += distance;
        stick.chest[X] += distance;
        stick.wristLeft[X] += distance;
        stick.wristRight[X] += distance;
        straightenArms(stick);

        switch (leadingLed) {
            case RIGHT:
                stick.ankleLeft[X] += distance * 2;
                straightenRightFoot(stick);
                if (stick.kneeLeft[X] < stick.pelvis[X]) {
                    stick.kneeLeft[X] += distance * 1.9;
                } else {
                    stick.kneeLeft[X] += distance;
                    float[] knee = getAveragePointInLine(stick.ankleLeft, stick.pelvis);
                    if (knee[X] > stick.kneeLeft[X]) {
                        stick.kneeLeft = knee;
                    }
                }
                if ((stick.ankleLeft[X] - stick.pelvis[X]) >= (stepWidth / 2)) {
                    leadingLed = LEFT;
                }
                break;
            case LEFT:
                stick.ankleRight[X] += distance * 2;
                straightenLeftFoot(stick);
                if (stick.kneeRight[X] < stick.pelvis[X]) {
                    stick.kneeRight[X] += distance * 1.9;
                } else {
                    stick.kneeRight[X] += distance;
                    float[] knee = getAveragePointInLine(stick.ankleRight, stick.pelvis);
                    if (knee[X] > stick.kneeRight[X]) {
                        stick.kneeRight = knee;
                    }
                }
                if ((stick.ankleRight[X] - stick.pelvis[X]) >= (stepWidth / 2)) {
                    leadingLed = RIGHT;
                }
                break;
        }
        return stick;
    }

    public Movable getMovement() {
        return movement;
    }


    void update(long deltaTime) {
        if (movement.isMoveToLeft){
            goToLeft(deltaTime);
        }else if (movement.isMoveToRight){
            goToRight(deltaTime);
        }
    }

    void resize(int width, int height){
        this.width = width;
        this.height = height;
        createStickman(stick, stickmanHeight);
    }

    private void createStickman(Stickman stickman, float stickmanHeight) {
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

        float startPosX = stickman.position[X];
        float startPosY = stickman.position[Y];

        ankleLeft[X] = startPosX;
        ankleLeft[Y] = startPosY + stickmanHeight;

        ankleRight[X] = startPosX + stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue);
        ankleRight[Y] = startPosY + stickmanHeight;

        wristLeft[X] = startPosX;
        wristLeft[Y] = startPosY + (stickmanHeight / 3);

        wristRight[X] = startPosX + stickmanHeight * (RATIO_WRIST_DISTANCE / fullRatioValue);
        wristRight[Y] = wristLeft[Y];

        pelvis[X] = startPosX + stickmanHeight * (RATIO_ANKLE_DISTANCE / fullRatioValue) / 2;
        pelvis[Y] = startPosY + (stickmanHeight * (RATIO_FOOTS / fullRatioValue));

        chest[X] = pelvis[X];
        chest[Y] = pelvis[Y] - (stickmanHeight * (RATIO_BODY / fullRatioValue));

        kneeLeft[X] = (pelvis[X] + ankleLeft[X]) / 2;
        kneeLeft[Y] = (pelvis[Y] + ankleLeft[Y]) / 2;

        kneeRight[X] = (pelvis[X] + ankleRight[X]) / 2;
        kneeRight[Y] = (pelvis[Y] + ankleRight[Y]) / 2;

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
        stickman.kneeRight[Y] = (stickman.pelvis[Y] + stickman.ankleRight[Y]) / 2;
    }

    private void straightenLeftFoot(Stickman stickman) {
        stickman.kneeLeft[Y] = (stickman.pelvis[Y] + stickman.ankleLeft[Y]) / 2;
        stickman.kneeLeft[X] = (stickman.pelvis[X] + stickman.ankleLeft[X]) / 2;
    }

    private void straightenRightFoot(Stickman stickman) {
        stickman.kneeRight[X] = (stickman.pelvis[X] + stickman.ankleRight[X]) / 2;
        stickman.kneeRight[Y] = (stickman.pelvis[Y] + stickman.ankleRight[Y]) / 2;
    }

    private float[] getAveragePointInLine(float[] point1, float[] point2) {
        float[] averagePoint = new float[2];
        averagePoint[X] = (point1[X] + point2[X]) / 2;
        averagePoint[Y] = (point1[Y] + point2[Y]) / 2;
        return averagePoint;
    }

    private double calculateMoveDistance(long deltaTime) {
        return speed * (deltaTime / 1000.0);
    }


    enum LeadingLed {
        RIGHT,
        LEFT
    }

    class StickmanMovement implements Movable{

        boolean isMoveToRight = false;
        boolean isMoveToLeft = false;

        @Override
        public void toRight(boolean isMove) {
            isMoveToLeft = !isMove && isMoveToLeft;
            isMoveToRight = isMove;
        }

        @Override
        public void toLeft(boolean isMove) {
            isMoveToRight = !isMove && isMoveToRight;
            isMoveToLeft = isMove;
        }

        @Override
        public void ToUp(boolean isMove) {

        }

        @Override
        public void ToDown(boolean isMove) {

        }
    }
}
