package com.moonstone.moonstonemod.init;

import com.mojang.blaze3d.shaders.Effect;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.effects.blood;
import com.moonstone.moonstonemod.effects.fear;
import com.moonstone.moonstonemod.effects.pain;
import com.moonstone.moonstonemod.effects.rage;
import com.moonstone.moonstonemod.item.ectoplasm.ectoplasmapple;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Effects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MoonStoneMod.MODID);
    public static final DeferredHolder<MobEffect,?> rage  =REGISTRY.register("rage",()->new rage());
    public static final DeferredHolder<MobEffect,?> blood  =REGISTRY.register("blood",()->new blood());
    public static final DeferredHolder<MobEffect,?> fear  =REGISTRY.register("fear",()->new fear());
    public static final DeferredHolder<MobEffect,?> pain  =REGISTRY.register("pain",()->new pain());

}
