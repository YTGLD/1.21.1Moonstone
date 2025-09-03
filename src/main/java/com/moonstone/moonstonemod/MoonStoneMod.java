package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.client.particle.blood;
import com.moonstone.moonstonemod.client.particle.blue;
import com.moonstone.moonstonemod.client.particle.popr;
import com.moonstone.moonstonemod.client.particle.red;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.crafting.AllCrafting;
import com.moonstone.moonstonemod.crafting.MoonRecipeProvider;
import com.moonstone.moonstonemod.entity.client.*;
import com.moonstone.moonstonemod.entity.client.blood.BloodBatRenderer;
import com.moonstone.moonstonemod.entity.client.zombie.CellZombieG;
import com.moonstone.moonstonemod.entity.client.zombie.CellZombieN;
import com.moonstone.moonstonemod.entity.client.zombie.ZombieRenderer;
import com.moonstone.moonstonemod.event.MEvent;
import com.moonstone.moonstonemod.event.ZombieEvent;
import com.moonstone.moonstonemod.event.old.*;
import com.moonstone.moonstonemod.init.Tab;
import com.moonstone.moonstonemod.init.items.BookItems;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.*;
import com.ytgld.seeking_immortals.ClientConfig;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.client.particle.cube;
import com.ytgld.seeking_immortals.event.key.ClientEvent;
import com.ytgld.seeking_immortals.event.key.SINetworkHandler;
import com.ytgld.seeking_immortals.event.now.EventHandler;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.init.Keys;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.nightmare.tip.ToolTip;
import com.ytgld.seeking_immortals.test_entity.client.LightingRender;
import com.ytgld.seeking_immortals.test_entity.client.OrbEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Mod(MoonStoneMod.MODID)
public class MoonStoneMod {


