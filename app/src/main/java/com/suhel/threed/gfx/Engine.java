package com.suhel.threed.gfx;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.RawRes;
import android.util.Log;
import android.util.SparseIntArray;

import com.suhel.threed.gfx.objects.camera.Camera;
import com.suhel.threed.gfx.objects.geometry.Geometry;
import com.suhel.threed.gfx.objects.light.Light;
import com.suhel.threed.gfx.objects.transformer.Transformer;
import com.suhel.threed.gfx.objects.viewport.ViewPort;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.utils.ResourcesHelper;
import com.suhel.threed.utils.ShaderHelper;

public class Engine implements IPrepareable {

    private Context context;

    private Camera camera;
    private Geometry geometry;
    private Light light;
    private Transformer transformer;
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

    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    public void assignProgramToSlot(int slot, Program program) {
        program.compile(context);
        this.currentProgram = program.getProgram();
        shaderPrograms.put(slot, this.currentProgram);
        prepare();
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

        if (geometry != null) {
            geometry.prepareWithProgram(currentProgram);
        }

        if (light != null) {
            light.prepareWithProgram(currentProgram);
        }

        if (transformer != null) {
            transformer.prepareWithProgram(currentProgram);
        }

        if (viewPort != null) {
            viewPort.prepareWithProgram(currentProgram);
        }
    }

    public void render() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(currentProgram);

        if (camera != null) {
            camera.render(currentProgram);
        }

        if (light != null) {
            light.render(currentProgram);
        }

        if (transformer != null) {
            transformer.render(currentProgram);
        }

        if (viewPort != null) {
            viewPort.render(currentProgram);
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
            int vs = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            GLES20.glShaderSource(vs,
                    ResourcesHelper.readFile(context, vertexShaderRes));
            GLES20.glCompileShader(vs);
            ShaderHelper.checkGlError("VS");
            GLES20.glGetShaderiv(vs, GLES20.GL_COMPILE_STATUS, flag, 0);
            if (flag[0] != GLES20.GL_TRUE) {
                Log.e("VS Compile", "Error: " + GLES20.glGetShaderInfoLog(vs));
                GLES20.glDeleteShader(vs);
                return;
            }

            int fs = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(fs,
                    ResourcesHelper.readFile(context, fragmentShaderRes));
            GLES20.glCompileShader(fs);
            ShaderHelper.checkGlError("FS");
            GLES20.glGetShaderiv(fs, GLES20.GL_COMPILE_STATUS, flag, 0);
            if (flag[0] != GLES20.GL_TRUE) {
                Log.e("FS Compile", "Error: " + GLES20.glGetShaderInfoLog(vs));
                GLES20.glDeleteShader(fs);
                return;
            }

            int p = GLES20.glCreateProgram();
            GLES20.glAttachShader(p, vs);
            ShaderHelper.checkGlError("VS Attach");
            GLES20.glAttachShader(p, fs);
            ShaderHelper.checkGlError("FS Attach");
            GLES20.glLinkProgram(p);
            ShaderHelper.checkGlError("Link");
            GLES20.glGetProgramiv(p, GLES20.GL_LINK_STATUS, flag, 0);
            if (flag[0] != GLES20.GL_TRUE) {
                Log.e("Link Program", "Error: " + GLES20.glGetProgramInfoLog(vs));
                GLES20.glDeleteProgram(p);
                return;
            }

            program = p;
        }

        public int getProgram() {
            return program;
        }

    }

}
