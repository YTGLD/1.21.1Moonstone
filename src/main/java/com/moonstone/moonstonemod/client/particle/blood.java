package com.moonstone.moonstonemod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class blood extends TextureSheetParticle {
    public blood(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {


        super(level, x, y, z, movementX, movementY, movementZ);
        this.setParticleSpeed( movementX, movementY, movementZ);
        this.lifetime = 200;
        this.quadSize *= 0.4f;
        this.roll += Mth.nextFloat(RandomSource.create(), 0.2f, 1);
        this.oRoll += Mth.nextFloat(RandomSource.create(), 0.2f, 1);;
    }



    @Override
    protected int getLightColor(float p_107249_) {
        return 240;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public void tick() {
        this.alpha -= 0.025f;
        this.roll += 0.025f;
        this.oRoll += 0.025f;
        if (alpha<=0){
            this.remove();
        }
        super.tick();

    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            blood particle = new blood(level, x, y, z, 0, 0, 0);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}

