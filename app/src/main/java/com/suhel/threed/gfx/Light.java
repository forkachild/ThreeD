package com.suhel.threed.gfx;

import android.opengl.GLES20;
import android.support.annotation.ColorInt;

import com.suhel.threed.gfx.types.interfaces.IPluggable;
import com.suhel.threed.gfx.types.interfaces.IShaded;
import com.suhel.threed.gfx.types.PluginType;
import com.suhel.threed.gfx.types.Point;
import com.suhel.threed.gfx.types.ShaderSpecs;

public class Light implements IPluggable, IShaded {

    private float[] position = new float[4];
    private float[] color = new float[4];

    private int lightPositionHandle = -1, lightColorHandle = -1;
    private int program;

    public void setPosition(Point point) {
        this.position = new float[]{point.getX(), point.getY(), point.getZ(), point.getW()};
    }

    public void setPosition(float x, float y, float z) {
        this.position = new float[]{x, y, z, 1.0f};
    }

    public void setColor(@ColorInt int color) {
        int a = (color >>> 6) & 0xFF;
        int r = (color >>> 4) & 0xFF;
        int g = (color >>> 2) & 0xFF;
        int b = color & 0xFF;
        this.color = new float[]{
                (float) r / 255.0f,
                (float) g / 255.0f,
                (float) b / 255.0f,
                (float) a / 255.0f
        };
    }

    @Override
    public void prepare() {
    }

    @Override
    public PluginType getPluginType() {
        return PluginType.LIGHT;
    }

    @Override
    public void render() {
        lightColorHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_LIGHT_COLOR0);
        GLES20.glUniformMatrix4fv(lightColorHandle, 1, false, color, 0);
        lightPositionHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_LIGHT_POSITION0);
        GLES20.glUniformMatrix4fv(lightPositionHandle, 1, false, position, 0);
    }

    @Override
    public void setShaderProgram(int program) {
        this.program = program;
    }

}
