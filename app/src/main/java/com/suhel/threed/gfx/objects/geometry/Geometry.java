package com.suhel.threed.gfx.objects.geometry;

import android.opengl.GLES30;
import android.opengl.Matrix;
import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.basic.Mat4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class Geometry implements IGeometry {

    private float[] vertices;
    private short[] indices;
    private Mat4 modelMatrix = new Mat4();
    private int vertexStride;

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    private int modelMatrixUniformHandle;

    public Geometry(@NonNull float[] vertices, int vertexStride, @NonNull short[] indices) {
        this.vertices = vertices;
        this.vertexStride = vertexStride;
        this.indices = indices;
    }

    @Override
    public int getVertexCount() {
        return vertices.length;
    }

    @Override
    public int getIndexCount() {
        return indices.length;
    }

    @Override
    public final int getVertexStride() {
        return vertexStride;
    }

    @Override
    public final FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    @Override
    public final ShortBuffer getIndexBuffer() {
        return indexBuffer;
    }

    @Override
    public void prepare() {
        Matrix.setIdentityM(modelMatrix.data, 0);

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * ShaderSpecs.FLOAT_SIZE);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        bb = ByteBuffer.allocateDirect(indices.length * ShaderSpecs.SHORT_SIZE);
        bb.order(ByteOrder.nativeOrder());
        indexBuffer = bb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    @Override
    public void prepareWithProgram(int program) {
        modelMatrixUniformHandle = GLES30.glGetUniformLocation(program,
                ShaderSpecs.UNI_GEOMETRY_MODEL_MATRIX);
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
