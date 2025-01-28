package com.moonstone.moonstonemod.mixin.client;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.nightmare_base_black_eye;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Shadow @Final private Camera mainCamera;

    @Shadow @Nullable private PostChain blurEffect;

    @Inject(at = @At("RETURN"), method = "render")
    public void init(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) {
       if (mainCamera.getEntity() instanceof Player player) {
           if (Handler.hascurio(player, Items.nightmare_base_black_eye.get())) {
               CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                   Map<String, ICurioStacksHandler> curios = handler.getCurios();
                   for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                       ICurioStacksHandler stacksHandler = entry.getValue();
                       IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                       for (int i = 0; i < stacksHandler.getSlots(); i++) {
                           ItemStack stack = stackHandler.getStackInSlot(i);
                           if (stack.get(DataReg.tag)!=null){
                               float fs =  stack.get(DataReg.tag).getInt(nightmare_base_black_eye.NightmareBaseBlackEye);
                               if (fs<=10){
                                   return;
                               }
                               fs/=100;
                               if (this.blurEffect != null) {
                                   this.blurEffect.setUniform("Radius", fs);
                                   this.blurEffect.process(deltaTracker.getGameTimeDeltaTicks());
                               }
                           }
                       }
                   }
               });

           }
       }
    }
}
