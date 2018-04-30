package com.suhel.threed.gfx.objects.viewport;

import android.opengl.GLES30;
import android.opengl.Matrix;

import com.suhel.threed.gfx.types.ShaderSpecs;

public class Orthogonal extends ViewPort {

    private float left, top, right, bottom, near, far;

    private int projectionMatrixHandle;

    public Orthogonal(float left, float top, float right, float bottom, float near, float far) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.near = near;
        this.far = far;
        prepare();
    }

    public final float getLeft() {
        return left;
    }

    public final void setLeft(float left) {
        this.left = left;
        prepare();
    }

    public final float getTop() {
        return top;
    }

    public final void setTop(float top) {
        this.top = top;
        prepare();
    }

    public final float getRight() {
        return right;
    }

    public final void setRight(float right) {
        this.right = right;
        prepare();
    }

    public final float getBottom() {
        return bottom;
    }

    public final void setBottom(float bottom) {
        this.bottom = bottom;
        prepare();
    }

    public final float getNear() {
        return near;
    }

    public final void setNear(float near) {
        this.near = near;
        prepare();
    }

    public final float getFar() {
        return far;
    }

    public final void setFar(float far) {
        this.far = far;
        prepare();
    }

    @Override
    public void prepare() {
        Matrix.orthoM(getMatrix().data, 0, getLeft(), getRight(), getBottom(), getTop(),
                near, far);
    }

    @Override
    public void prepareWithProgram(int program) {
        projectionMatrixHandle = GLES30.glGetUniformLocation(program,
                ShaderSpecs.UNI_PROJECTION_MATRIX);
    }

    @Override
    public void render(int program) {
        GLES30.glUniformMatrix4fv(projectionMatrixHandle, 1,
                false, getMatrix().data, 0);
    }

}
