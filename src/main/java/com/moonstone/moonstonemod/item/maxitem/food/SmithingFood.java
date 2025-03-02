package com.moonstone.moonstonemod.item.maxitem.food;

import com.moonstone.moonstonemod.init.moonstoneitem.Effects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class SmithingFood  extends Item {
    public SmithingFood() {
        super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(8).saturationModifier(0.8F).build()));
    }
    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity p_344979_) {
        return 32;
    }
    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        ItemStack stack = super.finishUsingItem(s, level, living);
        if (living instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("charm")
                    .ifPresent(stacks -> {
                        stacks.addPermanentModifier(new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                                1, AttributeModifier.Operation.ADD_VALUE));
                    }));
            player.addEffect(new MobEffectInstance(Effects.dead, 1200, 4));
            player.addEffect(new MobEffectInstance(Effects.blood, 200, 1));
            player.displayClientMessage(Component.translatable("item.moonstone.smithing_food.string").withStyle(ChatFormatting.WHITE), true);
        }
        return stack;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.string.tool.string").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(""));
        }else {
            tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("item.string.tool.string.1").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.string.tool.string.2").withStyle(ChatFormatting.GOLD));
          }
    }
}
