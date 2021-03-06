package com.suhel.threed.gfx.objects.viewport;

import com.suhel.threed.gfx.types.basic.Mat4;

public abstract class ViewPort implements IViewPort {

    private Mat4 matrix = new Mat4();

    @Override
    public final Mat4 getMatrix() {
        return matrix;
    }

}
