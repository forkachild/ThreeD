package com.suhel.threed.gl.base;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Model implements Renderable, Prepareable {

    private float[] vertices, normals;
    private short[] indices;
    private float[] modelMatrix = new float[16];
    private int vertexStride, normalStride, indexStride;

    private FloatBuffer verticesBuffer, normalsBuffer;
    private ShortBuffer indicesBuffer;

    private int positionHandle, normalHandle, modelMatrixHandle;

    public Model(float[] vertices, float[] normals, short[] indices,
                 int vertexStride, int normalStride, int indexStride) {
        this.vertices = vertices;
        this.normals = normals;
        this.indices = indices;
        this.vertexStride = vertexStride;
        this.normalStride = normalStride;
        this.indexStride = indexStride;
    }

    @Override
    public void prepare() {
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        verticesBuffer = bb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);

        bb = ByteBuffer.allocateDirect(normals.length * 4);
        bb.order(ByteOrder.nativeOrder());
        normalsBuffer = bb.asFloatBuffer();
        normalsBuffer.put(normals);
        normalsBuffer.position(0);

        bb = ByteBuffer.allocateDirect(indices.length * 2);
        bb.order(ByteOrder.nativeOrder());
        indicesBuffer = bb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.rotateM(modelMatrix, 0, 45f, 1f, 0, 0);
    }

    @Override
    public void startRender(int program) {
        Matrix.rotateM(modelMatrix, 0, 0.8f, 0, 1f, 0);
        positionHandle = GLES20.glGetAttribLocation(program, "a_Position");
        if (positionHandle != -1) {
            GLES20.glEnableVertexAttribArray(positionHandle);
            GLES20.glVertexAttribPointer(positionHandle, vertexStride,
                    GLES20.GL_FLOAT, false, vertexStride * 4, verticesBuffer);
        }
        normalHandle = GLES20.glGetAttribLocation(program, "a_Normal");
        if (normalHandle != -1) {
            GLES20.glEnableVertexAttribArray(normalHandle);
            GLES20.glVertexAttribPointer(normalHandle, normalStride,
                    GLES20.GL_FLOAT, false, normalStride * 4, normalsBuffer);
        }
        modelMatrixHandle = GLES20.glGetUniformLocation(program, "u_ModelMatrix");
        if (modelMatrixHandle != -1) {
            GLES20.glUniformMatrix4fv(modelMatrixHandle, 1,
                    false, modelMatrix, 0);
        }
    }

    @Override
    public void finishRender(int program) {
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, indicesBuffer);
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
    }

}
