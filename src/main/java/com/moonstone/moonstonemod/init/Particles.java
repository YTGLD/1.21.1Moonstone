package com.moonstone.moonstonemod.init;

import com.moonstone.moonstonemod.MoonStoneMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES;

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> gold;
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> blue;
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> popr;
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> blood;

    static {
        PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MoonStoneMod.MODID);

        gold = PARTICLE_TYPES.register("red", ()->{
            return new SimpleParticleType(false);
        });
        blue = PARTICLE_TYPES.register("blue", ()->{
            return new SimpleParticleType(false);
        });
        popr = PARTICLE_TYPES.register("popr", ()->{
            return new SimpleParticleType(false);
        });
        blood = PARTICLE_TYPES.register("blood", ()->{
            return new SimpleParticleType(false);
        });
    }
}
