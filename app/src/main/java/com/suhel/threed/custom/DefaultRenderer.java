package com.suhel.threed.custom;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.suhel.threed.R;
import com.suhel.threed.gfx.Engine;
import com.suhel.threed.gfx.objects.camera.Camera;
import com.suhel.threed.gfx.objects.camera.SimpleEye;
import com.suhel.threed.gfx.objects.geometry.Geometry;
import com.suhel.threed.gfx.objects.light.Light;
import com.suhel.threed.gfx.objects.light.PointLight;
import com.suhel.threed.gfx.objects.viewport.Frustum;
import com.suhel.threed.utils.ModelReader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DefaultRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private Engine engine;
    private Frustum frustum;
    private Camera camera;
    private Geometry geometry;
    private Light light;

    public DefaultRenderer(Context context) {
        this.context = context;
        engine = new Engine(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES30.glEnable(GLES30.GL_CULL_FACE);
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        frustum = new Frustum(-1, 1, 1, -1, 0.7f, 7.0f);
        camera = new SimpleEye(0, 0, -6, 0, 0, 0);
        geometry = ModelReader.fromFile(context, R.raw.monkey, ModelReader.Type.OBJ);
        light = new PointLight(0, -5, 4, Color.WHITE);

        engine.setCamera(camera);
        engine.setViewPort(frustum);
        engine.setLight(light);
        engine.setGeometry(geometry);
//        geometry.rotate(45, 1, 0, 0);
//        geometry.scale(2.0f, 2.0f, 2.0f);

        engine.assignProgramToSlot(0, Engine.Program.fromRes(R.raw.sh_vertex, R.raw.sh_fragment));
        engine.prepare();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        frustum.setLeft(-ratio);
        frustum.setRight(ratio);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
//        geometry.rotate(2.0f, 0.0f, 1.0f, 0.0f);
        engine.render();
    }

    public void rotate(float angle, float x, float y, float z) {
        geometry.rotate(angle, x, y, z);
    }

    public void setScale(float x, float y, float z) {
        geometry.scale(x, y, z);
    }

}
