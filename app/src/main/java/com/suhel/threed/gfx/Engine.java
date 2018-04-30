package com.suhel.threed.gfx;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLES30;
import android.support.annotation.RawRes;
import android.util.Log;
import android.util.SparseIntArray;

import com.suhel.threed.gfx.objects.camera.Camera;
import com.suhel.threed.gfx.objects.geometry.Geometry;
import com.suhel.threed.gfx.objects.light.Light;
import com.suhel.threed.gfx.objects.viewport.ViewPort;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.utils.ResourcesHelper;
import com.suhel.threed.utils.GLHelper;

public class Engine implements IPrepareable {

    private Context context;

    private Camera camera;
    private Geometry geometry;
    private Light light;
    private ViewPort viewPort;

    private SparseIntArray shaderPrograms = new SparseIntArray();
    private int currentProgram = 0;

    public Engine(Context context) {
        this.context = context;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public void assignProgramToSlot(int slot, Program program) {
        program.compile(context);
        this.currentProgram = program.getProgram();
        shaderPrograms.put(slot, this.currentProgram);
    }

    public void setCurrentSlot(int slot) {
        if (shaderPrograms.get(slot) != 0) {
            this.currentProgram = shaderPrograms.get(slot);
            prepare();
        }
    }

    @Override
    public void prepare() {

        if (camera != null) {
            camera.prepareWithProgram(currentProgram);
        }

        if (viewPort != null) {
            viewPort.prepareWithProgram(currentProgram);
        }

        if (light != null) {
            light.prepareWithProgram(currentProgram);
        }

        if (geometry != null) {
            geometry.prepareWithProgram(currentProgram);
        }
    }

    public void render() {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        GLES30.glUseProgram(currentProgram);

        if (camera != null) {
            camera.render(currentProgram);
        }

        if (viewPort != null) {
            viewPort.render(currentProgram);
        }

        if (light != null) {
            light.render(currentProgram);
        }

        if (geometry != null) {
            geometry.render(currentProgram);
        }
    }

    public static class Program {

        @RawRes
        private int vertexShaderRes, fragmentShaderRes;
        private int program = -1;

        private Program(@RawRes int vertexShaderRes, @RawRes int fragmentShaderRes) {
            this.vertexShaderRes = vertexShaderRes;
            this.fragmentShaderRes = fragmentShaderRes;
        }

        public static Program fromRes(@RawRes int vertexShaderRes, @RawRes int fragmentShaderRes) {
            return new Program(vertexShaderRes, fragmentShaderRes);
        }

        private void compile(Context context) {
            if (program != -1)
                return;

            int[] flag = new int[1];
            int vs = GLES30.glCreateShader(GLES30.GL_VERTEX_SHADER);
            GLES30.glShaderSource(vs,
                    ResourcesHelper.readFile(context, vertexShaderRes));
            GLES30.glCompileShader(vs);
            GLHelper.checkGlError("VS");
            GLES30.glGetShaderiv(vs, GLES30.GL_COMPILE_STATUS, flag, 0);
            if (flag[0] != GLES30.GL_TRUE) {
                Log.e("VS Compile", "Error: " + GLES30.glGetShaderInfoLog(vs));
                GLES30.glDeleteShader(vs);
                return;
            }

            int fs = GLES30.glCreateShader(GLES30.GL_FRAGMENT_SHADER);
            GLES30.glShaderSource(fs,
                    ResourcesHelper.readFile(context, fragmentShaderRes));
            GLES30.glCompileShader(fs);
            GLHelper.checkGlError("FS");
            GLES30.glGetShaderiv(fs, GLES30.GL_COMPILE_STATUS, flag, 0);
            if (flag[0] != GLES30.GL_TRUE) {
                Log.e("FS Compile", "Error: " + GLES30.glGetShaderInfoLog(vs));
                GLES30.glDeleteShader(fs);
                return;
            }

            int p = GLES30.glCreateProgram();
            GLES30.glAttachShader(p, vs);
            GLHelper.checkGlError("VS Attach");
            GLES30.glAttachShader(p, fs);
            GLHelper.checkGlError("FS Attach");
            GLES30.glLinkProgram(p);
            GLHelper.checkGlError("Link");
            GLES30.glGetProgramiv(p, GLES30.GL_LINK_STATUS, flag, 0);
            if (flag[0] != GLES30.GL_TRUE) {
                Log.e("Link Program", "Error: " + GLES30.glGetProgramInfoLog(vs));
                GLES30.glDeleteProgram(p);
                return;
            }

            program = p;
        }

        public int getProgram() {
            return program;
        }

    }

}
