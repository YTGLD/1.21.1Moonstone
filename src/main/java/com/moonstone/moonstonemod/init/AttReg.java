package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, MoonStoneMod.MODID);
    public static final DeferredHolder<Attribute,?> alL_attack = REGISTRY.register("allattack",()->{
        return new RangedAttribute("attribute.name.moonstone.allattack", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> heal = REGISTRY.register("heal",()->{
        return new RangedAttribute("attribute.name.moonstone.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> cit = REGISTRY.register("cit",()->{
        return new RangedAttribute("attribute.name.moonstone.cit", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> dig = REGISTRY.register("dig",()->{
        return new RangedAttribute("attribute.name.moonstone.dig", 1, -1024, 1024).setSyncable(true);
    });
}
