package com.suhel.threed.gfx.types;

public enum PluginType {

    VIEW,
    PROJECTION,
    GEOMETRY,
    LIGHT,
    SHADER_VERTEX,
    SHADER_FRAGMENT,
    SHADER_GEOMETRY;

    public boolean isMultipleSupported() {
        switch (this) {

            case GEOMETRY:
            case LIGHT:
                return true;

            case VIEW:
            case PROJECTION:
            case SHADER_VERTEX:
            case SHADER_FRAGMENT:
            case SHADER_GEOMETRY:
            default:
                return false;
        }
    }

}
