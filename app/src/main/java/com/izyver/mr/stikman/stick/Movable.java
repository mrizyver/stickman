package com.izyver.mr.stikman.stick;

public interface Movable {
    void toRight(boolean isMove);
    void toLeft(boolean isMove);
    default void ToUp(boolean isMove){}
    default void ToDown(boolean isMove){}
}
