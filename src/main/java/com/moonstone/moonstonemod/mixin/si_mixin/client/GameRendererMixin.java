package com.moonstone.moonstonemod.mixin.si_mixin.client;

import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Shadow @Final private Camera mainCamera;

    @Shadow @Nullable private PostChain blurEffect;

    @Shadow public abstract void tick();


    @Shadow @Final
    Minecraft minecraft;
   @Inject(at = @At("RETURN"), method = "render")
   public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
       if (mainCamera.getEntity() instanceof Player player)   {
           if (Config.SERVER.nightmare_base_black_eye.get()){
               if (Handler.hascurio(player, Items.muddy_jewels.get())){
                   return;
               }
               if (Handler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                   float fs = player.getPersistentData().getFloat("blurEffectOFNightmare_base_black_eye");
                   if (moonstone1_21_1$getPlayerLookTarget(player.level(), player) != null && moonstone1_21_1$getPlayerLookTarget(player.level(), player) instanceof LivingEntity) {
                       if (fs < 5) {
                           player.getPersistentData().putFloat("blurEffectOFNightmare_base_black_eye", fs + 0.1f);
                       }
                   } else {
                       if (fs > 0) {
                           player.getPersistentData().putFloat("blurEffectOFNightmare_base_black_eye", fs - 0.05f);
                       }
                   }
                   if (fs > 0) {
                       if (this.blurEffect != null) {
                           this.blurEffect.setUniform("Radius", player.getPersistentData().getFloat("blurEffectOFNightmare_base_black_eye"));
                           this.blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
                       }
                   }
               }
           }
       }
   }
    @Unique
    public Entity moonstone1_21_1$getPlayerLookTarget(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
}
