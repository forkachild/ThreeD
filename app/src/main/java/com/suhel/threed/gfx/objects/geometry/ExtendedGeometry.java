package com.suhel.threed.gfx.objects.geometry;

import android.support.annotation.NonNull;

import com.suhel.threed.gfx.types.ShaderSpecs;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ExtendedGeometry extends Geometry {

    private float[] normals;

    private FloatBuffer normalBuffer;

    public ExtendedGeometry(@NonNull float[] vertices, @NonNull float[] normals,
                            @NonNull short[] indices, int vertexStride) {
        super(vertices, indices, vertexStride);
        this.normals = normals;
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

    public FloatBuffer getNormalBuffer() {
        return normalBuffer;
    }

}
