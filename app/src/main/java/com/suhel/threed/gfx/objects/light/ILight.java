package com.suhel.threed.gfx.objects.light;

import com.suhel.threed.gfx.types.basic.Vec3;
import com.suhel.threed.gfx.types.basic.Vec4;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShadeable;

public interface ILight extends IPrepareable, IShadeable, IRenderable{

    Vec3 getPosition();

    Vec4 getColor();

}
