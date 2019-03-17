package com.izyver.mr.stikman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Movable, Runnable{

    private static final String TAG = "GameView";
    private final static int X = 0, Y = 1;
    private boolean
            moveToLeft = false,
            moveToRight = false,
            isGameRunning = true;
    private Paint paint;
    private float point[] = {10f,10f};

    private Thread gameThread;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;


    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        gameThread = new Thread(this::run);
    }

    private void draw(){
        if(surfaceHolder.getSurface().isValid() == false) return;

        if(moveToLeft){
            moveAside(-3);
            return;
        }else if(moveToRight){
            moveAside(3);
            return;
        }
    }

    void moveAside(int distance){
        canvas = surfaceHolder.lockCanvas();
        if (point[X] >= 0 && point[X] <= getMeasuredWidth())
            point[X] += distance;
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(point[X], point[Y], 10, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    public void startRun() {
        gameThread.start();
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
