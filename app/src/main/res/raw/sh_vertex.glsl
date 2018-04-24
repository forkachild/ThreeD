attribute vec4 a_Position;

uniform mat4 u_GeometryModelMatrix;
uniform mat4 u_LightModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;
uniform vec3 u_LightPosition;

varying vec4 v_Position;
varying vec4 v_LightPosition;

void main()
{
    mat4 MVGeometry = u_ViewMatrix * u_GeometryModelMatrix;
    v_Position = MVGeometry* a_Position;
    v_LightPosition = u_ViewMatrix * u_LightModelMatrix * vec4(u_LightPosition, 1.0);
    gl_Position = u_ProjectionMatrix * MVGeometry * a_Position;
}
