package com.suhel.threed.gl.extended;

import android.content.Context;
import android.support.annotation.RawRes;

import com.suhel.threed.gl.base.Shader;
import com.suhel.threed.utils.ResourcesHelper;

public abstract class ResourceShader extends Shader {

    private Context context;

    public ResourceShader(Context context) {
        this.context = context;
    }

    @Override
    protected final String getSource() {
        return ResourcesHelper.readFile(context, getSourceRes());
    }

    @RawRes
    protected abstract int getSourceRes();

}
