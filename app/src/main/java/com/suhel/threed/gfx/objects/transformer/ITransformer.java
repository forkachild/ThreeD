package com.suhel.threed.gfx.objects.transformer;

import com.suhel.threed.gfx.types.basic.Mat4;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShadeable;

public interface ITransformer extends IPrepareable, IShadeable, IRenderable {

    Mat4 getMatrix();

}
