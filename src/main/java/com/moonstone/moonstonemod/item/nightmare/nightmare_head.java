package com.moonstone.moonstonemod.item.nightmare;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class nightmare_head extends nightmare {
    public static final String die = "NigDie";
    public int size = 0;
    public static void LivingDeathEvent(LivingDeathEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_head.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmareeye.get())) {
                                int sss = player.deathTime;
                                if (sss < 1) {
                                   CompoundTag tag = stack.get(DataReg.tag);
                                    if (tag != null){
                                        if (tag.getInt(die) < 100) {
                                            tag.putInt(die, tag.getInt(die) + 1);
                                        }
                                    }else {
                                        stack.set(DataReg.tag,new CompoundTag());
                                    }
                                }
                                if (stack.is(Items.nightmare_head.get())) {
                                    int ss = player.deathTime;
                                    if (ss < 1) {

                                       CompoundTag tag = stack.get(DataReg.tag);
                                        if (tag != null){
                                            if (tag.getInt(die) < 100) {
                                                tag.putInt(die, tag.getInt(die) + 1);
                                            }
                                        }else {
                                            stack.set(DataReg.tag,new CompoundTag());
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void headHurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player,Items.nightmare_head.get())) {
                if (event.getSource() != null ){
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        living.hurt(living.damageSources().magic(),event.getAmount() * 5);
                    }
                }

                if (event.getSource() != null && !event.getSource().is(DamageTypes.MOB_ATTACK)
                        || !event.getSource().is(DamageTypes.MOB_PROJECTILE)) {
                    event.setAmount(0);
                }
                if (event.getSource() != null && event.getSource().is(DamageTypes.MOB_ATTACK)
                        || event.getSource().is(DamageTypes.MOB_PROJECTILE)
                        || event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
                    player.setHealth(player.getHealth()-1);
                }
            }
        }
    }
    public static void LivingHealEvent(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_head.get())){
                event.setAmount(0);
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide
                &&slotContext.entity().tickCount % 100 == 0) {
           CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null){
                tag.putString("TestTag", "TestTag");
                slotContext.entity().setHealth(slotContext.entity().getHealth() + 1);
            }else {
                stack.set(DataReg.tag,new CompoundTag());
            }
        }
    }


    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> tooltip, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, tooltip, pTooltipFlag);
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.8").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.6").withStyle(ChatFormatting.DARK_RED));
        CompoundTag tag = pStack.get(DataReg.tag);
        if (tag != null){
            tooltip.add(Component.literal("Now: " + -tag.getInt(die) + "%").withStyle(ChatFormatting.RED));
        }
        tooltip.add(Component.translatable(""));
        tooltip.add(Component.translatable("item.nightmare_head.tool.string.7").withStyle(ChatFormatting.DARK_RED));
        tooltip.add(Component.translatable(""));
    }


}
