package com.suhel.threed.gfx.objects.geometry;

import android.support.annotation.NonNull;

public class SimpleGeometry extends Geometry {

    private int positionAttribHandle;

    public SimpleGeometry(@NonNull float[] vertices, @NonNull short[] indices, int vertexStride) {
        super(vertices, indices, vertexStride);
        prepare();
    }

    @Override
    public void prepareWithProgram(int program) {

    }

    @Override
    public void render(int program) {

    }

}
