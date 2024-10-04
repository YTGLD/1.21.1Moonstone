package com.moonstone.moonstonemod.mixin;

import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.item.nanodoom.buyme.wind_and_rain;
import com.moonstone.moonstonemod.moonstoneitem.IBattery;
import com.moonstone.moonstonemod.moonstoneitem.INanoBattery;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(at = @At("HEAD"), method = "overrideOtherStackedOnMe")
    public void moonstone$clickMenuButton(ItemStack me,
                                          ItemStack Other,
                                          Slot slot,
                                          ClickAction clickAction,
                                          Player player,
                                          SlotAccess slotAccess,
                                          CallbackInfoReturnable<Boolean> cir) {
        if (me.getCount() == 1) {
            if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
                if (!Other.isEmpty()) {
                    if (Other.getItem() instanceof IBattery){
                        if (me.getItem() instanceof INanoBattery){
                            if (me.get(DataReg.tag) != null){
                                me.get(DataReg.tag).putBoolean(INanoBattery.nameTime,true);
                                Other.shrink(1);
                            }else {
                                me.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                    if (Other.getItem() instanceof wind_and_rain) {
                        if (me.getItem() instanceof SwordItem) {
                            CompoundTag tag   = me.get(DataReg.tag);
                            if (tag != null){
                                if (!tag.getBoolean(wind_and_rain.wind)) {
                                    tag.putBoolean(wind_and_rain.wind, true);
                                    Other.shrink(1);
                                }
                            }else {
                                me.set(DataReg.tag,new CompoundTag());
                            }

                        }
                    }
                }
            }
        }
    }
    @Inject(at = @At("HEAD"), method = "use")
    public void moonstone$clickMenuButton(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                if (tag.getBoolean(wind_and_rain.wind)) {
                    player.startUsingItem(hand);
                }
            }
        }
    }
}

