package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.AdvancementEvt;
import com.moonstone.moonstonemod.event.AllEvent;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class nightmare_base_insight extends nightmare implements SuperNightmare {
     @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (Handler.hascurio(player,Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return com.moonstone.moonstonemod.Config.SERVER.canUnequipMoonstoneItem.get();
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CuriosApi.getCuriosInventory(slotContext.entity()).ifPresent(handler -> {

            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack box = stackHandler.getStackInSlot(i);
                    if (box.is(Items.medicinebox.get())) {
                        CompoundTag tag = box.get(DataReg.tag);
                        if (tag != null) {
                            if (tag.getBoolean(AllEvent.blood_eat) &&
                                    tag.getBoolean(AllEvent.blood_hurt) &&
                                    tag.getBoolean(AllEvent.blood_jump) &&
                                    tag.getBoolean(AllEvent.blood_spawn) &&
                                    tag.getBoolean(AllEvent.blood_enchant))
                            {
                                if (stack.get(DataReg.tag)!=null&& !stack.get(DataReg.tag).getBoolean(AdvancementEvt.nightmare_base_insight_drug)){
                                    if (slotContext.entity() instanceof Player player){
                                        player.addItem(new ItemStack(Items.nightmare_base_insight_drug.get()));
                                        stack.get(DataReg.tag).putBoolean(AdvancementEvt.nightmare_base_insight_drug,true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_collapse").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_insane").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_insight_drug").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
}