    public static final String MODID = "moonstone";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ResourceLocation POST_Blood = ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
            "shaders/post/entity_outline_blood.json");
    private void registerPayloadHandler(final RegisterPayloadHandlersEvent evt) {
        SINetworkHandler.register(evt.registrar("1.0"));
    }
    public MoonStoneMod(IEventBus eventBus, ModContainer modContainer){
        {
            NeoForge.EVENT_BUS.register(new com.ytgld.seeking_immortals.event.old.NewEvent());
            NeoForge.EVENT_BUS.register(new AdvancementEvt());
            NeoForge.EVENT_BUS.register(new EventHandler());
            eventBus.addListener(this::registerPayloadHandler);

            com.ytgld.seeking_immortals.init.Effects.REGISTRY.register(eventBus);
            com.ytgld.seeking_immortals.init.AttReg.REGISTRY.register(eventBus);
            com.ytgld.seeking_immortals.init.DataReg.REGISTRY.register(eventBus);
            com.ytgld.seeking_immortals.init.Items.REGISTRY.register(eventBus);
            com.ytgld.seeking_immortals.init.EntityTs.REGISTRY.register(eventBus);
            com.ytgld.seeking_immortals.init.Particles.PARTICLE_TYPES.register(eventBus);
            com.ytgld.seeking_immortals.init.Tab.TABS.register(eventBus);
            modContainer.registerConfig(ModConfig.Type.COMMON, com.ytgld.seeking_immortals.Config.fc);
            modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT);
        }
        NeoForge.EVENT_BUS.register(new AllEvent());
        

        NeoForge.EVENT_BUS.register(new LootEvent());
        NeoForge.EVENT_BUS.register(new Village());
        NeoForge.EVENT_BUS.register(new LootTableEvent());
        NeoForge.EVENT_BUS.register(new NewEvent());
        NeoForge.EVENT_BUS.register(new BookEvt());
        NeoForge.EVENT_BUS.register(new EquippedEvt());
        NeoForge.EVENT_BUS.register(new TextEvt());
        NeoForge.EVENT_BUS.register(new MEvent());
        NeoForge.EVENT_BUS.register(new ZombieEvent());

        Drugs.REGISTRY.register(eventBus);

        BookItems.REGISTRY.register(eventBus);
        AttReg.REGISTRY.register(eventBus);
        DNAItems.REGISTRY.register(eventBus);
        LootReg.REGISTRY.register(eventBus);
        EntityTs.REGISTRY.register(eventBus);
        DataReg.REGISTRY.register(eventBus);
        Effects.REGISTRY.register(eventBus);
        Particles.PARTICLE_TYPES.register(eventBus);
        Items.REGISTRY.register(eventBus);



        AllCrafting.RECIPE_SERIALIZER_REGISTRY.register(eventBus);
        eventBus.addListener(this::gatherData);
        Tab.TABS.register(eventBus);
    }
    public void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        gen.addProvider(event.includeServer(), new MoonRecipeProvider(packOutput, lookupProvider));

    }
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent evt) {
            NeoForge.EVENT_BUS.register(new ClientEvent());
        }

        @SubscribeEvent // on the mod event bus only on the physical client
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(Keys.KEY_MAPPING_LAZY_R);
        }
        @SubscribeEvent
        public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event){
            event.register(NightmareTooltip.class, Function.identity());
            event.register(ToolTip.class, Function.identity());
        }
        @SubscribeEvent
        public static void EntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(com.ytgld.seeking_immortals.init.EntityTs.orb_entity.get(), OrbEntityRenderer::new);
            event.registerEntityRenderer(com.ytgld.seeking_immortals.init.EntityTs.lighting.get(), LightingRender::new);

        }
        @SubscribeEvent
        public static void RegisterStageEvent(RenderLevelStageEvent.RegisterStageEvent event) {
            RenderType renderType = com.ytgld.seeking_immortals.renderer.MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/p_blood.png"), true);
            stage_particles = event.register(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "seeking_particles"),
                    renderType);
        }
        @SubscribeEvent
        public static void registerFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(com.ytgld.seeking_immortals.init.Particles.blood.get(), com.ytgld.seeking_immortals.client.particle.blood.Provider::new);
            event.registerSpriteSet(com.ytgld.seeking_immortals.init.Particles.cube.get(), cube.Provider::new);

        }
        @SubscribeEvent
        public static void EntityRenderersEvent(RegisterShadersEvent event) {
            try {

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"rendertype_mls"),
                        DefaultVertexFormat.POSITION_TEX_COLOR), com.ytgld.seeking_immortals.renderer.MRender::setShaderInstance_mls);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID, "rendertype_ging"),
                        DefaultVertexFormat.POSITION_TEX_COLOR), com.ytgld.seeking_immortals.renderer.MRender::setShaderInstance_ging);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"p_blood"),
                        DefaultVertexFormat.POSITION_TEX_COLOR), com.ytgld.seeking_immortals.renderer.MRender::setShaderInstance_p_blood);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
    public static RenderLevelStageEvent.Stage stage_particles ;
    @EventBusSubscriber(modid = MoonStoneMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class Client {


        @SubscribeEvent
        public static void RegisterStageEvent(RenderLevelStageEvent.RegisterStageEvent event) {
            RenderType renderType = MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, "textures/p_blood.png"), true);
            stage_particles = event.register(ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID, "moon_particles"),
                    renderType);
        }
        public static void renderPoseStack(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, float s,float a  ) {
            int stacks = 10; // 垂直方向的分割数
            int slices = 10; // 水平方向的分割数
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = s * (float) Math.cos(phi0);
                    float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = s * (float) Math.cos(phi0);
                    float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = s * (float) Math.cos(phi1);
                    float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = s * (float) Math.cos(phi1);
                    float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(1.0f, 0,0, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(1.0f, 0,0, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(1.0f, 0,0, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                    vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(1.0f, 0,0, a).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                }
            }
        }

        @SubscribeEvent
        public static void registerFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(Particles.gold.get(), red.Provider::new);
            event.registerSpriteSet(Particles.blue.get(), blue.Provider::new);
            event.registerSpriteSet(Particles.popr.get(), popr.Provider::new);
            event.registerSpriteSet(Particles.blood.get(), blood.Provider::new);

        }
        @SubscribeEvent
        public static void EntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EntityTs.flysword.get(), SwordRenderer::new);
            event.registerEntityRenderer(EntityTs.suddenrain.get(), SwordRenderer::new);
            event.registerEntityRenderer(EntityTs.cell_zombie.get(), ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.cell_giant.get(), CellZombieG::new);
            event.registerEntityRenderer(EntityTs.nightmare_entity.get(), ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.red_entity.get(), com.moonstone.moonstonemod.entity.client.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.nightmare_giant_to.get(), CellZombieN::new);
            event.registerEntityRenderer(EntityTs.test_e.get(), NigBoomRender::new);
            event.registerEntityRenderer(EntityTs.blood_bat.get(), BloodBatRenderer::new);
            event.registerEntityRenderer(EntityTs.test_blood.get(), com.moonstone.moonstonemod.entity.client.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.blood_zombie.get(), com.moonstone.moonstonemod.entity.client.red.ZombieRenderer::new);
            event.registerEntityRenderer(EntityTs.blood_zombie_fly.get(), BloodSwordRenderer::new);
            event.registerEntityRenderer(EntityTs.blood_zombie_boom.get(), com.moonstone.moonstonemod.entity.client.red.ZombieRenderer::new);

            event.registerEntityRenderer(EntityTs.line.get(), com.moonstone.moonstonemod.entity.client.LineRenderer::new);
            event.registerEntityRenderer(EntityTs.snake.get(), com.moonstone.moonstonemod.entity.client.SnakeRenderer::new);
            event.registerEntityRenderer(EntityTs.attack_blood.get(), com.moonstone.moonstonemod.entity.client.AttackBloodRender::new);
            event.registerEntityRenderer(EntityTs.blood.get(), com.moonstone.moonstonemod.entity.client.BloodRender::new);
            event.registerEntityRenderer(EntityTs.owner_blood.get(), com.moonstone.moonstonemod.entity.client.OwnerBloodRenderer::new);
            event.registerEntityRenderer(EntityTs.blood_orb_attack.get(), com.moonstone.moonstonemod.entity.client.blood.BloodOrbAttack::new);
            event.registerEntityRenderer(EntityTs.blood_orb_owner.get(), com.moonstone.moonstonemod.entity.client.blood.BloodOrbOwner::new);
            event.registerEntityRenderer(EntityTs.blood_orb_small.get(), com.moonstone.moonstonemod.entity.client.blood.BloodOrbSmall::new);
            event.registerEntityRenderer(EntityTs.sun.get(), com.moonstone.moonstonemod.entity.client.SunRenderer::new);
            event.registerEntityRenderer(EntityTs.sword_soul.get(), com.moonstone.moonstonemod.entity.client.SwordSoulRenderer::new);
            event.registerEntityRenderer(EntityTs.rage_blood.get(), com.moonstone.moonstonemod.entity.client.RageBloodRender::new);
            event.registerEntityRenderer(EntityTs.as_sword.get(), com.moonstone.moonstonemod.entity.client.AsSwordRender::new);
            event.registerEntityRenderer(EntityTs.axe.get(), com.moonstone.moonstonemod.entity.client.AxeRenderer::new);
            event.registerEntityRenderer(EntityTs.bolt.get(), com.moonstone.moonstonemod.entity.client.BoltRenderer::new);
            event.registerEntityRenderer(EntityTs.sword.get(), com.moonstone.moonstonemod.entity.client.SwordOfTwelveRenderer::new);
            event.registerEntityRenderer(EntityTs.at_sword_entity.get(), com.moonstone.moonstonemod.entity.client.AtSwordRender::new);

            event.registerEntityRenderer(EntityTs.ytgld.get(), YtgldRender::new);
            event.registerEntityRenderer(EntityTs.coffin_entity.get(), CoffinRender::new);
            event.registerEntityRenderer(EntityTs.the_sword.get(), TheSwordRender::new);
        }
        @SubscribeEvent
        public static void EntityRenderersEvent(RegisterShadersEvent event) {
            try {


                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"rendertype_gateway"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_gateway);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"rendertype_mls"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_mls);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID, "rendertype_ging"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_ging);



                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"trail"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_trail);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"eye"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_EYE);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"snake"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShader_snake);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"p_blood"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_p_blood);
                
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

}
