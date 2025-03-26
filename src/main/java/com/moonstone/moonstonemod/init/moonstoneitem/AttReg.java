package com.moonstone.moonstonemod.init.moonstoneitem;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
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
    public static final DeferredHolder<Attribute,?> hurt = REGISTRY.register("hurt",()->{
        return new RangedAttribute("attribute.name.moonstone.res", 1, -1024, 1024).setSyncable(true);
    });

    public static final DeferredHolder<Attribute,?> owner_blood_damage = REGISTRY.register("owner_blood_damage",()->{
        return new RangedAttribute("attribute.name.moonstone.owner_blood_damage", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> owner_blood_speed = REGISTRY.register("owner_blood_speed",()->{
        return new RangedAttribute("attribute.name.moonstone.owner_blood_speed", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> owner_blood_time = REGISTRY.register("owner_blood_time",()->{
        return new RangedAttribute("attribute.name.moonstone.owner_blood_time", 1, -1024, 1024).setSyncable(true);
    });
    public static final DeferredHolder<Attribute,?> owner_blood_attack_speed = REGISTRY.register("owner_blood_attack_speed",()->{
        return new RangedAttribute("attribute.name.moonstone.owner_blood_attack_speed", 1, -1024, 1024).setSyncable(true);
    });


    public static final DeferredHolder<Attribute,?> zombie_attack_damage = REGISTRY.register("zombie_attack_damage",()->{
        return new RangedAttribute("attribute.name.moonstone.zombie_attack_damage", 1, -1024, 1024).setSyncable(true);
    });
}
