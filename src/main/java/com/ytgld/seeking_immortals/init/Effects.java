package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.effect.*;
import com.ytgld.seeking_immortals.effect.element_effect.the_yoke_of_war;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Effects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SeekingImmortalsMod.MODID);
    public static final DeferredHolder<MobEffect,?> dead  =REGISTRY.register("dead",()->new dead());
    public static final DeferredHolder<MobEffect,?> erode  =REGISTRY.register("erode",()->new erode());
    public static final DeferredHolder<MobEffect,?> hidden  =REGISTRY.register("hidden",()->new hidden());
    public static final DeferredHolder<MobEffect,?> blade  =REGISTRY.register("blade",()->new blade());
    public static final DeferredHolder<MobEffect,?> the_yoke_of_war  =REGISTRY.register("the_yoke_of_war",()->new the_yoke_of_war());
    public static final DeferredHolder<MobEffect,?> invulnerable  =REGISTRY.register("invulnerable",()->new invulnerable());
    public static final DeferredHolder<MobEffect,?> bone_god  =REGISTRY.register("bone_god",()->new bone_god());

}
