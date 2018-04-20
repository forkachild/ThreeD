package com.suhel.threed.custom.shader;

import android.content.Context;

import com.suhel.threed.R;
import com.suhel.threed.gl.extended.ResourceShader;

public class SmoothFragmentShader extends ResourceShader {

    public SmoothFragmentShader(Context context) {
        super(context);
    }

    @Override
    protected int getSourceRes() {
        return R.raw.sh_fragment;
    }

    @Override
    public Type getType() {
        return Type.FRAGMENT_SHADER;
    }

}
