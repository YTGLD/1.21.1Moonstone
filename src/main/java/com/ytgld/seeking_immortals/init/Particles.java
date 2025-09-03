package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES;
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> blood;
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> cube;

    static {
        PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SeekingImmortalsMod.MODID);

        blood = PARTICLE_TYPES.register("blood", ()->{
            return new SimpleParticleType(false);
        });
        cube = PARTICLE_TYPES.register("cube", ()->{
            return new SimpleParticleType(false);
        });
    }
}
