package com.suhel.threed.gfx.objects.geometry;

import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class Geometry implements IGeometry {

    private float[] vertices;
    private short[] indices;
    private int vertexStride;

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    public Geometry(@NonNull float[] vertices, @NonNull short[] indices, int vertexStride) {
        this.vertices = vertices;
        this.indices = indices;
        this.vertexStride = vertexStride;
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



}
