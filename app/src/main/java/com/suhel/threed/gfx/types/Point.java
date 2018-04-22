package com.suhel.threed.gfx.types;

public class Point extends Coordinates3D {

    public Point(float x, float y, float z) {
        super(x, y, z);
    }

    public float getW() {
        return 1.0f;
    }

}
