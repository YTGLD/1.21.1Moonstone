#version 100
precision mediump float;

struct Light {
    vec3 position;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

uniform Light uLight;
uniform vec3 uEyePosition;
uniform vec4 uMaterialColor;

varying vec3 vEyeNormal;
varying vec3 vEyePosition;

void main() {
    vec3 normal = normalize(vEyeNormal);
    vec3 lightDir = normalize(uLight.position - vEyePosition);

    // Ambient lighting
    vec3 ambient = uLight.ambient * uMaterialColor.rgb;

    // Diffuse lighting
    float diff = max(dot(normal, lightDir), 0.0);
    vec3 diffuse = uLight.diffuse * diff * uMaterialColor.rgb;

    // Specular lighting
    vec3 viewDir = normalize(uEyePosition - vEyePosition);
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
    vec3 specular = uLight.specular * spec * uMaterialColor.rgb;

    gl_FragColor = vec4(ambient + diffuse + specular, uMaterialColor.a);
}
