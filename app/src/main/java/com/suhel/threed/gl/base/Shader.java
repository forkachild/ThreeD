package com.suhel.threed.gl.base;

import android.opengl.GLES20;
import android.util.Log;

public abstract class Shader {

    private int handle;
    private boolean isCompiled = false;

    public final void compile() {
        if (isCompiled)
            return;

        int shader = GLES20.glCreateShader(getType().getGLType());
        GLES20.glShaderSource(shader, getSource());
        GLES20.glCompileShader(shader);
        int[] compiledStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiledStatus, 0);
        if (compiledStatus[0] != GLES20.GL_TRUE) {
            GLES20.glDeleteShader(shader);
            Log.e("Shader", GLES20.glGetShaderInfoLog(shader));
        } else {
            isCompiled = true;
            handle = shader;
        }
    }

    protected abstract String getSource();

    public abstract Type getType();

    public final int getHandle() {
        return handle;
    }

    @Override
    public final boolean equals(Object obj) {
        return obj instanceof Shader && ((Shader) obj).handle == this.handle;
    }

    @Override
    public final int hashCode() {
        return handle;
    }

    public enum Type {
        VERTEX_SHADER,
        FRAGMENT_SHADER;

        public int getGLType() {
            switch (this) {
                case VERTEX_SHADER:

                    return GLES20.GL_VERTEX_SHADER;

                case FRAGMENT_SHADER:

                    return GLES20.GL_FRAGMENT_SHADER;

                default:

                    return -1;
            }
        }
    }

    public static final class ShaderException extends Exception {

        @Override
        public String getMessage() {
            return "Not enough information for building";
        }

    }

//    public final class Builder {
//
//        private Type type = null;
//        private String sourceCode = null;
//
//        public Shader build() throws ShaderException {
//            if (type == null || sourceCode == null || sourceCode.isEmpty())
//                throw new ShaderException();
//
//            int shader = GLES20.glCreateShader(type.getGLType());
//            GLES20.glShaderSource(shader, sourceCode);
//            GLES20.glCompileShader(shader);
//
//            Shader temp = new Shader();
//
//            temp.handle = shader;
//            temp.type = type;
//
//            return temp;
//        }
//
//        public Builder setType(Type type) {
//            this.type = type;
//            return this;
//        }
//
//        public Builder setSourceCode(String sourceCode) {
//            this.sourceCode = sourceCode;
//            return this;
//        }
//
//    }

}
