package com.izyver.mr.stikman.game.screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.izyver.mr.stikman.game.GameWorld;
import com.izyver.mr.stikman.game.OnScreenInput;
import com.izyver.mr.stikman.game.stickman.Stickman;
import com.izyver.mr.stikman.game.stickman.StickmanController;
import com.izyver.mr.stikman.stick.World;

public class StickmanScreen implements GameScreen {

    private Paint paint;

    private int width;
    private int heigh;

    private final World gameWorld;
    private final StickmanController stickController;

    public StickmanScreen() {
        Stickman stickman = new Stickman();
        this.gameWorld = new GameWorld();
        this.stickController = new StickmanController(stickman, this.gameWorld);

        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(2);
    }

    @Override
    public void updateScreen(long deltaTime) {

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
    }

    @Override
    public OnScreenInput getInputScreen() {
        return new OnInputListener();
    }

    class OnInputListener extends OnScreenInput{

        @Override
        public void onDown(int x, int y) {
            if (x < width / 2){
                stickController.toLeft(true);

            }else {
                stickController.toRight(true);
            }
        }

        @Override
        public void onUp(int x, int y) {
            stickController.toLeft(false);
            stickController.toRight(false);
        }

        @Override
        public void onMove(int x, int y) {

        }
    }
}

