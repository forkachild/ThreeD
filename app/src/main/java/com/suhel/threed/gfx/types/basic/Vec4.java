package com.suhel.threed.gfx.types.basic;

public class Vec4 extends Vec {

    public Vec4() {
        data = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    }

    public Vec4(float a) {
        this();
        data[0] = a;
        data[1] = a;
        data[2] = a;
        data[3] = a;
    }

    public Vec4(float x, float y, float z, float w) {
        this();
        data[0] = x;
        data[1] = y;
        data[2] = z;
        data[3] = w;
    }

    public Vec4(Vec3 vec, float w) {
        this();
        data[0] = vec.data[0];
        data[1] = vec.data[1];
        data[2] = vec.data[2];
        data[3] = w;
    }

    public Vec4(float x, Vec3 vec) {
        this();
        data[0] = x;
        data[1] = vec.data[0];
        data[2] = vec.data[1];
        data[3] = vec.data[2];
    }

    public Vec4(Vec4 vec) {
        this();
        data[0] = vec.data[0];
        data[1] = vec.data[1];
        data[2] = vec.data[2];
        data[3] = vec.data[3];
    }

}
