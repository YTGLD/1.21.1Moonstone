package com.moonstone.moonstonemod.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class nightmare_base_black_eye_red  extends nightmare implements SuperNightmare {
    public static final String aty  = "NightmareRed";

      public static void kill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player ){
            if (Handler.hascurio(player, Items.nightmare_base_black_eye_red.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is( Items.nightmare_base_black_eye_red.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (stack.get(DataReg.tag).getInt(aty) < 50) {
                                        stack.get(DataReg.tag).putInt(aty, stack.get(DataReg.tag).getInt(aty) + 5);
                                    }
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
        if (Handler.hascurio(slotContext.entity(),this)) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.setDamageValue(stack.getDamageValue()+1);
        if (stack.get(DataReg.tag) == null){
            stack.set(DataReg.tag,new CompoundTag());
        } else {
            if (slotContext.entity().tickCount % 20 ==1) {
                if (stack.get(DataReg.tag).getInt(aty)>0) {
                    stack.get(DataReg.tag).putInt(aty, stack.get(DataReg.tag).getInt(aty) - 1);
                }
            }
        }
    }
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers( ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();
        if (stack.get(DataReg.tag)!=null) {
            double as = stack.get(DataReg.tag).getInt(aty) / 100f;

            for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()) {

                get.put(attribute, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            }
        }
        return get;
    }
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye_red.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));
    }
}
