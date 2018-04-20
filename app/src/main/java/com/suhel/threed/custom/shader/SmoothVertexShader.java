package com.suhel.threed.custom.shader;

import android.content.Context;

import com.suhel.threed.R;
import com.suhel.threed.gl.extended.ResourceShader;

public class SmoothVertexShader extends ResourceShader {

    public SmoothVertexShader(Context context) {
        super(context);
    }

    @Override
    protected int getSourceRes() {
        return R.raw.sh_vertex;
    }

    @Override
    public Type getType() {
        return Type.VERTEX_SHADER;
    }

}
