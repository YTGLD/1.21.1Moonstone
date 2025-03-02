package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class nightmare_base_black_eye extends nightmare {
    public static final String NightmareBaseBlackEye = "NightmareBaseBlackEye";
    
      @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.isCreative()){
                return true;
            }
        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().tickCount%2==1&&!slotContext.entity().level().isClientSide) {
            stack.setDamageValue(stack.getDamageValue()+1);
            if (stack.get(DataReg.tag) != null) {
                if (stack.get(DataReg.tag).getInt(NightmareBaseBlackEye) > 0) {
                    stack.get(DataReg.tag).putInt(NightmareBaseBlackEye, stack.get(DataReg.tag).getInt(NightmareBaseBlackEye) - 1);
                }
            }
        }
    }
    public static void damage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player&&!player.level().isClientSide) {
            if (Handler.hascurio(player, Items.nightmare_base_black_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.get(DataReg.tag)!=null){
                                if (stack.get(DataReg.tag).getInt(NightmareBaseBlackEye) < 175) {
                                    stack.get(DataReg.tag).putInt(NightmareBaseBlackEye, stack.get(DataReg.tag).getInt(NightmareBaseBlackEye) + 10);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    //白天会视力模糊
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string").withStyle(ChatFormatting.DARK_RED));
        if (stack.get(DataReg.tag) != null) {
            pTooltipComponents.add(Component.literal(String.valueOf(stack.get(DataReg.tag).getInt(NightmareBaseBlackEye))).withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_black_eye_eye").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_black_eye_red").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_black_eye_heart").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
}
