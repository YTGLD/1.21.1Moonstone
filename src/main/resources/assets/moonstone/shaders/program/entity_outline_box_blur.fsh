#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 sampleStep;

out vec4 fragColor;

void main() {
    vec4 blurred = vec4(0.0);
    float radius = 6;
    for (float a = -radius + 0.15; a <= radius; a += 1) {
        blurred += texture(DiffuseSampler, texCoord + sampleStep * a);
    }
    blurred += texture(DiffuseSampler, texCoord + sampleStep * radius) / 2.0;
    fragColor = vec4((blurred / (radius + 0.5)).rgb, blurred.a);
}
