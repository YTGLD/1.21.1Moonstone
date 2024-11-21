package com.moonstone.moonstonemod.item.maxitem;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.CommonItem;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Die;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class moon_stone extends CommonItem implements Die {
    public int time(LivingEntity player){
        return 400;
    }
    public static final String timeName = "timeName";

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (stack.get(DataReg.tag) != null){
            stack.get(DataReg.tag).putInt(timeName,time(slotContext.entity()));

        }
    }

    public static void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player living) {
            if  (Handler.hascurio(living, Items.moon_stone.get())) {
                CuriosApi.getCuriosInventory(living).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag)!=null){
                                if (!living.getCooldowns().isOnCooldown(Items.moon_stone.get())) {
                                    LivingDeathEvent deathEvent = new LivingDeathEvent(event.getEntity(), event.getSource());
                                    NeoForge.EVENT_BUS.post(deathEvent);

                                    living.getCooldowns().addCooldown(Items.moon_stone.get(),stack.get(DataReg.tag).getInt(timeName));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.moon_stone.tool.string.1").withStyle(ChatFormatting.GOLD));
    }

}
