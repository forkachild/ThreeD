package com.suhel.threed.custom;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.suhel.threed.R;
import com.suhel.threed.gfx.Engine;
import com.suhel.threed.gfx.objects.camera.SimpleEye;
import com.suhel.threed.gfx.objects.geometry.Geometry;
import com.suhel.threed.gfx.objects.light.Light;
import com.suhel.threed.gfx.objects.light.PointLight;
import com.suhel.threed.gfx.objects.viewport.Frustum;
import com.suhel.threed.utils.FileReader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DefaultRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private Engine engine;
    private Frustum frustum = new Frustum(-1, 1, 1, -1, 0.7f, 7.0f);
    private Geometry geometry;
    private Light light;

    public DefaultRenderer(Context context) {
        this.context = context;
        engine = new Engine(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        frustum = new Frustum(-1, 1, 1, -1, 0.7f, 7.0f);
        geometry = FileReader.fromFile(context, R.raw.goru, FileReader.Type.DAT);
        light = new PointLight(0, -5, 4, Color.WHITE);

        engine.setCamera(new SimpleEye(0, 0, -6, 0, 0, 0));
        engine.setViewPort(frustum);
        engine.setLight(light);
        engine.setGeometry(geometry);
        geometry.rotate(45, 1, 0, 0);
        engine.assignProgramToSlot(0, Engine.Program.fromRes(R.raw.sh_vertex, R.raw.sh_fragment));
        engine.prepare();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        frustum.setLeft(-ratio);
        frustum.setRight(ratio);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        geometry.rotate(2.0f, 0.0f, 1.0f, 0.0f);
        engine.render();
    }

}
