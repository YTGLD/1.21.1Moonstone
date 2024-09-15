package com.moonstone.moonstonemod.item.blood;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import com.moonstone.moonstonemod.moonstoneitem.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class max_eye extends Item implements ICurioItem, Blood {
    public static final String blood="MaxBlood";
    public max_eye() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void Die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_eye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag) != null) {
                                if (stack.get(DataReg.tag).getFloat(blood)<0.5){
                                    stack.get(DataReg.tag).putFloat(blood,stack.get(DataReg.tag).getFloat(blood)+0.1f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void A(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.max_eye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag) != null) {
                                if (stack.get(DataReg.tag).getFloat(blood)>0){
                                    stack.get(DataReg.tag).putFloat(blood,stack.get(DataReg.tag).getFloat(blood)-0.1f);
                                    event.setAmount(event.getAmount()*0.1f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.get(DataReg.tag) != null){
            if (slotContext.entity() instanceof Player player){
                if (stack.get(DataReg.tag).getFloat(blood)>0) {
                    stack.get(DataReg.tag).putFloat(blood, stack.get(DataReg.tag).getFloat(blood) - 0.001f);
                    player.heal(player.getMaxHealth() / 200);
                }
            }
        }
    }
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.3").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.max_eye.tool.string.4").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
