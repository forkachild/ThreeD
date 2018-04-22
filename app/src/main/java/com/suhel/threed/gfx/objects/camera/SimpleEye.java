package com.suhel.threed.gfx.objects.camera;

import android.opengl.Matrix;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.basic.Vec3;

public class SimpleEye extends Camera {

    protected Vec3 up;

    public SimpleEye() {
        super();
        this.up = new Vec3(0.0f, 1.0f, 0.0f);
        prepare();
    }

    public SimpleEye(@NonNull Vec3 eye, @NonNull Vec3 lookAt) {
        super(eye, lookAt);
        this.up = new Vec3(0.0f, 1.0f, 0.0f);
        prepare();
    }

    public SimpleEye(@NonNull Vec3 eye, @NonNull Vec3 lookAt, @NonNull Vec3 up) {
        super(eye, lookAt);
        this.up = up;
        prepare();
    }

    public SimpleEye(float eyeX, float eyeY, float eyeZ,
                     float lookAtX, float lookAtY, float lookAtZ) {
        super(eyeX, eyeY, eyeZ, lookAtX, lookAtY, lookAtZ);
        this.up = new Vec3(0.0f, 1.0f, 0.0f);
        prepare();
    }

    public SimpleEye(float eyeX, float eyeY, float eyeZ,
                     float lookAtX, float lookAtY, float lookAtZ,
                     float upX, float upY, float upZ) {
        super(eyeX, eyeY, eyeZ, lookAtX, lookAtY, lookAtZ);
        this.up = new Vec3(upX, upY, upZ);
        prepare();
    }

    public final Vec3 getUp() {
        return up;
    }

    public final void setUp(Vec3 up) {
        this.up = up;
        prepare();
    }

    @Override
    public void prepare() {
        Matrix.setLookAtM(getMatrix().data, 0,
                getEye().getX(), getEye().getY(), getEye().getZ(),
                getLookAt().getX(), getLookAt().getY(), getLookAt().getZ(),
                up.getX(), up.getY(), up.getZ());
    }

}
