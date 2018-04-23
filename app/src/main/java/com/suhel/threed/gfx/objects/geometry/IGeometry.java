package com.suhel.threed.gfx.objects.geometry;

import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShadeable;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

interface IGeometry extends IPrepareable, IShadeable, IRenderable {

    int getVertexCount();

    int getIndexCount();

    FloatBuffer getVertexBuffer();

    ShortBuffer getIndexBuffer();

    int getVertexStride();

}
