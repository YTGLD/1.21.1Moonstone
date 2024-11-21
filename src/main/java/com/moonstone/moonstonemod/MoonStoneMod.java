package com.moonstone.moonstonemod;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import com.moonstone.moonstonemod.client.particle.blood;
import com.moonstone.moonstonemod.client.particle.blue;
import com.moonstone.moonstonemod.client.particle.popr;
import com.moonstone.moonstonemod.client.particle.red;
import com.moonstone.moonstonemod.client.renderer.MRender;
import com.moonstone.moonstonemod.client.renderer.MoonPost;
import com.moonstone.moonstonemod.entity.client.BloodSwordRenderer;
import com.moonstone.moonstonemod.entity.client.SwordRenderer;
import com.moonstone.moonstonemod.entity.client.blood.BloodBatRenderer;
import com.moonstone.moonstonemod.entity.client.zombie.CellZombieG;
import com.moonstone.moonstonemod.entity.client.zombie.CellZombieN;
import com.moonstone.moonstonemod.entity.client.zombie.ZombieRenderer;
import com.moonstone.moonstonemod.event.*;
import com.moonstone.moonstonemod.init.*;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.io.IOException;

@Mod(MoonStoneMod.MODID)
public class MoonStoneMod {

    public static final String MODID = "moonstone";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceLocation POST = ResourceLocation.fromNamespaceAndPath(MoonStoneMod.MODID,
            "shaders/post/entity_outline.json");



    public MoonStoneMod(IEventBus eventBus, ModContainer modContainer){


        NeoForge.EVENT_BUS.register(new AllEvent());
        NeoForge.EVENT_BUS.register(new LootEvent());
        NeoForge.EVENT_BUS.register(new Village());
        NeoForge.EVENT_BUS.register(new LootTableEvent());
        NeoForge.EVENT_BUS.register(new NewEvent());
        AttReg.REGISTRY.register(eventBus);
        Blocks.REGISTRY.register(eventBus);
        DNAItems.REGISTRY.register(eventBus);
        LootReg.REGISTRY.register(eventBus);
        EntityTs.REGISTRY.register(eventBus);
        DataReg.REGISTRY.register(eventBus);
        Effects.REGISTRY.register(eventBus);
        Particles.PARTICLE_TYPES.register(eventBus);
        Items.REGISTRY.register(eventBus);
        Tab.TABS.register(eventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.fc);
    }

    @EventBusSubscriber(modid = MoonStoneMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class Client {
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
            event.registerEntityRenderer(EntityTs.nightmare_giant.get(), CellZombieN::new);
            event.registerEntityRenderer(EntityTs.test_e.get(), com.moonstone.moonstonemod.entity.client.red.ZombieRenderer::new);
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
                        ResourceLocation.fromNamespaceAndPath(MODID,"rendertype_outline"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_outline);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

}
