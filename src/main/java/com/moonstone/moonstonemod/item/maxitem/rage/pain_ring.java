package com.moonstone.moonstonemod.item.maxitem.rage;

import com.ytgld.seeking_immortals.Config;
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

public class pain_ring extends Item implements ICurioItem,RAGE{
    public static final String pain = "PainRing";

    public pain_ring() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static void Heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_ring.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_ring.get())) {
                                if (stack.get(DataReg.tag)!= null){
                                    if (stack.get(DataReg.tag).getFloat(pain) < (Config.SERVER.pain_ring.get()/100)*100) {
                                        stack.get(DataReg.tag).putFloat(pain,  (float) (stack.get(DataReg.tag).getFloat(pain) + Config.SERVER.pain_ring.get()/100));
                                    }
                                    event.setAmount((event.getAmount())*0.5f);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void Hurt(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.pain_ring.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.pain_ring.get())) {
                                if (stack.get(DataReg.tag)!= null){
                                    float dam = stack.get(DataReg.tag).getFloat(pain);
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
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable(""));
            tooltipComponents.add(Component.translatable("item.pain_ring.tool.string").withStyle(ChatFormatting.GOLD));
            tooltipComponents.add(Component.translatable("item.pain_ring.tool.string.1").withStyle(ChatFormatting.GOLD));
            tooltipComponents.add(Component.translatable(""));
            tooltipComponents.add(Component.translatable("item.pain_ring.tool.string.2").withStyle(ChatFormatting.GOLD));
            tooltipComponents.add(Component.translatable(""));
        }else {
            tooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.GOLD));
        }
        tooltipComponents.add(Component.translatable(""));
        if (stack.get(DataReg.tag)!=null) {
            tooltipComponents.add(Component.translatable("effect.minecraft.strength").append(": " + (stack.get(DataReg.tag).getFloat(pain)) + "%").withStyle(ChatFormatting.GOLD));
        }
    }

}
