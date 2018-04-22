package com.suhel.threed.custom;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.suhel.threed.custom.DefaultRenderer;

public class GameScreen extends GLSurfaceView {

    private DefaultRenderer engine;

    public GameScreen(Context context) {
        this(context, null);
    }

    public GameScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        engine = new DefaultRenderer(context);
        setRenderer(engine);
    }

}
