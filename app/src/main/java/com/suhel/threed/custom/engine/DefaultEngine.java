package com.suhel.threed.custom.engine;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.suhel.threed.custom.scene.triangle.TriangleScene;
import com.suhel.threed.gl.base.Scene;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DefaultEngine implements GLSurfaceView.Renderer {

    private Scene scene;

    public DefaultEngine(Context context) {
        scene = new TriangleScene(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        scene.prepare();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        scene.changeDimensions(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        scene.render();
    }

}
