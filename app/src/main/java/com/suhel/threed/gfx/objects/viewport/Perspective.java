package com.suhel.threed.gfx.objects.viewport;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.suhel.threed.gfx.types.ShaderSpecs;

public class Perspective extends ViewPort {

    private float fovy, aspectRatio, near, far;

    private int projectionMatrixHandle;

    public Perspective(float fovy, float aspectRatio, float near, float far) {
        this.fovy = fovy;
        this.aspectRatio = aspectRatio;
        this.near = near;
        this.far = far;
        prepare();
    }

    public final float getFovy() {
        return fovy;
    }

    public final void setFovy(float fovy) {
        this.fovy = fovy;
        prepare();
    }

    public final float getAspectRatio() {
        return aspectRatio;
    }

    public final void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
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
        Matrix.perspectiveM(getMatrix().data, 0, fovy, aspectRatio, near, far);
    }

    @Override
    public void prepareWithProgram(int program) {
        projectionMatrixHandle = GLES20.glGetUniformLocation(program,
                ShaderSpecs.UNI_PROJECTION_MATRIX);
    }

    @Override
    public void render(int program) {
        GLES20.glUniformMatrix4fv(projectionMatrixHandle, 1,
                false, getMatrix().data, 0);
    }

}
