package com.moonstone.moonstonemod.mixin.client;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.ytgld;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_black_eye;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin  {


    @Shadow @Final private Camera mainCamera;

    @Shadow @Nullable private PostChain blurEffect;

    @Shadow public abstract void tick();

    @Inject(at = @At("RETURN"), method = "render")
    public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
       if (mainCamera.getEntity() instanceof Player player) {
          if (!Handler.hascurio(player,Items.immortal.get())){
               float fs = player.getPersistentData().getFloat("blurEffect");
               if (moonstone1_21_1$getPlayerLookTarget(player.level(), player) != null && moonstone1_21_1$getPlayerLookTarget(player.level(), player).getType() ==EntityTs.ytgld.get()) {

                   if (fs<20){
                       player.getPersistentData().putFloat("blurEffect", fs + 0.1f);
                   }


               } else {
                   if (fs>0){
                       player.getPersistentData().putFloat("blurEffect", fs - 0.05f);
                   }
               }
               if (fs>0) {
                   if (this.blurEffect != null) {
                       this.blurEffect.setUniform("Radius", player.getPersistentData().getFloat("blurEffect"));
                       this.blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
                   }
               }
           }

           if (Handler.hascurio(player,Items.nightmare_base_black_eye.get())){
               float fs = player.getPersistentData().getFloat("blurEffectOFNightmare_base_black_eye");
               if (moonstone1_21_1$getPlayerLookTarget(player.level(), player) != null && moonstone1_21_1$getPlayerLookTarget(player.level(), player) instanceof LivingEntity) {

                   if (fs<5){
                       player.getPersistentData().putFloat("blurEffectOFNightmare_base_black_eye", fs + 0.1f);
                   }


               } else {
                   if (fs>0){
                       player.getPersistentData().putFloat("blurEffectOFNightmare_base_black_eye", fs - 0.05f);
                   }
               }
               if (fs>0) {
                   if (this.blurEffect != null) {
                       this.blurEffect.setUniform("Radius", player.getPersistentData().getFloat("blurEffectOFNightmare_base_black_eye"));
                       this.blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
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
