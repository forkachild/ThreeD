package com.suhel.threed.utils;

public class MatrixHelper {

    public static void setScale(float[] m, float x, float y, float z) {
        m[0] = x;
        m[5] = y;
        m[10] = z;
        m[15] = 1.0f;
    }

}
