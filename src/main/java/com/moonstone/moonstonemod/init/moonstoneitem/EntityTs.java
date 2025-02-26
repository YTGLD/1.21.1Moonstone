package com.moonstone.moonstonemod.init.moonstoneitem;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.entity.flysword;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.IronGolem;
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
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.line::new, MobCategory.MONSTER).sized(1, 0.2f).clientTrackingRange(16).build("line"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.snake>> snake = REGISTRY.register("snake",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.snake::new, MobCategory.MONSTER).sized(1, 0.2f).clientTrackingRange(16).build("snake"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.blood>> blood = REGISTRY.register("blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.blood::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(16).build("blood"));

    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.attack_blood>> attack_blood = REGISTRY.register("attack_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.attack_blood::new, MobCategory.MISC).sized(0.5f, 0.2f).clientTrackingRange(16).build("attack_blood"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.owner_blood>> owner_blood = REGISTRY.register("owner_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.owner_blood::new, MobCategory.MISC).sized(0.5f, 0.2f).clientTrackingRange(16).build("owner_blood"));
     public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_owner>> blood_orb_owner = REGISTRY.register("blood_orb_owner",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_owner::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_owner"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_attack>> blood_orb_attack = REGISTRY.register("blood_orb_attack",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_attack::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_attack"));

    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_small>> blood_orb_small = REGISTRY.register("blood_orb_small",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bloodvruis.blood_orb_small::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("blood_orb_small"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.sun>> sun = REGISTRY.register("sun",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.sun::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("sun"));

    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.zombie.sword_soul>> sword_soul = REGISTRY.register("sword_soul",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.zombie.sword_soul::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("sword_soul"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.rage_blood>> rage_blood = REGISTRY.register("rage_blood",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.rage_blood::new, MobCategory.MISC).sized(1, 1).clientTrackingRange(16).build("rage_blood"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.as_sword>> as_sword = REGISTRY.register("as_sword",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.as_sword::new, MobCategory.MISC).sized(1, 0.2f).clientTrackingRange(16).build("as_sword"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.axe>> axe = REGISTRY.register("axe",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.axe::new, MobCategory.MISC).sized(0.2f, 1).clientTrackingRange(50).build("axe"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.bolt>> bolt = REGISTRY.register("bolt",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.bolt::new, MobCategory.MISC).sized(0.2f, 1).clientTrackingRange(50).build("bolt"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.SwordOfTwelve>> sword = REGISTRY.register("sword",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.SwordOfTwelve::new, MobCategory.MISC).sized(0.2f, 1).clientTrackingRange(50).build("sword"));
    public static final DeferredHolder<EntityType<?>,EntityType<com.moonstone.moonstonemod.entity.AtSword>> at_sword_entity = REGISTRY.register("as_sword_entity",
            ()-> EntityType.Builder.of(com.moonstone.moonstonemod.entity.AtSword::new, MobCategory.MISC).sized(0.2f, 1).clientTrackingRange(50).build("as_sword_entity"));
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(EntityTs.cell_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.cell_giant.get(), com.moonstone.moonstonemod.entity.zombie.cell_giant.createAttributes().build());
        event.put(EntityTs.nightmare_entity.get(), Bat.createAttributes().build());
        event.put(EntityTs.red_entity.get(), Zombie.createAttributes().build());

        event.put(EntityTs.nightmare_giant.get(), com.moonstone.moonstonemod.entity.nightmare.nightmare_giant.createAttributes().build());

        event.put(EntityTs.test_blood.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_bat.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_zombie.get(), Zombie.createAttributes().build());
        event.put(EntityTs.blood_zombie_boom.get(), Zombie.createAttributes().build());
        event.put(EntityTs.line.get(), Zombie.createAttributes().build());
        event.put(EntityTs.snake.get(), Bat.createAttributes().build());
        event.put(EntityTs.owner_blood.get(), IronGolem.createAttributes().build());
        event.put(EntityTs.blood_orb_owner.get(), IronGolem.createAttributes().build());
        event.put(EntityTs.sword_soul.get(), IronGolem.createAttributes().build());
        event.put(EntityTs.rage_blood.get(), IronGolem.createAttributes().build());

    }
}
