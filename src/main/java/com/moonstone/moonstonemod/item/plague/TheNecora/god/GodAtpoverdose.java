package com.moonstone.moonstonemod.item.plague.TheNecora.god;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.GodDNA;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

public class GodAtpoverdose extends GodDNA {
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "curio", id , 2, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    @Override
    public Item getNotEquippedItem() {
        return Items.atpoverdose.get();
    }
}
