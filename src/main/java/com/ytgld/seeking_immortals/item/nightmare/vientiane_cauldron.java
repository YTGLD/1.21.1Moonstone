package com.ytgld.seeking_immortals.item.nightmare;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.renderer.light.Light;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class vientiane_cauldron extends nightmare implements SuperNightmare {

    public static final String p1= "PotionItem1";
    public static final String p1Time= "PotionItem1Time";
    public static final String p1Lvl= "PotionItem1Lvl";
    public static final String p2= "PotionItem2";
    public static final String p2Time= "PotionItem2Time";
    public static final String p2Lvl= "PotionItem2vl";
    public static final String p3= "PotionItem3";
    public static final String p3Time= "PotionItem3Time";
    public static final String p3Lvl= "PotionItem3vl";
    public static final String p4= "PotionItem4";
    public static final String p4Time= "PotionItem4Time";
    public static final String p4Lvl= "PotionItem4vl";
    public boolean overrideOtherStackedOnMe(ItemStack me, ItemStack other, Slot p_150744_, ClickAction p_150745_, Player player, SlotAccess p_150747_) {
        CompoundTag compoundTag =me.get(DataReg.tag);
        if (compoundTag==null){
            me.set(DataReg.tag,new CompoundTag());
        }

        if (p_150745_ == ClickAction.SECONDARY && p_150744_.allowModification(player)) {
            if (other.is(Items.WATER_BUCKET)){
                if (compoundTag != null) {
                    compoundTag.getAllKeys().clear();
                    p_150747_.set(Items.BUCKET.getDefaultInstance());
                    return true;
                }
            }

            if (other.getItem() instanceof PotionItem) {
                if (compoundTag != null) {

                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p1"))   {
                            compoundTag.putBoolean("111p1", true);
                            addTag(p1, p1Time, p1Lvl, other, compoundTag);
                            player.getCooldowns().addCooldown(this, 10);
                        }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p2")) {
                            if (compoundTag.getBoolean("111p1")) {
                                compoundTag.putBoolean("111p2", true);
                                addTag(p2, p2Time, p2Lvl, other, compoundTag);
                                player.getCooldowns().addCooldown(this, 10);
                            }
                        }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p3")) {
                            if (compoundTag.getBoolean("111p2")) {
                                compoundTag.putBoolean("111p3", true);
                                addTag(p3, p3Time, p3Lvl, other, compoundTag);
                                player.getCooldowns().addCooldown(this, 10);
                            }
                        }
                    if (!player.getCooldowns().isOnCooldown(this))
                        if (!compoundTag.getBoolean("111p4")) {
                            if (compoundTag.getBoolean("111p3")) {
                                compoundTag.putBoolean("111p4", true);
                                addTag(p4, p4Time, p4Lvl, other, compoundTag);
                                player.getCooldowns().addCooldown(this, 10);
                            }
                        }
                    if (compoundTag.getBoolean("111p4")) {
                        return false;
                    }else {
                        other.shrink(1);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public void addTag(String name, String time, String lvl, ItemStack me, CompoundTag compoundTag){
        PotionContents potionContents =  me.getOrDefault(DataComponents.POTION_CONTENTS,PotionContents.EMPTY);
        potionContents.forEachEffect((mobEffectInstance) -> {
            ResourceLocation resourceLocation = BuiltInRegistries.MOB_EFFECT.getKey(mobEffectInstance.getEffect().value());
            if (resourceLocation != null) {
                if (!mobEffectInstance.getEffect().value().isInstantenous()) {
                    //时间
                    compoundTag.putFloat(time, mobEffectInstance.getDuration()*2);
                    compoundTag.putInt(lvl, mobEffectInstance.getAmplifier()+1);
                    //药水种类
                    compoundTag.putString(name, resourceLocation.toString());
                }
            }
        });
    }

    public static void kill(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, com.ytgld.seeking_immortals.init.Items.vientiane_cauldron.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is( com.ytgld.seeking_immortals.init.Items.vientiane_cauldron.get())){
                                CompoundTag compoundTag =stack.get(DataReg.tag);
                                if (compoundTag==null){
                                    stack.set(DataReg.tag,new CompoundTag());
                                }else {
                                    addTime(p1Time,compoundTag,event.getEntity().getMaxHealth());
                                    addTime(p2Time,compoundTag,event.getEntity().getMaxHealth());
                                    addTime(p3Time,compoundTag,event.getEntity().getMaxHealth());
                                    addTime(p4Time,compoundTag,event.getEntity().getMaxHealth());
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void addTime(String time, CompoundTag compoundTag,float number){
        compoundTag.putFloat(time,compoundTag.getFloat(time)+number);
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CompoundTag compoundTag =stack.get(DataReg.tag);
        if (compoundTag==null){
            stack.set(DataReg.tag,new CompoundTag());
        }
        if (!slotContext.entity().level().isClientSide&&slotContext.entity().tickCount%20==1) {
            addEffect(compoundTag,slotContext.entity(),p1,p1Time,p1Lvl);
            addEffect(compoundTag,slotContext.entity(),p2,p2Time,p2Lvl);
            addEffect(compoundTag,slotContext.entity(),p3,p3Time,p3Lvl);
            addEffect(compoundTag,slotContext.entity(),p4,p4Time,p4Lvl);
        }
    }
    public void addEffect(CompoundTag compoundTag, LivingEntity entity, String name, String time, String level){
        if (compoundTag != null) {
            String p1Effect = compoundTag.getString(name);

            if (!p1Effect.isEmpty()) {

                String[] pats = p1Effect.split(":");
                ResourceLocation resourceLocation =  ResourceLocation.fromNamespaceAndPath(pats[0], pats[1]);
                Optional<Holder.Reference<MobEffect>> effectReference = BuiltInRegistries.MOB_EFFECT.getHolder (resourceLocation);

                float getDuration = compoundTag.getFloat(time);
                if (getDuration > 0) {
                    compoundTag.putFloat(time, getDuration - 20);
                }else {
                    return;
                }

                int lvl = compoundTag.getInt(level);


                effectReference.ifPresent(mobEffectReference -> entity.addEffect(new MobEffectInstance(mobEffectReference.getDelegate(), 100, lvl)));
            }
        }
    }
    public void addTip(List<Component> tooltipComponents, String Z) {
        int red  = 255;
        int purple  = 255;
        int g = (int) (255 * Math.sin(NewEvent.time/200f));
        g /= 2;
        if (g < 0) {
            g = -g;
        }
        tooltipComponents.add(Component.translatable(Z).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(Light.ARGB.color(255, red, g, purple)))));
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        CompoundTag compoundTag =stack.get(DataReg.tag);
        addTip(tooltipComponents, "item.alchemy_pot.tool.string.1");
        addTip(tooltipComponents, "item.alchemy_pot.tool.string.2");
        addTip(tooltipComponents, "item.alchemy_pot.tool.string.3");
        addTip(tooltipComponents, "item.alchemy_pot.tool.string.4");
        tooltipComponents.add(Component.literal(""));
        tooltipComponents.add(Component.translatable("item.vientiane_cauldron.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.vientiane_cauldron.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        tooltipComponents.add(Component.translatable("item.vientiane_cauldron.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        if (Screen.hasShiftDown()) {
            if (compoundTag != null) {
                tooltipComponents.add(Component.literal("effect: " + compoundTag.getString(p1)));
                tooltipComponents.add(Component.literal("duration: " + compoundTag.getFloat(p1Time)));
                tooltipComponents.add(Component.literal("lvl: " + compoundTag.getInt(p1Lvl)));
                tooltipComponents.add(Component.literal(""));
                tooltipComponents.add(Component.literal("effect: " + compoundTag.getString(p2)));
                tooltipComponents.add(Component.literal("duration: " + compoundTag.getFloat(p2Time)));
                tooltipComponents.add(Component.literal("lvl: " + compoundTag.getInt(p2Lvl)));
                tooltipComponents.add(Component.literal(""));
                tooltipComponents.add(Component.literal("effect: " + compoundTag.getString(p3)));
                tooltipComponents.add(Component.literal("duration: " + compoundTag.getFloat(p3Time)));
                tooltipComponents.add(Component.literal("lvl: " + compoundTag.getInt(p3Lvl)));
                tooltipComponents.add(Component.literal(""));
                tooltipComponents.add(Component.literal("effect: " + compoundTag.getString(p4)));
                tooltipComponents.add(Component.literal("duration: " + compoundTag.getFloat(p4Time)));
                tooltipComponents.add(Component.literal("lvl: " + compoundTag.getInt(p4Lvl)));
            }
        }else {
            tooltipComponents.add(Component.translatable("item.alchemy_pot.tool.string").withStyle(ChatFormatting.GRAY));
        }
    }
}
