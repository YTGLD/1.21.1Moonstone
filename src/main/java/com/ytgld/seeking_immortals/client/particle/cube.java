package com.ytgld.seeking_immortals.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class cube extends TextureSheetParticle {
    public cube(ClientLevel level, double x, double y, double z, float movementX, float movementY, float movementZ) {
        super(level, x, y, z, 0, movementY/4f, 0);
        this.lifetime = 60;
        this.alpha = 1;

        this.rCol = Mth.nextFloat(RandomSource.create(),0.8f,1);
        this.gCol = Mth.nextFloat(RandomSource.create(),0,0.2f);
        this.bCol = Mth.nextFloat(RandomSource.create(),0.5f,1);

        this.roll = Mth.nextFloat(RandomSource.create(),-100,100);

        this.quadSize = Mth.nextFloat(RandomSource.create(),0.1f/4,0.3f/4);


        this.gravity = this.gravity / 10f;

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
        this.alpha -= 0.1f;
        this.quadSize *= 0.97f;

        super.tick();
    }
    @OnlyIn(Dist.CLIENT)
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }


        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            cube particle = new cube(level, x, y, z, (float) 0, (float) ySpeed,0);
            particle.pickSprite(this.sprite);
            return particle;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }
    }
}

