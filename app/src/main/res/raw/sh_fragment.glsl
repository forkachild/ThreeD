precision mediump float;

uniform vec4 u_LightPos;
uniform vec4 u_LightColor;
//uniform float u_LightIntensity;

varying vec4 v_Position;
varying vec4 v_Normal;

void main() {
    float distance = length(u_LightPos - v_Position);
    vec4 lightVector = normalize(u_LightPos - v_Position);
    float incidence = max(dot(v_Normal, lightVector), 0.1);
    incidence = incidence / (1.0 + (0.001 * distance * distance));
    float diffuse = incidence + 0.1;
    gl_FragColor = u_LightColor * diffuse;
}
