package com.suhel.threed.gfx.objects.light;

import android.support.annotation.ColorInt;

import com.suhel.threed.gfx.types.basic.Vec3;
import com.suhel.threed.gfx.types.basic.Vec4;

public abstract class Light implements ILight {

    private Vec3 position;
    private Vec4 color;

    @ColorInt
    private int colorValue;

    public Light(Vec3 position, @ColorInt int colorValue) {
        this.position = position;
        this.color = new Vec4();
        this.colorValue = colorValue;
        prepare();
    }

    public Light(float x, float y, float z, @ColorInt int colorValue) {
        this.position = new Vec3(x, y, z);
        this.color = new Vec4();
        this.colorValue = colorValue;
        prepare();
    }

    @Override
    public final Vec3 getPosition() {
        return position;
    }

    public final void setPosition(Vec3 position) {
        this.position = position;
    }

    public final void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
    }

    @Override
    public final Vec4 getColor() {
        return color;
    }

    public final int getColorValue() {
        return colorValue;
    }

    public final void setColorValue(@ColorInt int colorValue) {
        this.colorValue = colorValue;
        prepare();
    }

    @Override
    public void prepare() {
        float a = (colorValue >>> 6) & 0xFF;
        float r = (colorValue >>> 4) & 0xFF;
        float g = (colorValue >>> 2) & 0xFF;
        float b = colorValue & 0xFF;
        this.color.set(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
    }

}
