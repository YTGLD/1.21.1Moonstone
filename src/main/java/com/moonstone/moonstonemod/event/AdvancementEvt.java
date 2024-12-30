package com.moonstone.moonstonemod.event;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class AdvancementEvt {

    //4死心之胚
    public static final String nightmare_base_black_eye_heart = "nightmare_base_black_eye_heart";
    //4惶恐肉瘤
    public static final String nightmare_base_black_eye_eye = "nightmare_base_black_eye_eye";
    //口红
    public static final String nightmare_base_black_eye_red = "nightmare_base_black_eye_red";






    //4末世之脑
    public static final String nightmare_base_stone_brain = "nightmare_base_stone_brain";
    //4绝望共鸣
    public static final String nightmare_base_stone_meet = "nightmare_base_stone_meet";
    //4Nightecora病毒
    public static final String nightmare_base_stone_virus = "nightmare_base_stone_virus";




    public static final String nightmare_base_reversal_card = "nightmare_base_reversal_card";

    public static final String nightmare_base_reversal_mysterious = "nightmare_base_reversal_mysterious";

    public static final String nightmare_base_reversal_orb = "nightmare_base_reversal_orb";




    public static final String nightmare_base_redemption_deception = "nightmare_base_redemption_deception";
    public static final String nightmare_base_redemption_degenerate = "nightmare_base_redemption_degenerate";
    public static final String nightmare_base_redemption_down_and_out = "nightmare_base_redemption_down_and_out";


    @SubscribeEvent
    public void nightmare_base_redemption_degenerate(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_redemption.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_redemption.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Villager raider) {
                                        if (stack.get(DataReg.tag).getInt(nightmare_base_redemption_degenerate)<100) {
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_degenerate, stack.get(DataReg.tag).getInt(nightmare_base_redemption_degenerate)+1);
                                        }else if (stack.get(DataReg.tag).getInt(nightmare_base_redemption_degenerate) == 100){
                                            player.addItem(new ItemStack(Items.nightmare_base_redemption_degenerate.get()));
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_degenerate, stack.get(DataReg.tag).getInt(nightmare_base_redemption_degenerate)+1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void nightmare_base_redemption_deception(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_redemption.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_redemption.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Raider raider) {
                                        if (stack.get(DataReg.tag).getInt(nightmare_base_redemption_deception)<100) {
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_deception, stack.get(DataReg.tag).getInt(nightmare_base_redemption_deception)+1);
                                        }else if (stack.get(DataReg.tag).getInt(nightmare_base_redemption_deception) == 100){
                                            player.addItem(new ItemStack(Items.nightmare_base_redemption_deception.get()));
                                            stack.get(DataReg.tag).putInt(nightmare_base_redemption_deception, stack.get(DataReg.tag).getInt(nightmare_base_redemption_deception)+1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void nightmare_base_reversal_card(LivingDropsEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_reversal.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBoolean(nightmare_base_reversal_card)) {

                                            event.getDrops().add(new ItemEntity(warden.level(),warden.getX(),warden.getY(),warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_reversal_card)));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_reversal_card, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void nightmare_base_stone_meet(LivingDropsEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_stone.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_stone.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof EnderDragon warden) {
                                        if (!stack.get(DataReg.tag).getBoolean(nightmare_base_stone_meet)) {

                                            event.getDrops().add(new ItemEntity(warden.level(),warden.getX(),warden.getY(),warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_stone_meet)));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_meet, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void nightmare_base_stone_virus(LivingUseTotemEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_base_stone.get())){
                if (event.getSource().getEntity() instanceof WitherBoss witherBoss) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.is(Items.nightmare_base_stone.get())) {
                                    if (stack.get(DataReg.tag) != null) {
                                        if (!stack.get(DataReg.tag).getBoolean(nightmare_base_stone_virus)) {
                                            player.addItem(new ItemStack(Items.nightmare_base_stone_virus.get()));
                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_virus, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }


    @SubscribeEvent
    public void nightmare_base_stone_brain(LivingDropsEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_stone.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_stone.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Zombie warden) {
                                        if (!stack.get(DataReg.tag).getBoolean(nightmare_base_stone_brain)) {

                                            event.getDrops().add(new ItemEntity(warden.level(),warden.getX(),warden.getY(),warden.getZ(),
                                                    new ItemStack(Items.nightmare_virus)));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_stone_brain, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }





    @SubscribeEvent
    public void LivingUseTotemEvent(LivingUseTotemEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmare_base_black_eye.get())){
                if (player.hasEffect(MobEffects.POISON)
                        && player.hasEffect(MobEffects.WITHER)
                        && player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
                        && player.hasEffect(MobEffects.BLINDNESS)
                        && player.hasEffect(MobEffects.DARKNESS)){
                    if (player.getRemainingFireTicks() > 0){
                        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                            Map<String, ICurioStacksHandler> curios = handler.getCurios();
                            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                                ICurioStacksHandler stacksHandler = entry.getValue();
                                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                    ItemStack stack = stackHandler.getStackInSlot(i);
                                    if (stack.is(Items.nightmare_base_black_eye.get())) {
                                        if (stack.get(DataReg.tag) != null) {
                                            if (!stack.get(DataReg.tag).getBoolean(nightmare_base_black_eye_heart)){

                                                player.addItem(new ItemStack(Items.nightmare_base_black_eye_heart.get()));

                                                stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_heart,true);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void drop(LivingDropsEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmare_base_black_eye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_black_eye.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (event.getEntity() instanceof Warden warden) {
                                        if (!stack.get(DataReg.tag).getBoolean(nightmare_base_black_eye_eye)) {

                                            event.getDrops().add(new ItemEntity(warden.level(),warden.getX(),warden.getY(),warden.getZ(),
                                                    new ItemStack(Items.nightmare_base_black_eye_eye)));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_eye, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public static void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                               Entity entity,
                               int lv){
        if (entity instanceof Player player ){
            if (Handler.hascurio(player,Items.nightmare_base_black_eye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_black_eye.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(nightmare_base_black_eye_red)) {
                                        if (Mth.nextInt(RandomSource.create(), 0, 100) <= lv) {
                                            generatedLoot.add(new ItemStack(Items.nightmare_base_black_eye_red.get()));

                                            stack.get(DataReg.tag).putBoolean(nightmare_base_black_eye_red, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }



    public static void nightmare_base_reversal_mysteriousLOOT(ObjectArrayList<ItemStack> generatedLoot,
                               Entity entity){
        if (entity instanceof Player player ){
            if (Handler.hascurio(player,Items.nightmare_base_reversal_orb.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmare_base_reversal_orb.get())) {
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(nightmare_base_reversal_mysterious)) {
                                        generatedLoot.add(new ItemStack(Items.nightmare_base_reversal_mysterious.get()));
                                        stack.get(DataReg.tag).putBoolean(nightmare_base_reversal_mysterious, true);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
