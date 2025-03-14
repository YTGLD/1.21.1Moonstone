package com.moonstone.moonstonemod.item.maxitem.rage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import net.minecraft.ChatFormatting;
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
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;

public class rage_bottle extends Item implements RAGE, ICurioItem{
    public rage_bottle() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }
    public static final String golden = "Gold";
    public static final int goldenMax = 5000;

    public static void expPickup(LivingExperienceDropEvent event){
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, Items.rage_bottle.get())){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.rage_bottle.get())) {
                            int exp = event.getDroppedExperience() / 2;
                            if (stack.get(DataReg.tag)!=null){
                                int size =  stack.get(DataReg.tag).getInt(golden);
                                if (size<goldenMax) {
                                    stack.get(DataReg.tag).putInt(golden, size + exp);
                                }
                            }
                            event.setDroppedExperience(exp);
                        }
                    }
                }
            });
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (player.tickCount%10==0) {
                if (Handler.hascurio(player, this)) {
                    if (stack.get(DataReg.tag) != null) {
                        int size = stack.get(DataReg.tag).getInt(golden);
                        if (size > 0) {
                            if (player.getFoodData().getFoodLevel() < 20) {
                                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 1);
                                player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() + 0.8f);

                                stack.get(DataReg.tag).putInt(golden, size - 1);
                            }
                        }
                        if (size < 0){
                            stack.get(DataReg.tag).putInt(golden, 0);
                        }
                        if (size > 5000){
                            stack.get(DataReg.tag).putInt(golden, 5000);
                        }
                    }
                }
            }
        }
        slotContext.entity().getAttributes().addTransientAttributeModifiers(getAttributeModifiers(stack));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(getAttributeModifiers(stack));

    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifierMultimap = HashMultimap.create();
        if (stack.get(DataReg.tag) != null) {
            float size = stack.get(DataReg.tag).getInt(golden);

            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                            size*0.04f/100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(AttReg.alL_attack, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    size*0.02f/100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

            modifierMultimap.put(AttReg.hurt, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    size*0.03f/100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(AttReg.heal, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage" + this.getDescriptionId()),
                    -size*0.01f/100f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }
        return modifierMultimap;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        CompoundTag tag = stack.get(DataReg.tag);
        if (tag != null){
            tag.putString("ytgld", "ytgld");
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.rage_bottle.tool.string").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.rage_bottle.tool.string.1").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.translatable("item.rage_bottle.tool.string.2").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.rage_bottle.tool.string.3").withStyle(ChatFormatting.GOLD));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.rage_bottle.tool.string.4").withStyle(ChatFormatting.GOLD));
        if (stack.get(DataReg.tag) != null) {
            tooltipComponents.add(Component.literal(""));
            float size = stack.get(DataReg.tag).getInt(golden);
            tooltipComponents.add(Component.translatable(String.valueOf(size)).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("attribute.name.moonstone.heal").append(-size*0.01f+"%").withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(Component.translatable("hurt.moonstone.string").append(size*0.03f+"%").withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("attribute.name.moonstone.allattack").append(size*0.02f+"%").withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(Component.translatable("attribute.name.generic.max_health").append(size*0.04f+"%").withStyle(ChatFormatting.YELLOW));
        }
    }
}
