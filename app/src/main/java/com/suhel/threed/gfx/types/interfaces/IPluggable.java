package com.suhel.threed.gfx.types.interfaces;

import com.suhel.threed.gfx.types.PluginType;

public interface IPluggable extends IPrepareable, IRenderable {

    PluginType getPluginType();

}
