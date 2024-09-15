package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.flysword;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = MoonStoneMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MoonStoneMod.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.flysword>> flysword = REGISTRY.register("flysword",
            ()-> EntityType.Builder.of(flysword::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("flysword"));

    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.suddenrain>> suddenrain = REGISTRY.register("suddenrain",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.suddenrain::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("suddenrain"));

    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.zombie.cell_zombie>> cell_zombie = REGISTRY.register("cell_zombie",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.cell_zombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("cell_zombie"));


    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.zombie.cell_giant>> cell_giant = REGISTRY.register("cell_giant",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.cell_giant::new, MobCategory.MONSTER).sized(0.9F, 2.9F).clientTrackingRange(16).build("cell_giant"));


    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.nightmare.nightmare_entity>> nightmare_entity = REGISTRY.register("nightmare_entity",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.nightmare.nightmare_entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("nightmare_entity"));
    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.zombie.test_e>> test_e = REGISTRY.register("test_e",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.test_e::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("test_e"));

    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.zombie.red_entity>> red_entity = REGISTRY.register("red_entity",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.red_entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(16).build("red_entity"));


    public static final DeferredHolder<EntityType<?>, EntityType<com.moonstone.moonstonemod.entity.nightmare.nightmare_giant>> nightmare_giant = REGISTRY.register("nightmare_giant",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.nightmare.nightmare_giant::new, MobCategory.MONSTER).sized(0.9F, 2.9F).clientTrackingRange(16).build("nightmare_giant"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bloodvruis.test_blood>> test_blood = REGISTRY.register("test_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.test_blood::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("test_blood"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bloodvruis.blood_bat>> blood_bat = REGISTRY.register("blood_bat",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.blood_bat::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(16).build("blood_bat"));

    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.zombie.blood_zombie>> blood_zombie = REGISTRY.register("blood_zombie",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.blood_zombie::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_zombie"));

    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.zombie.blood_zombie_fly>> blood_zombie_fly = REGISTRY.register("blood_zombie_fly",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.blood_zombie_fly::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_zombie_fly"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.zombie.blood_zombie_boom>> blood_zombie_boom = REGISTRY.register("blood_zombie_boom",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.blood_zombie_boom::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("blood_zombie_boom"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.line>> line = REGISTRY.register("line",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.line::new, MobCategory.MONSTER).sized(1, 1).clientTrackingRange(16).build("line"));


    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(EntityTs.cell_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.cell_giant.get(), com.moonstone.moonstonemod.entity.zombie.cell_giant.createAttributes().build());
        event.put(EntityTs.nightmare_entity.get(), Bat.createAttributes().build());
        event.put(EntityTs.red_entity.get(), Zombie.createAttributes().build());
        event.put(EntityTs.nightmare_giant.get(), Warden.createAttributes().build());
        event.put(EntityTs.test_e.get(), Warden.createAttributes().build());
        event.put(EntityTs.test_blood.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_bat.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_zombie_boom.get(), Zombie.createAttributes().build());
        event.put(EntityTs.line.get(), Zombie.createAttributes().build());

    }
}
