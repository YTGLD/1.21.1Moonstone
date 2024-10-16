package com.moonstone.moonstonemod.event;

import com.mojang.datafixers.util.Either;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.loot.DungeonLoot;
import com.moonstone.moonstonemod.init.AttReg;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import com.moonstone.moonstonemod.item.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.TheNecora.bnabush.giant_boom_cell;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.blood_sun;
import com.moonstone.moonstonemod.item.deceased_contract;
import com.moonstone.moonstonemod.item.maxitem.god_lead;
import com.moonstone.moonstonemod.item.maxitem.malice_die;
import com.moonstone.moonstonemod.item.nightmare.nightmare_head;
import com.moonstone.moonstonemod.item.nightmare.nightmare_heart;
import com.moonstone.moonstonemod.item.nightmare.nightmare_orb;
import com.moonstone.moonstonemod.item.plague.ALL.dna;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioCanEquipEvent;

import java.text.DecimalFormat;
import java.util.stream.Stream;

public class NewEvent {
    public static final String lootTable = "god_loot";
    public static final String meet = "the_meet";
    public static final String die = "the_die";
    public static final String doctor = "the_doctor";
    public static final String cell_cell = "the_cell";
    public static final String chromosome = "the_chromosome";
    public static final String bone = "the_bone";
    public static final String die_body = "the_die_body";
    @SubscribeEvent
    public  void doBreak(CurioCanEquipEvent event) {
        Item item = event.getStack().getItem();
        if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(MoonStoneMod.MODID)) {
            if (Handler.hascurio(event.getEntity(), item)) {
                event.setEquipResult(TriState.FALSE);
            }
        }
    }


    @SubscribeEvent
    public  void doBreak(LivingEntityUseItemEvent.Start event){
        dna.doBreak(event);
    }
    @SubscribeEvent
    public  void Finish(LivingEntityUseItemEvent.Finish event){
        dna.eat(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_orb.nightmare_orb_heal(event);
        nightmare_head.LivingHealEvent(event);

        DungeonLoot.heal(event);
        if (event.getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.heal)!=null){
                float attack = (float) living.getAttribute(AttReg.heal).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.get(DataReg.tag) !=null){
            if (stack.get(DataReg.tag).getBoolean("ALLBattery")){
                event.getToolTip().add(Component.translatable("item.moonstone.battery").withStyle(ChatFormatting.GOLD));
            }
        }

        if (stack.getItem() instanceof IBattery){
            event.getToolTip().add(Component.translatable("item.moonstone.tooltip.battery").withStyle(ChatFormatting.GOLD));

        }
        if (stack.get(DataReg.tag) !=null) {
            if (stack.get(DataReg.tag).getBoolean(Difficulty.PEACEFUL.getKey())) {

                event.getToolTip().add(1,Component.translatable("moonstone.difficulty.name.peaceful").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(DataReg.tag).getBoolean(Difficulty.EASY.getKey())) {

                event.getToolTip().add(1,Component.translatable("moonstone.difficulty.name.easy").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(DataReg.tag).getBoolean(Difficulty.NORMAL.getKey())) {
                event.getToolTip().add(1,Component.translatable("moonstone.difficulty.name.normal").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(DataReg.tag).getBoolean(Difficulty.HARD.getKey())) {
                event.getToolTip().add(1,Component.translatable("moonstone.difficulty.name.hard").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }
            if (stack.get(DataReg.tag).getBoolean(lootTable)) {
                event.getToolTip().add(1,Component.translatable("moonstone.difficulty.name.god").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Component.translatable("moonstone.difficulty.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }

            DecimalFormat df = new DecimalFormat("#.###");


            if (stack.get(DataReg.tag).getFloat(meet)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.meet").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("effect.minecraft.health_boost").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(meet)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }


            if (stack.get(DataReg.tag).getFloat(die)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.die").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));

                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("effect.minecraft.strength").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(die)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }


            if (stack.get(DataReg.tag).getFloat(doctor)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.doctor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("attribute.name.moonstone.heal").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(doctor)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }





            if (stack.get(DataReg.tag).getFloat(cell_cell)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.cell_cell").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("attribute.name.moonstone.cit").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(cell_cell)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }

            if (stack.get(DataReg.tag).getFloat(chromosome)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.chromosome").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("attribute.name.player.block_break_speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(chromosome)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }
            if (stack.get(DataReg.tag).getFloat(bone)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.bone").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("attribute.name.generic.movement_speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(bone)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }
            if (stack.get(DataReg.tag).getFloat(die_body)!=0) {
                event.getToolTip().add(1,Component.translatable("moonstone.curse.name.all.this").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                        .append(Component.translatable("moonstone.curse.name.die_body").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4))))
                        .append(Component.translatable("moonstone.curse.name.all").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFF7256)))));
                if (Screen.hasControlDown()){
                    event.getToolTip().add(1,Component.translatable("attribute.name.generic.armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEEE9BF)))
                            .append(Component.literal(String.valueOf(df.format(stack.get(DataReg.tag).getFloat(die_body)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFFFE4C4)))));
                }
            }
        }

    }
    @SubscribeEvent
    public void LivingHurtEvent(LivingIncomingDamageEvent event){
        nightmare_heart.NigH(event);
        nightmare_head.headHurt(event);
        bat_cell.Bat(event);
        max_sword.hurt(event);
        max_eye.A(event);
        max_blood_eye.Att(event);
        blood_amout.Hurt(event);
        giant_boom_cell.Boom(event);
        deceased_contract.attack(event);
        malice_die.att(event);
        god_lead.hurtS(event);
        dna.hur(event);
        DungeonLoot.attack(event);


        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.alL_attack)!=null){
                float attack = (float) living.getAttribute(AttReg.alL_attack).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
        DungeonLoot.cit(event);
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.cit)!=null){
                float attack = (float) living.getAttribute(AttReg.cit).getValue();
                event.setDamageMultiplier(event.getDamageMultiplier()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void EntityInteract(PlayerInteractEvent.RightClickItem event){
        max_blood_cube.RightClickItem(event);
    }
    @SubscribeEvent
    public  void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Stop event){


    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_head.LivingDeathEvent(event);
        max_eye.Die(event);
        blood_snake.Die(event);
        max_sword.die(event);
        the_prison_of_sin.LivingDeathEvent(event);
        blood_magic_box.Did(event);
        deceased_contract.Did(event);
        blood_sun.Did(event);
        dna.dieD(event);
    }
    @SubscribeEvent
    public void EffectTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living){
            if (living.hasEffect(Effects.blood)&&living.getEffect(Effects.blood)!=null) {
                if (!living.level().isClientSide){
                    if (living.tickCount%20==0){
                        living.hurt(living.damageSources().magic(),living.getEffect(Effects.blood).getAmplifier());
                        living.invulnerableTime = 0;
                    }
                }
            }
            if (living.hasEffect(Effects.rage)&&living.getEffect(Effects.rage)!=null) {
                if (!living.level().isClientSide){
                    if (living.tickCount%10==0){
                        living.hurt(living.damageSources().magic(),living.getEffect(Effects.rage).getAmplifier()+0.5f);
                        living.invulnerableTime = 0;
                    }
                }
            }
        }
    }
}
