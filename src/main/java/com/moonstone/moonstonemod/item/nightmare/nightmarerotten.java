package com.moonstone.moonstonemod.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmarerotten extends nightmare {

    public static final String nightmarerotten = "NightmareRotten";


    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        Multimap<Holder<Attribute>, AttributeModifier> get = HashMultimap.create();
        double as = 0.33;

        for (Holder<Attribute> attribute : BuiltInRegistries.ATTRIBUTE.asHolderIdMap()){

            get.put(attribute, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), as, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return get;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            int s = BuiltInRegistries.ATTRIBUTE.size();
           CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null){
                tag.putInt(nightmarerotten, s);
                player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers());
            }else {
                stack.set(DataReg.tag,new CompoundTag());
            }
        }
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers());
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.nightmarerotten.tool.string").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.nightmarerotten.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.nightmarerotten.tool.string.2").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable(""));
        CompoundTag tag = pStack.get(DataReg.tag);
        if (tag != null){
            pTooltipComponents.add(Component.translatable("·now：" + ((float) (tag.getInt(nightmarerotten) * 1.1)) + "%").withStyle(ChatFormatting.RED));

        }
        pTooltipComponents.add(Component.translatable(""));

        pTooltipComponents.add(Component.translatable("item.nightmarerotten.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmarerotten.tool.string.4").withStyle(ChatFormatting.DARK_RED));

    }
}
