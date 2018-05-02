package com.suhel.threed.custom;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.util.Locale;

public class GameScreen extends GLSurfaceView implements GestureDetector.OnGestureListener
        , ScaleGestureDetector.OnScaleGestureListener {

    private DefaultRenderer engine;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private float scale = 1.0f;

    public GameScreen(Context context) {
        this(context, null);
    }

    public GameScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(3);
        engine = new DefaultRenderer(context);
        setRenderer(engine);
        gestureDetector = new GestureDetector(context, this);
        scaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        performClick();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        engine.rotate(distanceX / 5.0f, 0, -1, 0);
        engine.rotate(distanceY / 5.0f, -1, 0, 0);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
//        scale *= (detector.getScaleFactor());
//        scale = Math.max(1.0f, Math.min(scale, 2.5f));
        engine.setScale(detector.getScaleFactor(), detector.getScaleFactor(), detector.getScaleFactor());
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

}
