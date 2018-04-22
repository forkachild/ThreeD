package com.suhel.threed.gfx.types;

import com.suhel.threed.gfx.types.PluginType;
import com.suhel.threed.gfx.types.Point;
import com.suhel.threed.gfx.types.Vector;
import com.suhel.threed.gfx.types.interfaces.IPluggable;
import com.suhel.threed.gfx.types.interfaces.IShaded;

public abstract class View implements IPluggable, IShaded {

    protected float[] matrix = new float[16];
    protected Point position;
    protected Vector lookAt;

    protected int program;

    public View(float x, float y, float z, float lookX, float lookY, float lookZ) {
        this.position = new Point(x, y, z);
        this.lookAt = new Vector(lookX, lookY, lookZ);
        prepare();
    }

    public void setPosition(Point position) {
        this.position = position;
        prepare();
    }

    public void setPosition(float x, float y, float z) {
        this.position.setCoordinates(x, y, z);
        prepare();
    }

    public void setLookAt(Vector lookAt) {
        this.lookAt = lookAt;
        prepare();
    }

    public void setLookAt(float lookX, float lookY, float lookZ) {
        this.lookAt.setCoordinates(lookX, lookY, lookZ);
        prepare();
    }

    @Override
    public final PluginType getPluginType() {
        return PluginType.VIEW;
    }

    @Override
    public final void setShaderProgram(int program) {
        this.program = program;
    }

}
