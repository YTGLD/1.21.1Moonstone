package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.test_entity.lighting;
import com.ytgld.seeking_immortals.test_entity.orb_entity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityTs {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SeekingImmortalsMod.MODID);
    public static final DeferredHolder<EntityType<?>, EntityType<orb_entity>> orb_entity = REGISTRY.register("orb_entity",
            () -> EntityType.Builder.of(orb_entity::new, MobCategory.MISC).sized(0.1F, 0.1F).clientTrackingRange(50).build("orb_entity"));
    public static final DeferredHolder<EntityType<?>, EntityType<lighting>> lighting = REGISTRY.register("lighting",
            () -> EntityType.Builder.of(com.ytgld.seeking_immortals.test_entity.lighting::new, MobCategory.MISC).sized(0.1F, 0.1F).clientTrackingRange(50).build("lighting"));

}
