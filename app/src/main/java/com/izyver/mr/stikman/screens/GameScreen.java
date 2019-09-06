package com.izyver.mr.stikman.screens;

import android.graphics.Canvas;

import com.izyver.mr.stikman.game.OnScreenInput;

public interface GameScreen {

    void updateScreen(long deltaTime);

    void render(Canvas canvas);

    void resize(int width, int height);

    OnScreenInput getInputScreen();
}
