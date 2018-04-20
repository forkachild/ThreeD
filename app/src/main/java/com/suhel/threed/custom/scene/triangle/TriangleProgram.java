package com.suhel.threed.custom.scene.triangle;

import android.content.Context;

import com.suhel.threed.custom.shader.SmoothFragmentShader;
import com.suhel.threed.custom.shader.SmoothVertexShader;
import com.suhel.threed.gl.base.Shader;
import com.suhel.threed.gl.extended.ResourceProgram;

public class TriangleProgram extends ResourceProgram {

    private Shader vertexShader, fragmentShader;

    TriangleProgram(Context context) {
        super(context);
    }

    @Override
    protected Shader[] getShaders() {
        return new Shader[]{vertexShader, fragmentShader};
    }

    @Override
    public void prepare() {
        vertexShader = new SmoothVertexShader(getContext());
        fragmentShader = new SmoothFragmentShader(getContext());
        super.prepare();
    }

}
