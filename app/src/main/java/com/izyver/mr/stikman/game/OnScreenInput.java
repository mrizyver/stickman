package com.izyver.mr.stikman.game;

public abstract class OnScreenInput {

    public abstract void onDown(int x, int y);

    public abstract void onUp(int x, int y);

    public abstract void onMove(int x, int y);

    public static OnScreenInput mock() {
        return new OnScreenInput() {
            public void onDown(int x, int y) {
            }

            public void onUp(int x, int y) {
            }

            public void onMove(int x, int y) {
            }
        };
    }
}
