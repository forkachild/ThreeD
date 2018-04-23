package com.suhel.threed.custom;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.suhel.threed.R;
import com.suhel.threed.gfx.Engine;
import com.suhel.threed.gfx.objects.camera.SimpleEye;
import com.suhel.threed.gfx.objects.light.PointLight;
import com.suhel.threed.gfx.objects.transformer.BasicTransforms;
import com.suhel.threed.gfx.objects.viewport.Frustum;
import com.suhel.threed.utils.ModelHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DefaultRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private Engine engine;
    private Frustum frustum = new Frustum(-1, 1, 1, -1, 0.7f, 7.0f);

    public DefaultRenderer(Context context) {
        this.context = context;
        engine = new Engine(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
//        engine.addPlugin(new Perspective(1.0f, 0.7f, 7.0f));
//        engine.addPlugin(new Eye(0f, 0f, -6f, 0f, 0f, 0f));
//        engine.addPlugin(OBJReader.fromFile(context, R.raw.arrow));

        engine.setCamera(new SimpleEye(0, 0, -6, 0, 0, 0));
//        engine.setLight(new PointLight(0, 3, 0, Color.WHITE));
        engine.setViewPort(frustum);
        engine.setTransformer(new BasicTransforms(0,0,0,0,
                0,0,0,0,0,0));
        engine.setGeometry(ModelHelper.fromFile(context, R.raw.goru));

        engine.assignProgramToSlot(0, Engine.Program.fromRes(R.raw.sh_vertex, R.raw.sh_fragment));
        engine.prepare();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
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
        engine.render();
    }

}
