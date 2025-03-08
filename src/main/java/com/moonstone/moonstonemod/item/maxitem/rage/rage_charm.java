package com.moonstone.moonstonemod.item.maxitem.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class rage_charm  extends Item  implements ICurioItem,RAGE {
    public static final String atr = "RageAtt";


    public rage_charm() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.rage_charm.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.rage_charm.get())){
                                if (stack.get(DataReg.tag) != null){
                                    if (stack.get(DataReg.tag).getFloat(atr)<100) {
                                        stack.get(DataReg.tag).putFloat(atr, stack.get(DataReg.tag).getFloat(atr) + 4);
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
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().tickCount % 10 == 0) {
            if (stack.get(DataReg.tag) != null) {
                if (stack.get(DataReg.tag).getFloat(atr) > 0) {
                    stack.get(DataReg.tag).putFloat(atr, stack.get(DataReg.tag).getFloat(atr) - 0.25f);
                }
            } else {
                stack.set(DataReg.tag, new CompoundTag());
            }
        }
        if (!slotContext.entity().level().isClientSide) {
            slotContext.entity().getAttributes().addTransientAttributeModifiers(g(stack));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(g(stack));

    }




    public Multimap<Holder<Attribute>, AttributeModifier> g(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifierMultimap =HashMultimap.create();
        if (stack.get(DataReg.tag) != null) {
            float r = stack.get(DataReg.tag).getFloat(atr);//r=1~100
            r/=100;//==1%~100^

            attributeModifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("ectoplasmstar"),
                    r/2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            attributeModifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("ectoplasmstar"),
                    r/4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            attributeModifierMultimap.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("ectoplasmstar"),
                    r/3.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

           attributeModifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("ectoplasmstar"),
                    r/2.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            attributeModifierMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("ectoplasmstar"),
                    r, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }

        return attributeModifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);


        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.rage_charm.tool.string").withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(Component.translatable("item.rage_charm.tool.string.1").withStyle(ChatFormatting.GOLD));
        } else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.GOLD));
            if (pStack.get(DataReg.tag) != null) {
                float r = pStack.get(DataReg.tag).getFloat(atr);//r=1~100
                pTooltipComponents.add(Component.translatable("attribute.name.generic.attack_damage").append("：").append(String.valueOf(r/2)).append("%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.attack_speed").append("：").append(String.valueOf(r/4)).append("%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.armor").append("：").append(String.valueOf(r/3.5)).append("%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("attribute.name.moonstone.heal").append("：").append(String.valueOf(r/2.5)).append("%").withStyle(ChatFormatting.YELLOW));
                pTooltipComponents.add(Component.translatable("attribute.name.generic.knockback_resistance").append("：").append(String.valueOf(r)).append("%").withStyle(ChatFormatting.YELLOW));
            }
        }
    }
}

