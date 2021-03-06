package com.suhel.threed.gfx.objects.light;

import android.opengl.GLES20;

import com.suhel.threed.gfx.types.ShaderSpecs;
import com.suhel.threed.gfx.types.basic.Vec3;

public class PointLight extends Light {

    private int lightPosUniformHandle, lightColorUniformHandle;

    public PointLight(Vec3 position, int colorValue) {
        super(position, colorValue);
    }

    public PointLight(float x, float y, float z, int colorValue) {
        super(x, y, z, colorValue);
    }

    @Override
    public void prepareWithProgram(int program) {
        super.prepareWithProgram(program);
        lightPosUniformHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_LIGHT_POSITION);
        lightColorUniformHandle = GLES20.glGetUniformLocation(program, ShaderSpecs.UNI_LIGHT_COLOR);
    }

    @Override
    public void render(int program) {
        super.render(program);
        GLES20.glUniform3fv(lightPosUniformHandle, 1, getPosition().asArray(), 0);
        GLES20.glUniform4fv(lightColorUniformHandle, 1, getColor().asArray(), 0);
    }

}
