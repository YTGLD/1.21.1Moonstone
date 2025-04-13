package com.moonstone.moonstonemod.client.renderer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;
import java.util.function.BiFunction;

public class MRender extends RenderType {
    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    private static ShaderInstance ShaderInstance_p_blood;


    private static ShaderInstance ShaderInstance_gateway;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;
    private static ShaderInstance ShaderInstance_trail;
    public static ShaderInstance Shader_EYE;
    public static ShaderInstance Shader_snake;


    protected static final OutputStateShard setOutputState = new OutputStateShard("set", () -> {
        RenderTarget target = MoonPost.getRenderTargetFor(MoonStoneMod.POST);
        if (target != null) {
            target.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
            target.bindWrite(false);
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });public static final TransparencyStateShard TransparencyStateShard = new TransparencyStateShard("lightning_transparency", () -> {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);

    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
    });

    public static final RenderType LIGHTNING = create(
            "lightning",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setDepthTestState(RenderStateShard.LEQUAL_DEPTH_TEST)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setTransparencyState(TransparencyStateShard)
                    .createCompositeState(false)
    );
    protected static final OutputStateShard BEACON = new OutputStateShard("set_beacon", () -> {
        RenderTarget target = MoonPost.getRenderTargetFor(MoonStoneMod.POST);
        if (target != null) {
            target.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
            target.bindWrite(false);
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_p_blood = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_p_blood);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_Shader_EYE = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_Shader_EYE);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_gateway);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_MLS = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_ging = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_ging);

    protected static final RenderStateShard.ShaderStateShard RENDER_STATE_SHARD_trail = new RenderStateShard.ShaderStateShard(MRender::getShaderInstance_trail);

    public static final BiFunction<ResourceLocation, Boolean, RenderType> beacon =
            Util.memoize((p_234330_, p_234331_) -> {
                CompositeState rendertype$compositestate =
                        RenderType.CompositeState.builder().setShaderState(RENDERTYPE_BEACON_BEAM_SHADER)
                                .setTextureState(new RenderStateShard.TextureStateShard(p_234330_, false, false))
                                .setTransparencyState(p_234331_ ? TRANSLUCENT_TRANSPARENCY : NO_TRANSPARENCY)
                                .setWriteMaskState(p_234331_ ? COLOR_WRITE : COLOR_DEPTH_WRITE)
                                .setOutputState(BEACON)
                                .createCompositeState(false);


                return create("beacon_beam", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 1536,
                        false, true, rendertype$compositestate);
            });
    public static final RenderType Snake_p_blood = create(
            "p_blood",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_p_blood)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(setOutputState)
                    .setTextureState(RenderStateShard.
                            MultiTextureStateShard.builder().
                            add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/p_blood.png"),
                                    false,
                                    false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/p_blood.png"),
                                    false, false).build()).createCompositeState(false));

    public static final RenderType Bluer = create(
            "blue",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_p_blood)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(setOutputState)
                    .setTextureState(RenderStateShard.
                            MultiTextureStateShard.builder().
                            add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/blue.png"),
                                    false,
                                    false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/blue.png"),
                                    false, false).build()).createCompositeState(false));



    public static final RenderType Snake_render = create(
            "lines_render",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(setOutputState)
                    .setTextureState(RenderStateShard.
                            MultiTextureStateShard.builder().
                            add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/p_blood.png"),
                                    false,
                                    false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/p_blood.png"),
                                    false, false).build()).createCompositeState(false));


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
    public static final RenderType man =
            create("man",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_Shader_EYE)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/man.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/man.png"),
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
                    RenderType.CompositeState.builder().setCullState(NO_CULL)
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    public static final RenderType MLS =
            create("mls",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_MLS)
                            .setCullState(NO_CULL)
                            .setTextureState(RenderStateShard.
                                    MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/mls.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,"textures/mls.png"),
                                            false, false).build()).createCompositeState(false));

    public static final BiFunction<ResourceLocation, Boolean, RenderType> ENTITY_TRANSLUCENT_EMISSIVE = Util.memoize(
            (p_286163_, p_286164_) -> {
                RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(p_286163_, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setWriteMaskState(COLOR_WRITE)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(p_286164_);
                return create("entity_translucent_emissive", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, rendertype$compositestate);
            }
    );
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

    public static ShaderInstance getShaderInstance_p_blood() {
        return ShaderInstance_p_blood;
    }

    public static void setShaderInstance_p_blood(ShaderInstance shaderInstance_p_blood) {
        ShaderInstance_p_blood = shaderInstance_p_blood;
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

    public static void setShaderInstance_mls(ShaderInstance shaderInstance_mls) {
        ShaderInstance_mls = shaderInstance_mls;
    }

    public static ShaderInstance getShaderInstance_gateway() {
        return ShaderInstance_gateway;
    }

    public static void setShaderInstance_gateway(ShaderInstance instance) {
        ShaderInstance_gateway = instance;
    }
}
