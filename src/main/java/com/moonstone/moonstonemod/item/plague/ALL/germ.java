package com.moonstone.moonstonemod.item.plague.ALL;

import com.moonstone.moonstonemod.moonstoneitem.extend.medIC;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class germ extends medIC implements ICurioItem {

    /*
    private final String lvl= "germString";
    private final String lvlSize= "germStringLvlSize";

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.germ.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.germ.tool.string.1").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("SHIFT").withStyle(ChatFormatting.DARK_RED));
        }
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("now："+stack.getOrCreateTag().getInt(lvlSize)).withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("").withStyle(ChatFormatting.RED));
        if (stack.getOrCreateTag().getInt(lvl)==0){
            pTooltipComponents.add(Component.translatable("+1 伤害").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+15% 攻速").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+4 护甲").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+8 生命").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+20% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==1){
            pTooltipComponents.add(Component.translatable("+1.5 伤害").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+20% 攻速").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+5 护甲").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+10 生命").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+25% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==2){
            pTooltipComponents.add(Component.translatable("+1.75 伤害").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+25% 攻速").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+6 护甲").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+12 生命").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+30% 游泳速度").withStyle(ChatFormatting.RED));
        }
        if (stack.getOrCreateTag().getInt(lvl)==3){
            pTooltipComponents.add(Component.translatable("+2 伤害").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+30% 攻速").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+7 护甲").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+14 生命").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("+35% 游泳速度").withStyle(ChatFormatting.RED));
        }
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(player, stack));
            stack.getOrCreateTag().putInt(lvl, stack.getOrCreateTag().getInt(lvl));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            player.getAttributes().removeAttributeModifiers(this.getAttributeModifiers(player, stack));
        }
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(Player player, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("efe2ab2d-072a-337a-9d65-10da4f48c969");
        float ss = stack.getOrCreateTag().getInt(lvl);
        if (ss == 0) {
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 4, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 8, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }else if (ss == 1){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1.5, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 5, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 10, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        } else  if (ss == 2){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 1.75, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 6, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 12, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }else  if (ss == 3){
            modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 2, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifierMultimap.put(Attributes.ARMOR, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 7, AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, MoonStoneMod.MODID + "ec",14 , AttributeModifier.Operation.ADDITION));
            modifierMultimap.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, MoonStoneMod.MODID + "ec", 0.35, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        }


        return modifierMultimap;
    }


     */

}
