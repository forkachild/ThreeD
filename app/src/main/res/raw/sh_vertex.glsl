#version 300 es

uniform mat4 GeometryModelMatrix;
uniform mat4 LightModelMatrix;
uniform mat4 ViewMatrix;
uniform mat4 ProjectionMatrix;
uniform vec3 LightPosition;

in vec3 VertexPosition;
in vec3 VertexNormal;

smooth out vec4 VaryingVertexPosition;
smooth out vec4 VaryingLightPosition;
smooth out vec4 VaryingVertexNormal;

void main()
{
    mat4 MVGeometry = ViewMatrix * GeometryModelMatrix;
//    mat4 MVPGeometry = ProjectionMatrix * MVGeometry;

    VaryingVertexPosition = MVGeometry * vec4(VertexPosition, 1.0);
    VaryingLightPosition = ViewMatrix * LightModelMatrix * vec4(LightPosition, 1.0);
    VaryingVertexNormal = normalize(MVGeometry * vec4(VertexNormal, 0.0));
    gl_Position = ProjectionMatrix * VaryingVertexPosition;
}
