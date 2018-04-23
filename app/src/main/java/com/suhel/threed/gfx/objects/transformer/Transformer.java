package com.suhel.threed.gfx.objects.transformer;

import com.suhel.threed.gfx.types.basic.Mat4;

public abstract class Transformer implements ITransformer {

    private Mat4 matrix = new Mat4();

    @Override
    public final Mat4 getMatrix() {
        return matrix;
    }

}
