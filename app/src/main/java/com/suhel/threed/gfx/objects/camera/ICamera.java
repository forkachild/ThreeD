package com.suhel.threed.gfx.objects.camera;

import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShadeable;

interface ICamera extends IPrepareable, IShadeable, IRenderable {

    Mat4 getMatrix();

}
