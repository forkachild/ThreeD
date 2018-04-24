package com.suhel.threed.gfx.objects.base;

import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShadeable;

public interface IPhysicalObject extends IPrepareable, IShadeable, IRenderable {

    void setRotation(float angle, float x, float y, float z);

    void rotate(float angle, float x, float y, float z);

    void translate(float x, float y, float z);

    void scale(float x, float y, float z);

}
