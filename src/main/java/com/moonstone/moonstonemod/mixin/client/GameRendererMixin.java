package com.moonstone.moonstonemod.mixin.client;
//
//import com.moonstone.moonstonemod.Handler;
//import com.moonstone.moonstonemod.init.items.Items;
//import net.minecraft.client.Camera;
//import net.minecraft.client.DeltaTracker;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.client.renderer.PostChain;
//import net.minecraft.world.entity.player.Player;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import javax.annotation.Nullable;
//
//@Mixin(GameRenderer.class)
//public abstract class GameRendererMixin {
//
//
//    @Shadow @Final private Camera mainCamera;
//
//    @Shadow @Nullable private PostChain blurEffect;
//
//    @Inject(at = @At("RETURN"), method = "render")
//    public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
//       if (mainCamera.getEntity() instanceof Player player){
//           if (Handler.hascurio(player, Items.nightmare_base.get())){
//               float f = 1;
//               if (this.blurEffect != null) {
//                   this.blurEffect.setUniform("Radius", f);
//                   this.blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
//               }
//           }
//       }
//    }
//}
