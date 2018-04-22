package com.suhel.threed.gfx;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.Projection;

public class Perspective extends Projection {

    private int projectionMatrixHandle = -1;

    public Perspective(float ratio, float near, float far) {
        super(ratio, near, far);
    }

    @Override
    public void prepare() {
        Matrix.frustumM(matrix, 0, -ratio, ratio, -1, 1, near, far);
    }

    @Override
    public void render() {
        projectionMatrixHandle = GLES20.glGetUniformLocation(program,
                ShaderSpecs.UNI_PROJECTION_MATRIX);
        GLES20.glUniformMatrix4fv(projectionMatrixHandle, 1, false, matrix, 0);
    }

}
