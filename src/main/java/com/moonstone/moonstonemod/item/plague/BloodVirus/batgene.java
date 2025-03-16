package com.moonstone.moonstonemod.item.plague.BloodVirus;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonstone.moonstonemod.init.moonstoneitem.AttReg;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.BloodViru;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class batgene extends BloodViru {
    private static final String bat = "BatGll";
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().getAttributes().addTransientAttributeModifiers(this.Head(stack));
        if (stack.get(DataReg.tag)!=null) {
            if (slotContext.entity() instanceof Player player) {
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                int integers = 0;
                for (LivingEntity living : entities) {
                    if (living instanceof Bat) {
                        integers++;
                    }
                }
                float integer = integers;
                integer /= 10;

                stack.get(DataReg.tag).putFloat(bat,integer);
            }
            if (slotContext.entity() instanceof Player player) {
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 24;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

                for (LivingEntity living : entities) {
                    if (living instanceof Bat bat) {
                        bat.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2, false, false));
                        bat.setTarget(player);
                    }
                }


            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getAttributes().removeAttributeModifiers(this.Head(stack));

    }

    private Multimap<Holder<Attribute>, AttributeModifier> Head(ItemStack stack){
        Multimap<Holder<Attribute>, AttributeModifier> multimap = HashMultimap.create();
        CompoundTag tag = stack.get(DataReg.tag);
        float s = 0;
        if (tag != null) {
            s = tag.getInt(bat)-0.1f;
        }


        multimap.put(AttReg.alL_attack, new AttributeModifier(
                ResourceLocation.withDefaultNamespace("base_attack_damage"+this.getDescriptionId()),
                s,
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.batgene.tool.string").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable("item.batgene.tool.string.1").withStyle(ChatFormatting.RED));
            pTooltipComponents.add(Component.translatable(""));
            pTooltipComponents.add(Component.translatable("item.batgene.tool.string.2").withStyle(ChatFormatting.RED));
        } else {
            pTooltipComponents.add(Component.translatable("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
