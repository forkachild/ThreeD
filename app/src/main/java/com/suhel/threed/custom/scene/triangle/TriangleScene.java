package com.suhel.threed.custom.scene.triangle;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.suhel.threed.R;
import com.suhel.threed.gl.base.Model;
import com.suhel.threed.gl.base.Program;
import com.suhel.threed.gl.extended.ResourceScene;
import com.suhel.threed.utils.ModelHelper;

public class TriangleScene extends ResourceScene {

    private static final String PR_DEFAULT = "Default";
    private static final String M_ARROW = "Arrow";

    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];

    private float[] lighPos = new float[]{
            0.0f, 6.0f, 0.0f, 1.0f
    };

    private float[] lightColor = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f
    };

    private int viewMatrixHandle, projectionMatrixHandle, lightPosHandle, lightColorHandle;

    public TriangleScene(Context context) {
        super(context);
    }

    @Override
    protected int getProgramCount() {
        return 1;
    }

    @Override
    protected Program getProgram(int position) {
        return new TriangleProgram(getContext());
    }

    @Override
    protected String getProgramName(int position) {
        return PR_DEFAULT;
    }

    @Override
    protected int getModelCount() {
        return 1;
    }

    @Override
    protected Model getModel(int position) {
        return ModelHelper.fromFile(getContext(), R.raw.arrow);
    }

    @Override
    protected String getModelName(int position) {
        return M_ARROW;
    }

    @Override
    public void changeDimensions(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 0.7f, 7);
    }

    @Override
    public void prepare() {
        super.prepare();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -6, 0f,
                0f, 0f, 0f, 1.0f, 0.0f);
    }

    @Override
    public void render() {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        Program program = getProgram(PR_DEFAULT);
        GLES20.glUseProgram(program.getHandle());
        viewMatrixHandle = GLES20.glGetUniformLocation(program.getHandle(), "u_ViewMatrix");
        if (viewMatrixHandle != -1)
            GLES20.glUniformMatrix4fv(viewMatrixHandle, 1, false, viewMatrix, 0);

        projectionMatrixHandle = GLES20.glGetUniformLocation(program.getHandle(), "u_ProjectionMatrix");
        if (projectionMatrixHandle != -1)
            GLES20.glUniformMatrix4fv(projectionMatrixHandle, 1, false, projectionMatrix, 0);

        lightPosHandle = GLES20.glGetUniformLocation(program.getHandle(), "u_LightPos");
        if (lightPosHandle != -1)
            GLES20.glUniform4fv(lightPosHandle, 1, lighPos, 0);

        lightColorHandle = GLES20.glGetUniformLocation(program.getHandle(), "u_LightColor");
        if (lightColorHandle != -1)
            GLES20.glUniform4fv(lightColorHandle, 1, lightColor, 0);

        getModel(M_ARROW).startRender(program.getHandle());
        getModel(M_ARROW).finishRender(program.getHandle());
    }

}
