uniform mat4 u_ModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;

attribute vec4 a_Position;

//varying vec4 v_Position;

void main()
{
//    mat4 u_MVPMatrix = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix;
//    v_Position = u_ModelMatrix * a_Position;
    gl_Position = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix * a_Position;
}