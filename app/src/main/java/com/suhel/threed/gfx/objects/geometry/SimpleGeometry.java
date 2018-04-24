package com.suhel.threed.gfx.objects.geometry;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.utils.ShaderHelper;

public class SimpleGeometry extends Geometry {

    private int positionAttribHandle;

    public SimpleGeometry(@NonNull float[] vertices, int vertexStride, @NonNull short[] indices) {
        super(vertices, vertexStride, indices);
        prepare();
    }

    @Override
    public void prepareWithProgram(int program) {
        positionAttribHandle = GLES20.glGetAttribLocation(program, ShaderSpecs.ATTR_POSITION);
    }

    @Override
    public void render(int program) {
        GLES20.glEnableVertexAttribArray(positionAttribHandle);

        GLES20.glVertexAttribPointer(positionAttribHandle, getVertexStride(),
                GLES20.GL_FLOAT, false, getVertexStride() * ShaderSpecs.FLOAT_SIZE,
                getVertexBuffer());

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, getIndexCount(), GLES20.GL_UNSIGNED_SHORT,
                getIndexBuffer());

        GLES20.glDisableVertexAttribArray(positionAttribHandle);
    }

}
