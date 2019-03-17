package com.izyver.mr.stikman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.izyver.mr.stikman.stickman.Stickman;
import com.izyver.mr.stikman.stickman.StickmanEngine;

public class GameView extends SurfaceView implements Movable, Runnable{

    private static final String TAG = "GameView";
    private final static int X = 0, Y = 1;
    private boolean
            moveToLeft = false,
            moveToRight = false,
            isGameRunning = true;
    private Paint paint;
    private Thread gameThread;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    private StickmanEngine stickmanEngine;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        gameThread = new Thread(this::run);
        stickmanEngine = new StickmanEngine(0, 0, 200);


    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid() == false) return;

        if(moveToLeft){
            drawStickman(stickmanEngine.moveToLeft());
            return;
        }else if(moveToRight){
            return;
        }
    }

    private void drawStickman(Stickman stickman) {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawLines(stickman.getAllLines(), paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void startGame() {
        gameThread.start();
    }

    public void stopGame(){
        isGameRunning = false;
        gameThread.interrupt();
    }

    @Override
    public void run() {
        while (isGameRunning){
            updateGameScreen();
        }
    }

    private void updateGameScreen() {
        draw();
        try {
            gameThread.sleep(20);
        } catch (InterruptedException e) {
            Log.e(TAG, "updateGameScreen: game is stop", e);
            isGameRunning = false;
        }
    }

    @Override
    public void toRight(boolean isMove) {
        moveToRight = isMove;
    }

    @Override
    public void toLeft(boolean isMove) {
        moveToLeft = isMove;
    }

}
