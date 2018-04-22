package com.suhel.threed.gfx;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.suhel.threed.gfx.types.interfaces.IPluggable;
import com.suhel.threed.gfx.types.interfaces.IShaded;
import com.suhel.threed.gfx.types.PluginType;
import com.suhel.threed.gfx.types.ShaderSpecs;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class BetterModel implements IPluggable, IShaded {

    private float[] vertices;
    private short[] indices;
    private int vertexStride;

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    private float[] modelMatrix = new float[ShaderSpecs.DEFAULT_MATRIX_SIZE];

    private int program;
    private int positionAttribHandle, modelMatrixHandle;

    public BetterModel(float[] vertices, short[] indices, int vertexStride) {
        this.vertices = vertices;
        this.indices = indices;
        this.vertexStride = vertexStride;
    }

    @Override
    public PluginType getPluginType() {
        return PluginType.GEOMETRY;
    }

    @Override
    public void prepare() {
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

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.rotateM(modelMatrix, 0, 45, 1, 0, 0);
    }

    @Override
    public void render() {

        positionAttribHandle = GLES20.glGetAttribLocation(program, ShaderSpecs.ATTR_POSITION);
        GLES20.glEnableVertexAttribArray(positionAttribHandle);
        GLES20.glVertexAttribPointer(positionAttribHandle, vertexStride,
                GLES20.GL_FLOAT, false, vertexStride * ShaderSpecs.FLOAT_SIZE,
                vertexBuffer);

        modelMatrixHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_MODEL_MATRIX);
//        ShaderHelper.checkGlError("Load Model Matrix");
        GLES20.glUniformMatrix4fv(modelMatrixHandle, 1, false, modelMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT,
                indexBuffer);
        GLES20.glDisableVertexAttribArray(positionAttribHandle);
    }

    @Override
    public void setShaderProgram(int program) {
        this.program = program;
    }

}
