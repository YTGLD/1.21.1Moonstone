package com.moonstone.moonstonemod.item.maxitem.rage;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class pain_candle extends Item implements ICurioItem,RAGE {
    public static final String pain = "PainCandle";

    public pain_candle() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    /*
    污秽蜡烛：

	恢复血量时+1%受到伤害
	直到恢复至满血状态

	+1 生命恢复
	+33% 生命恢复


     */
    public static void Heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_candle.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_candle.get())) {
                                if (stack.get(DataReg.tag)!= null){
                                    stack.get(DataReg.tag).putInt(pain,stack.get(DataReg.tag).getInt(pain)+2);
                                    event.setAmount((event.getAmount()+1)*1.33f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void Hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_candle.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_candle.get())) {
                                if (stack.get(DataReg.tag)!= null){
                                    float dam = stack.get(DataReg.tag).getInt(pain);
                                    dam/=100;
                                    event.setAmount(event.getAmount()*(1+dam));
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
        if (slotContext.entity().getHealth()>=slotContext.entity().getMaxHealth()){
            stack.get(DataReg.tag).remove(pain);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable TooltipContext level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.pain_candle.tool.string").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.pain_candle.tool.string.1").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.pain_candle.tool.string.2").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.pain_candle.tool.string.3").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(""));
        }else {
            tooltip.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD));
        }
        if (stack.get(DataReg.tag)!=null) {
            tooltip.add(Component.translatable(""));
            tooltip.add(Component.translatable("item.pain_candle.tool.string.4").append((100 + stack.get(DataReg.tag).getInt(pain)) + "%").withStyle(ChatFormatting.GOLD));
        }

    }
}
