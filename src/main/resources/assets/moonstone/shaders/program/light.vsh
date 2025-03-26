#version 100
precision mediump float;

attribute vec4 vPosition;
attribute vec4 vNormal;
uniform mat4 uMVPMatrix;
uniform mat4 uNormalMatrix;
varying vec3 vEyeNormal;
varying vec3 vEyePosition;

void main() {
    vEyeNormal = vec3(uNormalMatrix * vNormal);
    vEyePosition = vec3(uMVPMatrix * vPosition);
    gl_Position = uMVPMatrix * vPosition;
}
