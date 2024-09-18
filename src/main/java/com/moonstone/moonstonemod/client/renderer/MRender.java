package com.moonstone.moonstonemod.client.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }
    private static ShaderInstance ShaderInstance_gateway;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;
    private static ShaderInstance ShaderInstance_trail;
    public static ShaderInstance meteorTrailShader;
    public static ShaderInstance Shader_EYE;
    public static ShaderInstance Shader_snake;
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_Shader_snake = new RenderStateShard.ShaderStateShard(MRender::getShader_snake);





    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_meteorTrailShader = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_meteorTrailShader);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_Shader_EYE = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_Shader_EYE);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_gateway);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_MLS = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_ging = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_ging);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_trail = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_trail);
    public static final RenderType Snake_render = create(
            "lines_render",
            DefaultVertexFormat.POSITION_COLOR_NORMAL,
            VertexFormat.Mode.LINES,
            1536,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_Shader_snake)
                    .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.empty()))
                    .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setOutputState(ITEM_ENTITY_TARGET)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setCullState(NO_CULL)
                    .createCompositeState(false));



    public static final RenderType TRAIL =
            create("lines",
                    DefaultVertexFormat.POSITION_COLOR_NORMAL,
                    VertexFormat.Mode.LINES,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder().setShaderState(RENDER_STATE_SHARD_trail)
                            .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.empty()))
                            .setLayeringState(VIEW_OFFSET_Z_LAYERING).setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                            .setOutputState(ITEM_ENTITY_TARGET).setWriteMaskState(COLOR_DEPTH_WRITE).setCullState(NO_CULL)
                            .createCompositeState(false));
        private static final RenderType EYE =
            create("eye",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_Shader_EYE)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/eye.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/eye.png"),
                                            false, false).build()).createCompositeState(false));

    private static final RenderType GATEWAY =
            create("gateway",
            DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/necr_image.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/necr_image.png"),
                                            false, false).build()).createCompositeState(false));
    private static final RenderType GING =
            create("ging",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    private static final RenderType MLS =
            create("mls",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_MLS)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/mls.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/mls.png"),
                                            false, false).build()).createCompositeState(false));

    public static RenderType gateways() {
        return GATEWAY;
    }
    public static RenderType Snake() {
        return Snake_render;
    }
    public static RenderType t() {
        return TRAIL;
    }
    public static RenderType getMls() {
        return MLS;
    }

    public static RenderType ging() {
        return GING;
    }
    public static RenderType eye() {
        return EYE;
    }

    public static ShaderInstance getShaderInstance_Shader_EYE() {
        return Shader_EYE;
    }
    public static ShaderInstance getShaderInstance_mls() {
        return ShaderInstance_mls;
    }
    public static ShaderInstance getShaderInstance_ging() {
        return ShaderInstance_ging;
    }
    public static ShaderInstance getShaderInstance_trail() {
        return ShaderInstance_trail;
    }
    public static void setShaderInstance_EYE(ShaderInstance shaderInstance_ging) {
        Shader_EYE = shaderInstance_ging;
    }
    public static void setShaderInstance_ging(ShaderInstance shaderInstance_ging) {
        ShaderInstance_ging = shaderInstance_ging;
    }
    public static void setShaderInstance_trail(ShaderInstance shaderInstance_ging) {
        ShaderInstance_trail = shaderInstance_ging;
    }

    public static void setShader_snake(ShaderInstance shader_snake) {
        Shader_snake = shader_snake;
    }

    public static ShaderInstance getShader_snake() {
        return Shader_snake;
    }

    public static void setShaderInstance_mls(ShaderInstance shaderInstance_mls) {
        ShaderInstance_mls = shaderInstance_mls;
    }

    public static ShaderInstance getShaderInstance_gateway() {
        return ShaderInstance_gateway;
    }
    public static ShaderInstance getShaderInstance_meteorTrailShader() {
        return meteorTrailShader;
    }
    public static void setShaderInstance_gateway(ShaderInstance instance) {
        ShaderInstance_gateway = instance;
    }
}
