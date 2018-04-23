package com.suhel.threed.gfx.types.basic;

public class Vec3 extends Vec {

    public Vec3() {
        data = new float[]{0.0f, 0.0f, 0.0f};
    }

    public Vec3(float x, float y, float z) {
        this();
        data[0] = x;
        data[1] = y;
        data[2] = z;
    }

    public Vec3(float a) {
        this(a, a, a);
    }

    public Vec3(Vec2 vec, float z) {
        this(vec.getX(), vec.getY(), z);
    }

    public Vec3(float x, Vec2 vec) {
        this(x, vec.getX(), vec.getY());
    }

    public Vec3(Vec3 vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public void set(float x, float y, float z) {
        data[0] = x;
        data[1] = y;
        data[2] = z;
    }

    public float getX() {
        return data[0];
    }

    public void setX(float x) {
        data[0] = x;
    }

    public float getY() {
        return data[1];
    }

    public void setY(float y) {
        data[1] = y;
    }

    public float getZ() {
        return data[2];
    }

    public void setZ(float z) {
        data[2] = z;
    }

}
