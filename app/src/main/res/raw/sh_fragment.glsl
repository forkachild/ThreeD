#extension GL_OES_standard_derivatives : enable

precision highp float;

uniform vec4 u_LightColor;
uniform vec3 u_EyePosition;

varying vec4 v_Position;
varying vec4 v_LightPosition;

void main() {
    vec3 dX = dFdx(v_Position.xyz);
    vec3 dY = dFdy(v_Position.xyz);
    vec3 normal = normalize(cross(dX, dY));

    vec3 lightVector = normalize(v_Position.xyz - v_LightPosition.xyz);
    float lambert = max(dot(normal, lightVector), 0.3);
    float length = length(lightVector);
    float attenuation = 1.0 / (1.0 + (length * length));
    vec4 ambient = vec4(0.02, 0.02, 0.02, 1.0);

    gl_FragColor = ambient + (u_LightColor * lambert * attenuation);
}
