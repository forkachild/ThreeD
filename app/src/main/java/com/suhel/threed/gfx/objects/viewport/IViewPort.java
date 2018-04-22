package com.suhel.threed.gfx.objects.viewport;

import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;

interface IViewPort extends IPrepareable {

    Mat4 getMatrix();

}
