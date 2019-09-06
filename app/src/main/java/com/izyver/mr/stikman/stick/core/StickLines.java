package com.izyver.mr.stikman.stick.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describes an array of int, where every 4 elements is an line points
 * example:
 *          int[] = {ax, ay, bx, by} - one line
 *          int[] = {ax, ay, bx, by,  cx, cy, dx, dy} - two lines
 */

@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.SOURCE)
public @interface StickLines {
}
