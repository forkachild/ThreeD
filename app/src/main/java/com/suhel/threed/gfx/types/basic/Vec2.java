package com.suhel.threed.gfx.types.basic;

public class Vec2 extends Vec {

    public Vec2() {
        data = new float[]{0.0f, 0.0f};
    }

    public Vec2(float x, float y) {
        this();
        data[0] = x;
        data[1] = y;
    }

    public Vec2(float a) {
        this(a, a);
    }

    public Vec2(Vec2 vec) {
        this(vec.getX(), vec.getY());
    }

    public float getX() {
        return data[0];
    }

    public float getY() {
        return data[1];
    }

}
