package com.izyver.mr.stikman.screens;

import android.graphics.Canvas;

public interface GameScreen {

    void gameStarted();

    void updateScreen(long deltaTime);

    void render(Canvas canvas);

    void resize(int width, int height);

    OnScreenInput getInputScreen();
}
