package com.suhel.threed.gfx;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.View;

public class Eye extends View {

    private int viewMatrixHandle = -1;

    public Eye(float x, float y, float z, float lookX, float lookY, float lookZ) {
        super(x, y, z, lookX, lookY, lookZ);
    }

    @Override
    public void prepare() {
        Matrix.setLookAtM(matrix, 0, position.getX(), position.getY(), position.getZ(),
                lookAt.getX(), lookAt.getY(), lookAt.getZ(), 0.0f, 1.0f, 0.0f);
    }

    @Override
    public void render() {
        Matrix.rotateM(matrix, 0, 1.0f, 0, 1, 0);
        viewMatrixHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_VIEW_MATRIX);
        GLES20.glUniformMatrix4fv(viewMatrixHandle, 1, false, matrix, 0);
    }

}
