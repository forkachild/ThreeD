package com.suhel.threed.gfx.objects.geometry;

import android.opengl.GLES30;
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
        super.prepareWithProgram(program);
        positionAttribHandle = GLES30.glGetAttribLocation(program, ShaderSpecs.IN_POSITION);
        normalAttribHandle = GLES30.glGetAttribLocation(program, ShaderSpecs.IN_NORMAL);
    }

    @Override
    public void render(int program) {
        super.render(program);
        GLES30.glEnableVertexAttribArray(positionAttribHandle);
        GLES30.glEnableVertexAttribArray(normalAttribHandle);

        GLES30.glVertexAttribPointer(positionAttribHandle, getVertexStride(),
                GLES30.GL_FLOAT, false, getVertexStride() * ShaderSpecs.FLOAT_SIZE,
                getVertexBuffer());

        GLES30.glVertexAttribPointer(normalAttribHandle, getNormalStride(),
                GLES30.GL_FLOAT, false, getNormalStride() * ShaderSpecs.FLOAT_SIZE,
                getNormalBuffer());

        GLES30.glDrawElements(GLES30.GL_TRIANGLES, getIndexCount(), GLES30.GL_UNSIGNED_SHORT,
                getIndexBuffer());

        GLES30.glDisableVertexAttribArray(normalAttribHandle);
        GLES30.glDisableVertexAttribArray(positionAttribHandle);
    }

}
