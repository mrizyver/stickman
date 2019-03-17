package com.izyver.mr.stikman;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        LinearLayout gameLayout = findViewById(R.id.game_layout);
        GameView gameView = new GameView(this);
        gameLayout.addView(gameView);
        Button  butToLeft = findViewById(R.id.button_left),
                butToRight = findViewById(R.id.button_right);
        butToLeft.setOnTouchListener((view, event)->{
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                gameView.toLeft(true);
                return true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                gameView.toLeft(false);
                return true;
            }
            return false;
        });
        butToRight.setOnTouchListener((view, event)->{
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                gameView.toRight(true);
                return true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                gameView.toRight(false);
                return true;
            }
            return false;
        });
        gameView.startRun();
    }

}
