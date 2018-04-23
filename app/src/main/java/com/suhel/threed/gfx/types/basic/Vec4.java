package com.suhel.threed.gfx.types.basic;

public class Vec4 extends Vec {

    public Vec4() {
        data = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    }

    public Vec4(float x, float y, float z, float w) {
        this();
        data[0] = x;
        data[1] = y;
        data[2] = z;
        data[3] = w;
    }

    public Vec4(float a) {
        this(a, a, a, a);
    }

    public Vec4(Vec3 vec, float w) {
        this(vec.getX(), vec.getY(), vec.getZ(), w);
    }

    public Vec4(float x, Vec3 vec) {
        this(x, vec.getX(), vec.getY(), vec.getZ());
    }

    public Vec4(Vec2 vec, float z, float w) {
        this(vec.getX(), vec.getY(), z, w);
    }

    public Vec4(float x, Vec2 vec, float w) {
        this(x, vec.getX(), vec.getY(), w);
    }

    public Vec4(float x, float y, Vec2 vec) {
        this(x, y, vec.getX(), vec.getY());
    }

    public Vec4(Vec2 vec0, Vec2 vec1) {
        this(vec0.getX(), vec0.getY(), vec1.getX(), vec1.getY());
    }

    public Vec4(Vec4 vec) {
        this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
    }

    public void set(float x, float y, float z, float w) {
        data[0] = x;
        data[1] = y;
        data[2] = z;
        data[3] = w;
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

    public float getW() {
        return data[3];
    }

    public void setW(float w) {
        data[3] = w;
    }


}
