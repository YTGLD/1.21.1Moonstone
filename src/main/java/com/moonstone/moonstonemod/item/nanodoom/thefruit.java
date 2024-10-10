package com.moonstone.moonstonemod.item.nanodoom;

import com.moonstone.moonstonemod.init.moonstoneitem.i.IDoom;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import java.util.UUID;

public class thefruit extends Item implements IDoom {
    public thefruit() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE).food(
                new FoodProperties.Builder().alwaysEdible().nutrition(10).saturationModifier(10).build()));
    }



    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity p_344979_) {
        return 32;
    }

    public static final UUID uuid = UUID.fromString("8fffffe9-1697-3450-bdc4-1834288fdb99");
    public static final String thefruit = "TheFruit";
    public static AttributeModifier attributeModifier (){
        return new AttributeModifier(ResourceLocation.withDefaultNamespace("base_attack_damage"+"thefruit"), 0, AttributeModifier.Operation.ADD_VALUE);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack s, Level level, LivingEntity living) {
        ItemStack stack = super.finishUsingItem(s, level, living);
        if (living instanceof Player player) {

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler("curio").ifPresent(stacks -> {
                stacks.addPermanentModifier(this.attributeModifier());
            }));
        }
        return stack;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.thefruit.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.thefruit.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.thefruit.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));

        pTooltipComponents.add(Component.translatable("item.thefruit.tool.string.3").withStyle(ChatFormatting.DARK_RED));

    }
}

