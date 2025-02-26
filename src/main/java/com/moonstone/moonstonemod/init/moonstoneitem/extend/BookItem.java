package com.moonstone.moonstonemod.init.moonstoneitem.extend;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class BookItem extends Item implements ICurioItem, Iplague {
    public final  Holder<Attribute> attribute;
    public final float size;
    public final AttributeModifier.Operation operation ;

    public final String[] stringName;

    public BookItem(Properties properties,  Holder<Attribute> attribute, float size,AttributeModifier.Operation operation, String... stringName) {
        super(properties);
        this.operation = operation;
        this.attribute = attribute;
        this.size = size;
        this.stringName = stringName;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(this.attribute,
                new AttributeModifier(id,this.size,operation));
        return modifierMultimap;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);

        pTooltipComponents.add(Component.translatable("moonstone.use.dna").withStyle(ChatFormatting.RED));

        for (String string : stringName) {
            pTooltipComponents.add(Component.translatable(string).withStyle(ChatFormatting.RED));
        }
    }
}