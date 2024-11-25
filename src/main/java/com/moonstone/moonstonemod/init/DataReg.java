package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.contents.BundleContents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class DataReg {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, MoonStoneMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> tag =
            REGISTRY.register("tag",()-> DataComponentType.<CompoundTag>builder().persistent(CompoundTag.CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BundleContents>> BUNDLE_CONTENTS =
            REGISTRY.register("bundle",()-> DataComponentType.<BundleContents>builder().persistent(BundleContents.CODEC).build());




}


