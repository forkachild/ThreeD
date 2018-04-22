package com.suhel.threed.gfx;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.RawRes;
import android.util.Log;
import android.util.SparseIntArray;

import com.suhel.threed.gfx.types.Projection;
import com.suhel.threed.gfx.types.View;
import com.suhel.threed.gfx.types.interfaces.IPluggable;
import com.suhel.threed.gfx.types.interfaces.IPrepareable;
import com.suhel.threed.gfx.types.interfaces.IRenderable;
import com.suhel.threed.gfx.types.interfaces.IShaded;
import com.suhel.threed.utils.ResourcesHelper;
import com.suhel.threed.utils.ShaderHelper;

import java.util.ArrayList;
import java.util.List;

public class BetterEngine implements IPrepareable, IRenderable {

    private Context context;
    private Projection projection;
    private View view;
    private List<IPluggable> geometries = new ArrayList<>();
    private List<IShaded> shadeds = new ArrayList<>();

    private SparseIntArray shaderPrograms = new SparseIntArray();
    private int currentProgram = 0;

    public BetterEngine(Context context) {
        this.context = context;
    }

    public void addPlugin(IPluggable pluggable) {
        switch (pluggable.getPluginType()) {

            case VIEW:

                view = (View) pluggable;
                break;

            case PROJECTION:

                projection = (Projection) pluggable;
                break;

            case GEOMETRY:

                geometries.add(pluggable);
                break;

        }
        if (pluggable instanceof IShaded) {
            shadeds.add((IShaded) pluggable);
        }
    }

    public void assignProgramToSlot(int slot, Program program) {
        program.compile(context);
        this.currentProgram = program.getProgram();
        shaderPrograms.put(slot, this.currentProgram);
        updateShader();
    }

    public void setCurrentSlot(int slot) {
        if (shaderPrograms.get(slot) != 0) {
            this.currentProgram = shaderPrograms.get(slot);
            updateShader();
        }
    }

    private void updateShader() {
        for (IShaded shaded : shadeds) {
            shaded.setShaderProgram(currentProgram);
        }
    }

    public void setResolution(int width, int height) {
        projection.setRatio((float) width / height);
    }

    @Override
    public void prepare() {
        if (view != null) {
            view.prepare();
        }

        if (projection != null) {
            projection.prepare();
        }

        for (IPluggable geometry : geometries)
            geometry.prepare();
    }

    @Override
    public void render() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(currentProgram);

        if (view != null) {
            view.render();
        }

        if (projection != null) {
            projection.render();
        }

        for (IPluggable geometry : geometries)
            geometry.render();
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
