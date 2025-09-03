package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.MainNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.nightmare.tip.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.tip.ToolTip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_stone extends nightmare implements SuperNightmare ,  AllTip, MainNightmare {
    public static final String  uDead = "undead";

    public static final String deathTag  = "DeathTag";
    public static void LivingHurtEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_stone.get())) {
                if (Handler.hascurio(player,Items.candle.get())){
                    return;
                }
                if (Handler.hascurio(player, Items.blood_god.get())) {
                    return;
                }
                if (player.getHealth() >= player.getMaxHealth()) {
                    float s  = (float) Config.SERVER.nightmare_base_stone.getAsDouble();
                    s+=1;
                    event.setAmount(event.getAmount() * s);

                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone.get())) {
                        if (event.getAmount() > player.getHealth()) {
                            event.setAmount(0);
                            player.setHealth(1);
                            player.getCooldowns().addCooldown(Items.nightmare_base_stone.get(),200);
                        }
                    }

                }
            }
        }
    }
    public static void CurioHurtEvent(CurioHurtEvent event){
        Player player = event.getPlayer();
        ItemStack stack = event.getStack();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (Handler.hascurio(player,Items.nightmare_base_stone.get())){
            if (stack.is(Items.nightmare_base_stone.get())) {
                if (event.getEvent().getSource().getEntity() instanceof LivingEntity) {
                    if (compoundTag != null) {
                        compoundTag.putInt(deathTag, compoundTag.getInt(deathTag) + 1);
                    }
                }
            }
        }
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).isPresent()
                    && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }



    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_stone" + "add_slot"
                ), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (slotContext.entity() instanceof Player player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.get(DataReg.tag)==null){
                stack.set(DataReg.tag,new CompoundTag());
            }
            if (stack.get(DataReg.tag)!=null){
                stack.get(DataReg.tag).putInt(uDead,now);
            }

            player.getAttributes().addTransientAttributeModifiers(ad(stack));
        }
    }
    public Multimap<Holder<Attribute>, AttributeModifier> ad(ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(DataReg.tag)!=null) {
            int lvl = stack.get(DataReg.tag).getInt(uDead);
            float heal = 0.85f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal,new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID +this.getDescriptionId()),
                    heal, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.MOVEMENT_SPEED,new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID+this.getDescriptionId()),
                    speed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_DAMAGE,new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID+this.getDescriptionId()),
                    damage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ATTACK_SPEED,new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID+this.getDescriptionId()),
                    attSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(Attributes.ARMOR,new AttributeModifier(ResourceLocation.parse(SeekingImmortalsMod.MODID+this.getDescriptionId()),
                    armor, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));



        }
        return modifiers;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_virus").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.nightmare_base_stone_brain").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.moonstone.end_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new ToolTip(this,stack));
    }

    @Override
    public Map<Integer, String> tooltip() {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }

    @Override
    public Map<Integer, String> element(ItemStack stack) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"这是试炼  磨练你的意志");
        map.put(2,"生命值越低 各方面属性越高");
        return map;
    }
}

