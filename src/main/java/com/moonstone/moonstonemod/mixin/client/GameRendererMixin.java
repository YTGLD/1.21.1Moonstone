package com.moonstone.moonstonemod.mixin.client;

import com.mojang.blaze3d.shaders.ProgramManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin  {


    @Shadow @Final private Camera mainCamera;

    @Shadow @Nullable private PostChain blurEffect;

    @Unique
    private PostChain light_PostChain;

    @Shadow public abstract void tick();


    @Shadow @Final
    Minecraft minecraft;
//
//    @Inject(at = @At("RETURN"), method = "close")
//    public void close(CallbackInfo ci){
//        if (this.light_PostChain != null) {
//            this.light_PostChain.close();
//        }
//
//    }
//
//    @Inject(at = @At("RETURN"), method = "loadBlurEffect")
//    private void loadBlurEffect(ResourceProvider resourceProvider, CallbackInfo ci) {
//        if (this.light_PostChain != null) {
//            this.light_PostChain.close();
//        }
//        try {
//            this.light_PostChain =
//                    new PostChain(this.minecraft.getTextureManager(), resourceProvider,
//                            this.minecraft.getMainRenderTarget(), MoonStoneMod.light);
//            this.light_PostChain.resize(this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
//        } catch (IOException ioException) {
//            MoonStoneMod.LOGGER.error(String.valueOf(ioException));
//        }
//
//    }
//    @Inject(at = @At("RETURN"), method = "resize")
//    public void resize(int width, int height, CallbackInfo ci) {
//        if (this.light_PostChain != null) {
//            this.light_PostChain.resize(width, height);
//        }
//    }
//    @Unique
//    public void process_BlurEffect(float partialTick) {
//        if (this.light_PostChain != null) {
//
//            if (mainCamera.getEntity() instanceof Entity player){
//                this.light_PostChain.setUniform("lightPos_X", (float) player.getX());
//                this.light_PostChain.setUniform("lightPos_Y", (float) player.getY());
//                this.light_PostChain.setUniform("lightPos_Z", (float) player.getZ());
//            }
//
//            this.light_PostChain.process(partialTick);
//        }
//    }
    @Inject(at = @At("RETURN"), method = "render")
    public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
        if (mainCamera.getEntity() instanceof Player player) {
            if (player.isShiftKeyDown()){
//                process_BlurEffect(deltaTracker.getGameTimeDeltaTicks());

            }
        }


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
