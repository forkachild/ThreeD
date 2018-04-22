package com.suhel.threed.gfx.types;

public interface ShaderSpecs {

    int DEFAULT_MATRIX_SIZE = 16;
    int FLOAT_SIZE = 4;
    int INT_SIZE = 4;
    int SHORT_SIZE = 2;

    String ATTR_POSITION = "a_Position";
    String ATTR_NORMAL = "a_Normal";

    String UNI_MODEL_MATRIX = "u_ModelMatrix";
    String UNI_VIEW_MATRIX = "u_ViewMatrix";
    String UNI_PROJECTION_MATRIX = "u_ProjectionMatrix";

    String UNI_LIGHT_POSITION0 = "u_LightPos0";
    String UNI_LIGHT_POSITION1 = "u_LightPos1";
    String UNI_LIGHT_POSITION2 = "u_LightPos2";
    String UNI_LIGHT_POSITION3 = "u_LightPos3";
    String UNI_LIGHT_POSITION4 = "u_LightPos4";
    String UNI_LIGHT_POSITION5 = "u_LightPos5";
    String UNI_LIGHT_POSITION6 = "u_LightPos6";
    String UNI_LIGHT_POSITION7 = "u_LightPos7";
    String UNI_LIGHT_POSITION8 = "u_LightPos8";
    String UNI_LIGHT_POSITION9 = "u_LightPos9";

    String UNI_LIGHT_COLOR0 = "u_LightColor0";
    String UNI_LIGHT_COLOR1 = "u_LightColor1";
    String UNI_LIGHT_COLOR2 = "u_LightColor2";
    String UNI_LIGHT_COLOR3 = "u_LightColor3";
    String UNI_LIGHT_COLOR4 = "u_LightColor4";
    String UNI_LIGHT_COLOR5 = "u_LightColor5";
    String UNI_LIGHT_COLOR6 = "u_LightColor6";
    String UNI_LIGHT_COLOR7 = "u_LightColor7";
    String UNI_LIGHT_COLOR8 = "u_LightColor8";
    String UNI_LIGHT_COLOR9 = "u_LightColor9";

}
