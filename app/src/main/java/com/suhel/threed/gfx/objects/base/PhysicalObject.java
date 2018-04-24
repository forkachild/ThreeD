package com.suhel.threed.gfx.objects.base;

import android.opengl.Matrix;

import com.suhel.threed.gfx.types.basic.Mat4;

public abstract class PhysicalObject implements IPhysicalObject {

    private Mat4 modelMatrix = new Mat4();

    private int modelMatrixUniformHandle;

    @Override
    public void setRotation(float angle, float x, float y, float z) {
        Matrix.setRotateM(modelMatrix.data, 0, angle, x, y, z);
    }

    @Override
    public void rotate(float angle, float x, float y, float z) {
        Matrix.rotateM(modelMatrix.data, 0, angle, x, y, z);
    }

    @Override
    public void translate(float x, float y, float z) {
        Matrix.translateM(modelMatrix.data, 0, x, y, z);
    }

    @Override
    public void scale(float x, float y, float z) {
        Matrix.scaleM(modelMatrix.data, 0, x, y, z);
    }

    @Override
    public void prepare() {
        Matrix.setIdentityM(modelMatrix.data, 0);
    }

    @Override
    public void prepareWithProgram(int program) {
    }

    @Override
    public void render(int program) {

    }

}
