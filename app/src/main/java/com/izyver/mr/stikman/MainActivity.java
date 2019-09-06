package com.izyver.mr.stikman;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.izyver.mr.stikman.R;
import com.izyver.mr.stikman.game.GameView;
import com.izyver.mr.stikman.game.screens.StickmanScreen;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        LinearLayout gameLayout = findViewById(R.id.game_layout);
        GameView gameView = new GameView(this);
        gameLayout.addView(gameView);

        gameView.setGameScreen(new StickmanScreen());
        gameView.startGame();
    }
}
