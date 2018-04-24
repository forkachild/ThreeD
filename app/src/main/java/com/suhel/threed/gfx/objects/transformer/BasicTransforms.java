package com.suhel.threed.gfx.objects.transformer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.basic.Vec3;

public class BasicTransforms extends Transformer {

    private Vec3 translate, rotateAxis, scale;
    private float angle;

    private int modelMatrixHandle;

    public BasicTransforms(@NonNull Vec3 translate, @NonNull Vec3 rotateAxis,
                           @NonNull Vec3 scale, float angle) {
        this.translate = translate;
        this.rotateAxis = rotateAxis;
        this.scale = scale;
        this.angle = angle;
        prepare();
    }

    public BasicTransforms(float translateX, float translateY, float translateZ,
                           float rotateAxisX, float rotateAxisY, float rotateAxisZ,
                           float scaleX, float scaleY, float scaleZ,
                           float angle) {
        this.translate = new Vec3(translateX, translateY, translateZ);
        this.rotateAxis = new Vec3(rotateAxisX, rotateAxisY, rotateAxisZ);
        this.scale = new Vec3(scaleX, scaleY, scaleZ);
        this.angle = angle;
        prepare();
    }

    public final Vec3 getTranslate() {
        return translate;
    }

    public final void setTranslate(@NonNull Vec3 translate) {
        this.translate = translate;
        prepareTranslate();
    }

    public final void setTranslate(float translateX, float translateY, float translateZ) {
        this.translate.set(translateX, translateY, translateZ);
        prepareTranslate();
    }

    public final void setRotate(float rotateAxisX, float rotateAxisY,
                                float rotateAxisZ, float angle) {
        this.rotateAxis.set(rotateAxisX, rotateAxisY, rotateAxisZ);
        this.angle = angle;
        prepareRotate();
    }

    public final void setRotate(@NonNull Vec3 rotateAxis, float angle) {
        this.rotateAxis = rotateAxis;
        this.angle = angle;
        prepareRotate();
    }

    public final Vec3 getScale() {
        return scale;
    }

    public final void setScale(@NonNull Vec3 scale) {
        this.scale = scale;
        prepareScale();
    }

    public final void setScale(float scaleX, float scaleY, float scaleZ) {
        this.scale.set(scaleX, scaleY, scaleZ);
        prepareScale();
    }

    private void prepareTranslate() {
        Matrix.translateM(getMatrix().data, 0, translate.getX(),
                translate.getY(), translate.getZ());
    }

    private void prepareRotate() {
        Matrix.rotateM(getMatrix().data, 0, angle, rotateAxis.getX(),
                rotateAxis.getY(), rotateAxis.getZ());
    }

    private void prepareScale() {
        Matrix.scaleM(getMatrix().data, 0, scale.getX(), scale.getY(), scale.getZ());
    }

    @Override
    public void prepare() {
        Matrix.setIdentityM(getMatrix().data, 0);
//        prepareTranslate();
//        prepareRotate();
//        prepareScale();
    }

    @Override
    public void prepareWithProgram(int program) {
        modelMatrixHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_GEOMETRY_MODEL_MATRIX);
    }

    @Override
    public void render(int program) {
        GLES20.glUniformMatrix4fv(modelMatrixHandle, 1, false, getMatrix().data,
                0);
    }

}
