package com.suhel.threed.gfx.types;

public interface ShaderSpecs {

    int DEFAULT_MATRIX_SIZE = 16;
    int FLOAT_SIZE = 4;
    int INT_SIZE = 4;
    int SHORT_SIZE = 2;

    String ATTR_POSITION = "a_Position";
    String ATTR_NORMAL = "a_Normal";

    String UNI_GEOMETRY_MODEL_MATRIX = "u_GeometryModelMatrix";
    String UNI_LIGHT_MODEL_MATRIX = "u_LightModelMatrix";
    String UNI_CAMERA_MODEL_MATRIX = "u_CameraModelMatrix";
    String UNI_VIEW_MATRIX = "u_ViewMatrix";
    String UNI_PROJECTION_MATRIX = "u_ProjectionMatrix";
    String UNI_EYE_POSITION = "u_EyePosition";

    String UNI_LIGHT_POSITION = "u_LightPosition";
//    String UNI_LIGHT_POSITION1 = "u_LightPosition1";
//    String UNI_LIGHT_POSITION2 = "u_LightPosition2";
//    String UNI_LIGHT_POSITION3 = "u_LightPosition3";
//    String UNI_LIGHT_POSITION4 = "u_LightPosition4";
//    String UNI_LIGHT_POSITION5 = "u_LightPosition5";
//    String UNI_LIGHT_POSITION6 = "u_LightPosition6";
//    String UNI_LIGHT_POSITION7 = "u_LightPosition7";
//    String UNI_LIGHT_POSITION8 = "u_LightPosition8";
//    String UNI_LIGHT_POSITION9 = "u_LightPosition9";

    String UNI_LIGHT_COLOR = "u_LightColor";
//    String UNI_LIGHT_COLOR1 = "u_LightColor1";
//    String UNI_LIGHT_COLOR2 = "u_LightColor2";
//    String UNI_LIGHT_COLOR3 = "u_LightColor3";
//    String UNI_LIGHT_COLOR4 = "u_LightColor4";
//    String UNI_LIGHT_COLOR5 = "u_LightColor5";
//    String UNI_LIGHT_COLOR6 = "u_LightColor6";
//    String UNI_LIGHT_COLOR7 = "u_LightColor7";
//    String UNI_LIGHT_COLOR8 = "u_LightColor8";
//    String UNI_LIGHT_COLOR9 = "u_LightColor9";

}
