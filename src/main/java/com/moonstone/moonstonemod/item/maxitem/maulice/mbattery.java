package com.moonstone.moonstonemod.item.maxitem.maulice;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.MLS;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class mbattery extends MLS {
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 8;
            List<Zombie> entities = player.level().getEntitiesOfClass(Zombie.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Zombie zombie : entities) {
                zombie.goalSelector.addGoal(1, new AvoidEntityGoal<>(zombie, Player.class, 6.0F, 1.0D, 1.2D));
            }
        }
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return 2;
    }

    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return 2;
    }


    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 1, AttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()), 0.18, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.mbattery.tool.string").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mbattery.tool.string.1").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mbattery.tool.string.2").withStyle(ChatFormatting.DARK_GREEN));
        pTooltipComponents.add(Component.translatable("item.mbattery.tool.string.3").withStyle(ChatFormatting.DARK_GREEN));

    }

}