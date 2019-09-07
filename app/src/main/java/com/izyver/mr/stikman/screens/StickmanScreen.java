package com.izyver.mr.stikman.screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.izyver.mr.stikman.game.GameWorld;
import com.izyver.mr.stikman.game.stickman.Stickman;
import com.izyver.mr.stikman.stick.Movable;
import com.izyver.mr.stikman.stick.World;

public class StickmanScreen implements GameScreen {

    public static final String USER_STICK_NAME = "com.izyver.mr.stikman.game.stickman.Stickman.User";

    private Paint paint;

    private int width;
    private int heigh;

    private final World gameWorld;
    private Stickman stickman;

    public StickmanScreen() {
        this.gameWorld = new GameWorld();

        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(2);
    }

    @Override
    public void gameStarted() {
        this.stickman = new Stickman(USER_STICK_NAME, 150);
        this.stickman.setPosition( 0, heigh - 150);
        this.gameWorld.addStick(stickman);
    }

    @Override
    public void updateScreen(long deltaTime) {
        this.gameWorld.update(deltaTime);
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawLines(gameWorld.getPoints(), paint);
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.heigh = height;
        this.gameWorld.resize(width, height);
    }

    @Override
    public OnScreenInput getInputScreen() {
        return new OnInputListener();
    }

    class OnInputListener extends OnScreenInput{

        @Override
        public void onDown(int x, int y) {
            if (x < width / 2){
                movement().toLeft(true);
            }else {
                movement().toRight(true);
            }
        }

        @Override
        public void onUp(int x, int y) {
            movement().toLeft(false);
            movement().toRight(false);
        }

        @Override
        public void onMove(int x, int y) {

        }

        private Movable stickmanMovable;

        private Movable movement(){
            if (stickmanMovable == null) {
                stickmanMovable = stickman.getMovable();
            }
            return stickmanMovable;
        }
    }
}

