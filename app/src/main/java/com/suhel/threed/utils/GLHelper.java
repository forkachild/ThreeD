package com.suhel.threed.utils;

import android.opengl.GLES20;
import android.util.Log;

public class GLHelper {

    public static void checkGlError(String TAG) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "Error Code: " + error);
        }
    }

}
