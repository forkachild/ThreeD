//#extension GL_OES_standard_derivatives : enable

precision mediump float;

//varying vec4 v_Position;

void main() {
//    vec3 lightPos = vec3(0.0, 6.0, -4.0);
//    vec4 lightColor = vec4(1.0, 1.0, 0.8, 1.0);
//    vec3 dX = dFdx(v_Position.xyz);
//    vec3 dY = dFdy(v_Position.xyz);
//    vec3 normal = normalize(cross(dY, dX));
//
//    vec3 lightVector = normalize(v_Position.xyz - lightPos);
//    float lambert = dot(normal, lightVector);
//
//    gl_FragColor = lightColor * (1.8 + lambert);
    gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);
}
