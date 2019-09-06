package com.izyver.mr.stikman.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.izyver.mr.stikman.game.screens.GameScreen;

public class GameView extends SurfaceView{

    private static final String TAG = "GameView";
    private final static int X = 0, Y = 1;
    private boolean isGameRunning = true;

    private Thread gameThread;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    private GameScreen gameScreen;
    private OnScreenInput inputScreen;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.inputScreen = gameScreen.getInputScreen();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        gameScreen.resize(getWidth(), getHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Game thread starting when view created
     */
    public void startGame() {
        if (gameScreen == null) throw new NullPointerException("gameScreen must be not null!");
        gameThread = new Thread(this::render);

        if (isAttachedToWindow()){
            gameThread.start();
        }else {
            post(() -> gameThread.start());
        }
    }

    public void stopGame(){
        isGameRunning = false;
        gameThread.interrupt();
        gameThread = null;
    }

    private void render() {
        while (isGameRunning){

            if(!surfaceHolder.getSurface().isValid()) continue;

            canvas = surfaceHolder.lockCanvas();
            gameScreen.render(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);

            gameScreen.updateScreen(16);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.e(TAG, "render: game is stop", e);
                isGameRunning = false;
                break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                inputScreen.onDown(x, y);
                break;
            case MotionEvent.ACTION_UP :
                inputScreen.onUp(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                inputScreen.onMove(x, y);
                break;
        }
        return true;
    }
}
