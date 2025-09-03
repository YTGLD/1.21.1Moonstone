package com.ytgld.seeking_immortals.item.nightmare.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class blood_ring extends nightmare implements SuperNightmare {
    public static final String blood = "BloodName";
    public static final String bloodtime = "bloodTimeName";


    public static final String give_End = "give_End_blood_ring";
    public static final String giveName_damage = "giveName_damage_blood_ring";

    public static void giveBlood(Player player,ItemStack stack){
        if (Handler.hascurio(player, Items.blood_ring.get())) {
            if (!player.getCooldowns().isOnCooldown(Items.blood_ring.get())){
                if (stack.is(Items.blood_ring.get())) {
                    if (stack.getItem() instanceof nightmare nightmare) {
                        float s = player.getHealth() / 2;
                        CompoundTag compoundTag = stack.get(DataReg.tag);
                        if (compoundTag != null) {
                            float sa =  s * (1 + (nightmare.nowLevel(stack) / 100f));

                            if (player.getHealth()>=player.getMaxHealth()) {
                                if (compoundTag.getInt(terrorName) < nightmare.maxLevel(stack)) {
                                    compoundTag.putInt(terrorName, compoundTag.getInt(terrorName) + 5);
                                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RAVAGER_ROAR, SoundSource.AMBIENT, 1, 1);
                                }
                            }

                            player.hurt(player.damageSources().genericKill(),s);
                            player.getFoodData().eat(-10,-10);
                            compoundTag.putInt(bloodtime, (int) (300+sa));
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.RAVAGER_ROAR, SoundSource.AMBIENT, 0.25f, 0.25f);


                            float sb = (player.getHealth()) / 2;
                            float sd =  sb * (1 + (nightmare.nowLevel(stack) / 100f));
                            compoundTag.putFloat(blood,sd);
                            player.getCooldowns().addCooldown(Items.blood_ring.get(), (int) (100+sa));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player) {
            if (!slotContext.entity().level().isClientSide) {
                slotContext.entity().getAttributes().addTransientAttributeModifiers(ad(stack, this));

                CompoundTag compoundTag = stack.get(DataReg.tag);
                if (compoundTag!=null) {
                    if (compoundTag.getFloat(blood)>100) {
                        compoundTag.putFloat(blood, 100);
                    }
                    if (compoundTag.getInt(bloodtime)<=0){
                        compoundTag.putFloat(blood, 0);
                    }else {
                        compoundTag.putInt(bloodtime,compoundTag.getInt(bloodtime)-1);
                    }
                }
            }


        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide) {
            slotContext.entity().getAttributes().removeAttributeModifiers(ad(stack, this));
        }
    }

    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack, Terror terror) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag!=null) {

            float s =0;
            if (compoundTag.getInt(bloodtime) > 0) {
                s = compoundTag.getFloat(blood) / 100f / 2f;
            }
            modifiers.put(AttReg.heal, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    -s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID + this.getDescriptionId()),
                    s, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
        return modifiers;
    }

    @Override
    public int nowLevel(ItemStack stack) {
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (compoundTag != null) {
            return compoundTag.getInt(terrorName);
        }
        return 0;
    }

    @Override
    public int color(ItemStack stack) {
        return Light.ARGB.color(255,20,100,255);
    }

    /**
     *
     * @param stack 这个物品
     * @return 这个物品的最大上限为多少，这里是额外150%，也就是250%
     */
    @Override
    public int maxLevel(ItemStack stack) {
        return 150;
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/cube.png");
    }

    @Override
    public boolean isRot(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.blood_ring.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.blood_ring.tool.string.2")
                        .append(Component.literal(String.format("%.2f",0.5 * (1 +(this.nowLevel(stack)/100f))))).append("% ")
                        .append(Component.translatable("item.blood_ring.tool.string.3")).withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.blood_ring.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.blood_ring.tool.string.5").withStyle(ChatFormatting.GOLD));



    }
}
