uniform mat4 u_ModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;

attribute vec4 a_Position;
attribute vec4 a_Normal;

varying vec4 v_Position;
varying vec4 v_Normal;

void main()
{
    mat4 u_MVMatrix = u_ViewMatrix * u_ModelMatrix;
    mat4 u_MVPMatrix = u_ProjectionMatrix * u_MVMatrix;

    v_Position = u_MVMatrix * a_Position;
    v_Normal = u_MVMatrix * a_Normal;

    gl_Position = u_MVPMatrix * a_Position;
}