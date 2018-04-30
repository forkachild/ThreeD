#version 300 es
#extension GL_OES_standard_derivatives : enable

precision highp float;

uniform vec4 LightColor;

smooth in vec4 VaryingVertexPosition;
smooth in vec4 VaryingLightPosition;

out vec4 OutColor;

void main() {
    vec3 dX = dFdx(VaryingVertexPosition.xyz);
    vec3 dY = dFdy(VaryingVertexPosition.xyz);
    vec3 normal = normalize(cross(dX, dY));

    vec3 lightVector = normalize(VaryingVertexPosition.xyz - VaryingLightPosition.xyz);
    float lambert = max(dot(normal, lightVector), 0.1);
    float length = length(lightVector);
    float attenuation = 1.0 / (1.0 + (0.25 * length * length));
    vec4 ambient = vec4(0.01, 0.01, 0.01, 1.0);

    OutColor = ambient + (lambert * attenuation * LightColor);
}
