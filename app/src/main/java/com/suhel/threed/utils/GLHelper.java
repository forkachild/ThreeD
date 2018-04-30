package com.suhel.threed.utils;

import android.opengl.GLES30;
import android.util.Log;

public class GLHelper {

    public static void checkGlError(String TAG) {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e(TAG, "Error Code: " + error);
        }
    }

}
