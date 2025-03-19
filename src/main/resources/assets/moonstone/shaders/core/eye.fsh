#version 150

#moj_import <matrix.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform float GameTime;
uniform int EndPortalLayers;

in vec4 texProj0;

const vec3[] COLORS = vec3[](
vec3(0.1, 0.4, 0.5), // 提高亮度
vec3(0.05, 0.4, 0.4), // 提高亮度
vec3(0.15, 0.45, 0.45), // 提高亮度
vec3(0.25, 0.5, 0.55), // 提高亮度
vec3(0.35, 0.55, 0.45), // 提高亮度
vec3(0.35, 0.45, 0.6), // 提高亮度
vec3(0.45, 0.55, 0.8), // 提高亮度
vec3(0.5, 0.75, 0.45), // 提高亮度
vec3(0.55, 0.65, 0.95), // 提高亮度
vec3(0.5, 0.55, 0.9), // 提高亮度
vec3(0.65, 0.65, 0.7), // 提高亮度
vec3(0.35, 1.2, 1.15), // 提高亮度
vec3(0.95, 0.7, 1.05), // 提高亮度
vec3(0.25, 1.55, 1.6), // 提高亮度
vec3(1.05, 1.9, 1.5), // 提高亮度
vec3(0.4, 1.55, 3.3) // 提高亮度
);

const mat4 SCALE_TRANSLATE = mat4(
0.5, 0.0, 0.0, 0.25,
0.0, 0.5, 0.0, 0.25,
0.0, 0.0, 1.0, 0.0,
0.0, 0.0, 0.0, 1.0
);

mat4 end_portal_layer(float layer) {
mat4 translate = mat4(
1.0, 0.0, 0.0, 17.0 / layer,
0.0, 1.0, 0.0, (2.0 + layer / 1.5) * (GameTime * 60),
0.0, 0.0, 1.0, 0.0,
0.0, 0.0, 0.0, 1.0
);

mat2 rotate = mat2_rotate_z(radians((layer * layer * 4321.0 + layer * 9.0) * 4.0));

mat2 scale = mat2((4.5 - layer / 4.0) * 1.3);

return mat4(scale * rotate) * translate * SCALE_TRANSLATE;
}

out vec4 fragColor;

void main() {
    vec3 color = textureProj(Sampler0, texProj0).rgb * COLORS[0];
    for (int i = 0; i < 4; i++) {
        color += textureProj(Sampler1, texProj0 * end_portal_layer(float(i + 1))).rgb * COLORS[i];
    }
    color *= 1.22;
    fragColor = vec4(color, 1.0);
}
