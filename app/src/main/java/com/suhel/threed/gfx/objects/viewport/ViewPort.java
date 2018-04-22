package com.suhel.threed.gfx.objects.viewport;

import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.basic.Vec3;

public abstract class ViewPort implements IViewPort {

    private Mat4 matrix = new Mat4();
    private Vec3 eye;
    private Vec3 lookAt;

    public ViewPort() {
        this.eye = new Vec3();
        this.eye = new Vec3();
    }

    public ViewPort(@NonNull Vec3 eye, @NonNull Vec3 lookAt) {
        this.eye = eye;
        this.lookAt = lookAt;
    }

    public ViewPort(float eyeX, float eyeY, float eyeZ, float lookAtX, float lookAtY, float lookAtZ) {
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

}
