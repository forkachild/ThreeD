package com.suhel.threed.gfx.objects.camera;

import android.opengl.GLES30;
import android.opengl.Matrix;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.basic.Vec3;

public abstract class Camera implements ICamera {

    private Mat4 matrix = new Mat4();
    private Vec3 eye;
    private Vec3 lookAt;
    private Mat4 modelMatrix = new Mat4();

    private int modelMatrixUniformHandle;

    public Camera() {
        this.eye = new Vec3();
        this.lookAt = new Vec3();
    }

    public Camera(@NonNull Vec3 eye, @NonNull Vec3 lookAt) {
        this.eye = eye;
        this.lookAt = lookAt;
    }

    public Camera(float eyeX, float eyeY, float eyeZ, float lookAtX, float lookAtY, float lookAtZ) {
        this.eye = new Vec3(eyeX, eyeY, eyeZ);
        this.lookAt = new Vec3(lookAtX, lookAtY, lookAtZ);
    }

    public final Vec3 getEye() {
        return eye;
    }

    public final void setEye(Vec3 eye) {
        this.eye = eye;
        prepare();
    }

    public final void setEye(float x, float y, float z) {
        this.eye = new Vec3(x, y, z);
        prepare();
    }

    public final Vec3 getLookAt() {
        return lookAt;
    }

    public final void setLookAt(Vec3 lookAt) {
        this.lookAt = lookAt;
        prepare();
    }

    public final void setLookAt(float x, float y, float z) {
        this.lookAt = new Vec3(x, y, z);
        prepare();
    }

    @Override
    public final Mat4 getMatrix() {
        return matrix;
    }

    @Override
    public void prepare() {
        Matrix.setIdentityM(modelMatrix.data, 0);
    }

    @Override
    public void prepareWithProgram(int program) {
        modelMatrixUniformHandle = GLES30.glGetUniformLocation(program,
                ShaderSpecs.UNI_CAMERA_MODEL_MATRIX);
    }

    @Override
    public void render(int program) {
        GLES30.glUniformMatrix4fv(modelMatrixUniformHandle, 1, false,
                modelMatrix.data, 0);
    }

    public final void rotate(float angle, float x, float y, float z) {
        Matrix.rotateM(modelMatrix.data, 0, angle, x, y, z);
    }

    public final void setRotation(float angle, float x, float y, float z) {
        Matrix.setRotateM(modelMatrix.data, 0, angle, x, y, z);
    }

    public final void translate(float x, float y, float z) {
        Matrix.translateM(modelMatrix.data, 0, x, y, z);
    }

    public final void scale(float x, float y, float z) {
        Matrix.scaleM(modelMatrix.data, 0, x, y, z);
    }
}
