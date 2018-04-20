package com.suhel.threed.gl.extended;

import android.content.Context;

import com.suhel.threed.gl.base.Scene;

public abstract class ResourceScene extends Scene {

    private Context context;

    public ResourceScene(Context context) {
        this.context = context;
    }

    protected final Context getContext() {
        return context;
    }

}
