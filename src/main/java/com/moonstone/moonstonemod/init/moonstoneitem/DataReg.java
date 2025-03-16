package com.moonstone.moonstonemod.init.moonstoneitem;

import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.contents.BundleContents;
import com.moonstone.moonstonemod.contents.BundleContentsDNA;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataReg {
    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, MoonStoneMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompoundTag>> tag =
            REGISTRY.register("tag",()-> DataComponentType.<CompoundTag>builder().persistent(CompoundTag.CODEC).build());
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BundleContents>> BUNDLE_CONTENTS =
            REGISTRY.register("bundle",()-> DataComponentType.<BundleContents>builder().persistent(BundleContents.CODEC).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BundleContentsDNA>> SUPER_BAG =
            REGISTRY.register("dna",()-> DataComponentType.<BundleContentsDNA>builder().persistent(BundleContentsDNA.CODEC).build());


}


