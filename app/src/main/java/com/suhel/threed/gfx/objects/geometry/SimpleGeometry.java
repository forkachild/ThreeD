package com.suhel.threed.gfx.objects.geometry;

import android.opengl.GLES30;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;

public class SimpleGeometry extends Geometry {

    private int positionAttribHandle;

    public SimpleGeometry(@NonNull float[] vertices, int vertexStride, @NonNull short[] indices) {
        super(vertices, vertexStride, indices);
        prepare();
    }

    @Override
    public void prepareWithProgram(int program) {
        super.prepareWithProgram(program);
        positionAttribHandle = GLES30.glGetAttribLocation(program, ShaderSpecs.IN_POSITION);
    }

    @Override
    public void render(int program) {
        super.render(program);
        GLES30.glEnableVertexAttribArray(positionAttribHandle);

        GLES30.glVertexAttribPointer(positionAttribHandle, getVertexStride(),
                GLES30.GL_FLOAT, false, getVertexStride() * ShaderSpecs.FLOAT_SIZE,
                getVertexBuffer());

        GLES30.glDrawElements(GLES30.GL_TRIANGLES, getIndexCount(), GLES30.GL_UNSIGNED_SHORT,
                getIndexBuffer());

        GLES30.glDisableVertexAttribArray(positionAttribHandle);
    }

}
