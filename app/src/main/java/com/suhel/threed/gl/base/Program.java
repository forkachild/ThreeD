package com.suhel.threed.gl.base;

import android.opengl.GLES20;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public abstract class Program implements Prepareable {

    private int handle;
    private Set<Shader> shaders = new HashSet<>();
    private boolean isLinked = false;

    @Override
    public void prepare() {
        if (isLinked)
            return;

        for (Shader shader : getShaders()) {
            shader.compile();
            shaders.add(shader);
        }

        int program = GLES20.glCreateProgram();
        for (Shader shader : shaders) {
            GLES20.glAttachShader(program, shader.getHandle());
            checkGlError();
        }

        GLES20.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] != GLES20.GL_TRUE) {
            String error = GLES20.glGetProgramInfoLog(program);
            Log.e("Program", "Error " + error);
            GLES20.glDeleteProgram(program);
        } else {
            isLinked = true;
            handle = program;
        }
    }

    public int getHandle() {
        return handle;
    }

    public void setCurrent() {
        GLES20.glUseProgram(handle);
    }

    protected void checkGlError() {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("Attach Error", ": glError " + error);
//            throw new RuntimeException(": glError " + error);
        }
    }

    protected abstract Shader[] getShaders();

}
