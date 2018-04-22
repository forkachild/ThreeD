package com.suhel.threed.gfx.objects.camera;

import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;

interface ICamera extends IPrepareable {

    Mat4 getMatrix();

}
