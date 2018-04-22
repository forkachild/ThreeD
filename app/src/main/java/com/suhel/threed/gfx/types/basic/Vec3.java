package com.suhel.threed.gfx.types.basic;

public class Vec3 extends Vec {

    public Vec3() {
        data = new float[]{0.0f, 0.0f, 0.0f};
    }

    public Vec3(float a) {
        this();
        data[0] = a;
        data[1] = a;
        data[2] = a;
    }

    public Vec3(float x, float y, float z) {
        this();
        data[0] = x;
        data[1] = y;
        data[2] = z;
    }

    public Vec3(Vec3 vec) {
        this();
        data[0] = vec.data[0];
        data[1] = vec.data[1];
        data[2] = vec.data[2];
    }

    public float getX() {
        return data[0];
    }

    public float getY() {
        return data[1];
    }

    public float getZ() {
        return data[2];
    }

}
