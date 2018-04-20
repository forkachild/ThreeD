package com.suhel.threed.gl.extended;

import android.content.Context;

import com.suhel.threed.gl.base.Program;

public abstract class ResourceProgram extends Program {

    private Context context;

    public ResourceProgram(Context context) {
        this.context = context;
    }

    protected final Context getContext() {
        return context;
    }

}
