package com.suhel.threed.custom.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.suhel.threed.custom.engine.DefaultEngine;

public class GameScreen extends GLSurfaceView {

    private DefaultEngine engine;

    public GameScreen(Context context) {
        this(context, null);
    }

    public GameScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        engine = new DefaultEngine(context);
        setRenderer(engine);
    }

}
