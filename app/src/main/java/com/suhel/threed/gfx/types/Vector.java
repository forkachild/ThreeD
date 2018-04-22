package com.suhel.threed.gfx.types;

public class Vector extends Coordinates3D {

    public Vector(float x, float y, float z) {
        super(x, y, z);
    }

    public float getW() {
        return 0.0f;
    }

}
