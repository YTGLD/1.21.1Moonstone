package com.moonstone.moonstonemod.item.blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.snake;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class blood_snake extends Item implements ICurioItem, Blood {
    public blood_snake() {
        super(new Properties().stacksTo(1).durability(1000000000).rarity(Rarity.UNCOMMON));
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier>modifierMultimap = HashMultimap.create();
        modifierMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(id, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifierMultimap;
    }
    public static void Die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.blood_snake.get())){
                int s = 3;
                if (event.getEntity().getMaxHealth()%20==0){
                    if (event.getEntity().getMaxHealth()/20<10) {
                        s += (int) (event.getEntity().getMaxHealth() / 20);
                    }else {
                        s+=10;
                    }
                }
                for (int i = 0; i < s; i++) {
                    snake line = new snake(EntityTs.snake.get(), player.level());
                    line.setPos(new Vec3(event.getEntity().getX()+i/5f* Mth.nextFloat(RandomSource.create(),-1,1),event.getEntity().getY()+i/5f* Mth.nextFloat(RandomSource.create(),-1.01f,1.01f),event.getEntity().getZ()+i/5f* Mth.nextFloat(RandomSource.create(),-1.011f,1.011f)));
                    line.setDeltaMovement(0,1,0);
                    line.setOwnerUUID(player.getUUID());

                    player.level().addFreshEntity(line);
                }
            }
        }
    }



    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_snake.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_snake.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.blood_snake.tool.string.2").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("item.blood_snake.tool.string.3").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}