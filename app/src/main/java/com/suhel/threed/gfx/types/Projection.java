package com.suhel.threed.gfx.types;

import com.suhel.threed.gfx.types.PluginType;
import com.suhel.threed.gfx.types.interfaces.IPluggable;
import com.suhel.threed.gfx.types.interfaces.IShaded;

public abstract class Projection implements IPluggable, IShaded {

    protected float[] matrix = new float[16];
    protected float ratio = 0.0f, near = 0.0f, far = 0.0f;

    protected int program;

    public Projection(float ratio, float near, float far) {
        this.ratio = ratio;
        this.near = near;
        this.far = far;
        prepare();
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        prepare();
    }

    public float getNear() {
        return near;
    }

    public void setNear(float near) {
        this.near = near;
        prepare();
    }

    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        this.far = far;
        prepare();
    }

    @Override
    public final PluginType getPluginType() {
        return PluginType.PROJECTION;
    }

    @Override
    public final void setShaderProgram(int program) {
        this.program = program;
    }

}
