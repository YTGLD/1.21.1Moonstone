package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = SeekingImmortalsMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AttReg {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, SeekingImmortalsMod.MODID);
    public static final DeferredHolder<Attribute,?> alL_attack = REGISTRY.register("allattack",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.allattack", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> heal = REGISTRY.register("heal",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.heal", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> cit = REGISTRY.register("cit",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.cit", 1, -1024, 1024).setSyncable(true);
    });

    public static final DeferredHolder<Attribute,?> effectNumber = REGISTRY.register("effect_number",()->{
        return new RangedAttribute("attribute.name.seeking_immortals.effect_number", 1, -1024, 1024).setSyncable(true);
    });
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeModificationEvent event){
        event.add(EntityType.PLAYER , AttReg.alL_attack,1);
        event.add(EntityType.PLAYER , AttReg.heal,1);
        event.add(EntityType.PLAYER , AttReg.cit,1);
        event.add(EntityType.PLAYER , AttReg.effectNumber,1);

    }
}
