package com.moonstone.moonstonemod.mixin.other;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.item.man.health_dna;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin {
    @Shadow private int duration;


    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(LivingEntity entity, Runnable onExpirationRunnable, CallbackInfoReturnable<Boolean> cir){
        if (entity instanceof Player player) {

            if (Handler.hascurio(player, Items.health_dna.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.health_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.abnormal)) {
                                            if (duration==1){
                                                duration--;
                                                player.heal(6);
                                            }
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }

            if (Handler.hascurio(player, Items.copy_dna.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.copy_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.lymphadenopathy)) {
                                            duration -= 2;
                                            if (duration==2){
                                                duration -= 2;
                                                player.heal(player.getMaxHealth()*0.05f);
                                            }


                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }


            if (Handler.hascurio(player, Items.health_dna.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.health_dna.get())) {
                                ManBundleContents manBundleContents = stack.get(DataReg.man);
                                if (manBundleContents != null) {
                                    manBundleContents.items().forEach((itemStack -> {
                                        if (itemStack.is(Drugs.abnormal)) {
                                            duration -= 1;
                                        }
                                    }));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
