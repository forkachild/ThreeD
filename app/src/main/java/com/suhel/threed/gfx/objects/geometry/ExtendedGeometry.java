package com.suhel.threed.gfx.objects.geometry;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ExtendedGeometry extends Geometry {

    private float[] normals;
    private int normalStride;

    private FloatBuffer normalBuffer;

    private int positionAttribHandle, normalAttribHandle;

    public ExtendedGeometry(@NonNull float[] vertices, int vertexStride,
                            @NonNull float[] normals, int normalStride,
                            @NonNull short[] indices) {
        super(vertices, vertexStride, indices);
        this.normals = normals;
        this.normalStride = normalStride;
        prepare();
    }

    @Override
    public void prepare() {
        super.prepare();
        ByteBuffer bb = ByteBuffer.allocateDirect(normals.length * ShaderSpecs.FLOAT_SIZE);
        bb.order(ByteOrder.nativeOrder());
        normalBuffer = bb.asFloatBuffer();
        normalBuffer.put(normals);
        normalBuffer.position(0);
    }

    public int getNormalStride() {
        return normalStride;
    }

    public FloatBuffer getNormalBuffer() {
        return normalBuffer;
    }

    @Override
    public void prepareWithProgram(int program) {
        positionAttribHandle = GLES20.glGetAttribLocation(program, ShaderSpecs.ATTR_POSITION);
        normalAttribHandle = GLES20.glGetAttribLocation(program, ShaderSpecs.ATTR_NORMAL);
    }

    @Override
    public void render(int program) {
        GLES20.glEnableVertexAttribArray(positionAttribHandle);
        GLES20.glEnableVertexAttribArray(normalAttribHandle);

        GLES20.glVertexAttribPointer(positionAttribHandle, getVertexStride(),
                GLES20.GL_FLOAT, false, getVertexStride() * ShaderSpecs.FLOAT_SIZE,
                getVertexBuffer());

        GLES20.glVertexAttribPointer(normalAttribHandle, getNormalStride(),
                GLES20.GL_FLOAT, false, getNormalStride() * ShaderSpecs.FLOAT_SIZE,
                getNormalBuffer());

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, getIndexCount(), GLES20.GL_UNSIGNED_SHORT,
                getIndexBuffer());

        GLES20.glDisableVertexAttribArray(normalAttribHandle);
        GLES20.glDisableVertexAttribArray(positionAttribHandle);
    }

}
