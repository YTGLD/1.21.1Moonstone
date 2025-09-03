package com.ytgld.seeking_immortals.renderer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.lwjgl.opengl.GL11C.GL_LEQUAL;
import static org.lwjgl.opengl.GL11C.GL_LESS;

public class MRender extends RenderType {


    public static RenderType entityShadowsEEKING(ResourceLocation location) {
        return ENTITY_SHADOW.apply(location);
    }

    public static final TransparencyStateShard UNIFIED_TRANSPARENCY_STATE = new TransparencyStateShard("unified_transparency", () -> {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        RenderSystem.depthFunc(GL_LESS);
        RenderSystem.depthMask(false);

    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(GL_LEQUAL);
        RenderSystem.disableDepthTest();
    });
    public static final RenderType light = create(
            "lightning_si_do",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            true,
            CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(UNIFIED_TRANSPARENCY_STATE)
                    .setOutputState(WEATHER_TARGET)
                    .createCompositeState(false)
    );
    public static final Function<ResourceLocation, RenderType> ENTITY_SHADOW = Util.memoize(
            p_286151_ -> {
                CompositeState rendertype$compositestate = CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_SHADOW_SHADER)
                        .setTextureState(new TextureStateShard(p_286151_, false, false))
                        .setTransparencyState(UNIFIED_TRANSPARENCY_STATE)
                        .setCullState(NO_CULL)
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .setWriteMaskState(COLOR_WRITE)
                        .setDepthTestState(LEQUAL_DEPTH_TEST)
                        .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                        .createCompositeState(false);
                return create("entity_shadow_seeking", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, false, rendertype$compositestate);
            }
    );










    public MRender(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    private static ShaderInstance ShaderInstance_p_blood;
    private static ShaderInstance ShaderInstance_mls;
    private static ShaderInstance ShaderInstance_ging;

    protected static final OutputStateShard setOutputState = new OutputStateShard("set", () -> {
        RenderTarget target = MoonPost.getRenderTargetFor(SeekingImmortalsMod.POST);
        if (target != null) {
            target.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
            target.bindWrite(false);
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });

    public static final RenderType DRAGON_RAYS = create(
            "lightning",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            true,
            CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setCullState(NO_CULL)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(WEATHER_TARGET)
                    .createCompositeState(false)
    );
    protected static final OutputStateShard BEACON = new OutputStateShard("set_beacon", () -> {
        RenderTarget target = MoonPost.getRenderTargetFor(SeekingImmortalsMod.POST);
        if (target != null) {
            target.copyDepthFrom(Minecraft.getInstance().getMainRenderTarget());
            target.bindWrite(false);
        }
    }, () -> {
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
    });
    protected static final ShaderStateShard RENDER_STATE_SHARD_p_blood = new ShaderStateShard(MRender::getShaderInstance_p_blood);
    protected static final ShaderStateShard RENDER_STATE_SHARD_MLS = new ShaderStateShard(MRender::getShaderInstance_mls);
    protected static final ShaderStateShard RENDER_STATE_SHARD_ging = new ShaderStateShard(MRender::getShaderInstance_ging);


    public static final BiFunction<ResourceLocation, Boolean, RenderType> beacon =
            Util.memoize((p_234330_, p_234331_) -> {
                CompositeState rendertype$compositestate =
                        CompositeState.builder().setShaderState(RENDERTYPE_BEACON_BEAM_SHADER)
                                .setTextureState(new TextureStateShard(p_234330_, false, false))
                                .setTransparencyState(p_234331_ ? TRANSLUCENT_TRANSPARENCY : NO_TRANSPARENCY)
                                .setWriteMaskState(p_234331_ ? COLOR_WRITE : COLOR_DEPTH_WRITE)
                                .setOutputState(BEACON)
                                .createCompositeState(false);


                return create("beacon_beam", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 1536,
                        false, true, rendertype$compositestate);
            });
    public static final RenderType Bluer = create(
            "blue",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            CompositeState.builder()
                    .setShaderState(RENDER_STATE_SHARD_p_blood)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setCullState(NO_CULL)
                    .setDepthTestState(RenderStateShard.NO_DEPTH_TEST)
                    .setTextureState(MultiTextureStateShard.builder().
                            add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/blue.png"),
                                    false,
                                    false).add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/blue.png"),
                                    false, false).build()).createCompositeState(false));


    private static final RenderType GING =
            create("ging",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder().setCullState(NO_CULL)
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));

    public static final RenderType gingAlpha =
            create("ging_alpha",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder().setCullState(NO_CULL)
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setTransparencyState(LIGHTNING_TRANSPARENCY)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));
    public static final RenderType endBloodOutline =
            create("end_blood",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder().setCullState(NO_CULL)
                            .setShaderState(RENDER_STATE_SHARD_ging)
                            .setOutputState(setOutputState)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/ging.png"),
                                            false, false).build()).createCompositeState(false));
    public static final RenderType lightning_color_outline =create(
            "lightning",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS,
            1536,
            false,
            true,
            CompositeState.builder()
                    .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(setOutputState)
                    .createCompositeState(false)
    );

    public static final RenderType MLS =
            create("mls",
                    DefaultVertexFormat.POSITION,
                    VertexFormat.Mode.QUADS,
                    256,
                    false,
                    false,
                    CompositeState.builder()
                            .setShaderState(RENDER_STATE_SHARD_MLS)
                            .setCullState(NO_CULL)
                            .setTextureState(MultiTextureStateShard.builder().
                                    add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/mls.png"),
                                            false,
                                            false).add(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/mls.png"),
                                            false, false).build()).createCompositeState(false));
    public static final RenderType LIGHTING =
            create(
                    "lightning",
                    DefaultVertexFormat.POSITION_COLOR,
                    VertexFormat.Mode.QUADS,
                    1536,
                    false,
                    true,
                    CompositeState.builder()
                            .setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                            .setWriteMaskState(COLOR_DEPTH_WRITE)
                            .setCullState(NO_CULL)
                            .setTransparencyState(LIGHTNING_TRANSPARENCY)
                            .setOutputState(WEATHER_TARGET)
                            .createCompositeState(false)
            );
    public static final Function<ResourceLocation, RenderType> ENTITY_SHADOWOut = Util.memoize(
            p_286151_ -> {
                CompositeState rendertype$compositestate = CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_SHADOW_SHADER)
                        .setTextureState(new TextureStateShard(p_286151_, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .setOutputState(setOutputState)
                        .setWriteMaskState(COLOR_WRITE)
                        .setDepthTestState(LEQUAL_DEPTH_TEST)
                        .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                        .createCompositeState(false);
                return create("entity_shadow_seeking_outline", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, false, rendertype$compositestate);
            }
    );
    public static RenderType ENTITY_SHADOWOut(ResourceLocation location) {
        return ENTITY_SHADOWOut.apply(location);
    }
    public static RenderType ging() {
        return GING;
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
    public static void setShaderInstance_ging(ShaderInstance shaderInstance_ging) {
        ShaderInstance_ging = shaderInstance_ging;
    }


    public static void setShaderInstance_mls(ShaderInstance shaderInstance_mls) {
        ShaderInstance_mls = shaderInstance_mls;
    }
}
