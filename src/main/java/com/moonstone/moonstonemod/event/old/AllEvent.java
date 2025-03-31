package com.moonstone.moonstonemod.event.old;

import com.google.common.collect.Lists;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.flysword;
import com.moonstone.moonstonemod.entity.suddenrain;
import com.moonstone.moonstonemod.entity.zombie.cell_zombie;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.EntityTs;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.MLS;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.Perhaps;
import com.moonstone.moonstonemod.init.moonstoneitem.i.*;
import com.moonstone.moonstonemod.item.man.ManDNA;
import com.moonstone.moonstonemod.item.maxitem.rage.RAGE;
import com.moonstone.moonstonemod.item.maxitem.the_heart;
import com.moonstone.moonstonemod.item.maxitem.uncommon.evilmug;
import com.moonstone.moonstonemod.item.nanodoom.buyme.wind_and_rain;
import com.moonstone.moonstonemod.item.nanodoom.doomeye;
import com.moonstone.moonstonemod.item.nanodoom.doomswoud;
import com.moonstone.moonstonemod.item.nanodoom.thefruit;
import com.moonstone.moonstonemod.item.plague.ALL.dna;
import com.moonstone.moonstonemod.item.plague.BloodVirus.Skill.batskill;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_blood;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_boom;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_calcification;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.cell_mummy;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

public class AllEvent {
    private int shield = 1;
    private int Kidney = 100;
     private float clientSideAttackTime = 0;

     public static final String cod = "cod";
     public static final String SALMON = "SALMON";
     public static final String CHICKEN = "CHICKEN";
     public static final String BEEF = "BEEF";
     public static final String RABBIT = "RABBIT";
     public static final String MUTTON = "MUTTON";
     public static final String PORKCHOP = "PORKCHOP";
     public static final String TROPICAL_FISH = "TROPICAL_FISH";
     public static final String give = "GiveItem";
     public static final String fungus = "GiveFungus";
     public static final String virus = "virus";
    public static float aFloat = 0;
    public static final String lvl= "germString";
    public static final String lvlSize= "germStringLvlSize";
    public static String lvl_parasite = "lvl";
    public static String sizeLevel = "sizeLevel";
    public static String blood = "bloodgene";
    public static String rage = "ragegene";
    public static String FlyEye = "FlyNecoraorb";
    public static String FlySword = "FlySword";

    public static final String DamageCell = "DamageCell";
    public static final  String muMMY = cell_mummy.Mummy;
    public static final  String boom = cell_boom.cb;
    public static final  String calcification = cell_calcification.cc;
    public static final  String cb_blood = cell_blood.c_blood;
    public static final String Gorillas ="Gorillas";
    @SubscribeEvent
    public void th_dna(LivingIncomingDamageEvent event){
        if ((event.getSource().getEntity() instanceof Player player)){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            if (stack.get(DataReg.tag).getBoolean(Difficulty.EASY.getKey())){
                                event.setAmount(event.getAmount()+0);
                            }
                            if (stack.get(DataReg.tag).getBoolean(Difficulty.NORMAL.getKey())){
                                event.setAmount(event.getAmount()+0.08f);
                            }
                            if (stack.get(DataReg.tag).getBoolean(Difficulty.HARD.getKey())){
                                event.setAmount(event.getAmount()+0.17f);
                            }
                            if (stack.get(DataReg.tag).getBoolean(NewEvent.lootTable)){
                                event.setAmount(event.getAmount()+0.25f);
                            }

                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void fermentation(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fermentation.get())){
                if (player.getCooldowns().isOnCooldown(Items.fermentation.get())){
                    event.setAmount(event.getAmount() * 0.3f);
                }else {
                    event.setAmount(event.getAmount() * 4);
                    player.getCooldowns().addCooldown(Items.fermentation.get(), 200);
                }
            }
        }
    }
    @SubscribeEvent
    public  void parasite(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.parasite.get())) {
                            if (Handler.hascurio(player, Items.parasite.get())) {
                                if (stack.get(DataReg.tag)!=null) {
                                    if (stack.get(DataReg.tag).getInt(sizeLevel) > 900) {
                                        if (player.getFoodData().getFoodLevel() > 12) {
                                            event.setAmount(event.getAmount() * 1.4f);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void the_heart(LivingDropsEvent event){
        if ((event.getSource().getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.the_heart.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            BundleContents bundlecontents = stack.get(DataComponents.BUNDLE_CONTENTS);
                            if (bundlecontents != null && !bundlecontents.isEmpty()){
                                Set<ResourceLocation> resourceLocations = new HashSet<>();
                                bundlecontents.itemsCopy().forEach((itemStack)-> {
                                    if (resourceLocations.add(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))) {
                                        Collection<ItemEntity> drop = event.getDrops();
                                        for (ItemEntity entity : drop) {
                                            ItemStack i_stack = entity.getItem();
                                            if (i_stack.is(itemStack.getItem())) {
                                                if (!(i_stack.getMaxStackSize() < 3)) {
                                                    i_stack.setCount(i_stack.getCount() * 3);
                                                    entity.setItem(i_stack);
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });

            }
        }
    }
    @SubscribeEvent
    public void Boom(LivingIncomingDamageEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.cell_boom.get())){
                if (event.getSource().is(DamageTypes.EXPLOSION)){
                    event.setAmount(0);
                }
            }
        }
    }
    @SubscribeEvent
    public void gen(LivingIncomingDamageEvent event) {
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player, Items.air.get())) {
                if (!player.onGround()) {
                    event.setAmount(event.getAmount() * 0.8f);
                }
                if (!player.isInWater()) {
                    event.setAmount(event.getAmount() * 0.85f);
                }
            }
        }
    }

    @SubscribeEvent
    public void evil(LivingDeathEvent event){
        if ((event.getEntity() instanceof Player player)) {
            if (Handler.hascurio(player,Items.cell_boom.get())){
                player.level().explode(null,player.getX(),player.getY(),player.getZ(),5.5f,true , Level.ExplosionInteraction.NONE);
            }
        }

        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.giant.get())){

                if (!Handler.hascurio(player,Items.giant_nightmare.get())) {
                    if (!player.getCooldowns().isOnCooldown(Items.giant.get())) {
                        if (player.level() instanceof ServerLevel p_222881_) {
                            if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {

                                Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER,Items.giant.get(), 600);
                                player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                                if (Handler.hascurio(player, Items.mother_cell.get())) {
                                    if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                        Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER,Items.giant.get(), 600);
                                    }
                                    for (int i = 0; i < 2; i++) {
                                        cell_zombie cell_zombie = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                                        cell_zombie.setOwnerUUID(player.getUUID());
                                        cell_zombie.setPos(player.position());
                                        player.level().addFreshEntity(cell_zombie);
                                    }
                                }
                                player.getCooldowns().addCooldown(Items.giant.get(), 600);
                            }
                        }
                    }
                }else {
                    if (!player.getCooldowns().isOnCooldown(Items.giant.get())) {
                        if (player.level() instanceof ServerLevel p_222881_) {
                            if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                                Handler.trySpawnMob(player, EntityTs.nightmare_giant_to.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER,Items.giant.get(), 600);

                                player.hurt(player.damageSources().dryOut(), player.getHealth() /2);
                                player.level().playSound(null, player.blockPosition(), SoundEvents.WARDEN_EMERGE, SoundSource.NEUTRAL, 1.0F, 1.0F);

                                if (Handler.hascurio(player, Items.subspace_cell.get())) {
                                    for (int i = 0; i < 3; i++) {
                                        Handler.trySpawnMob(player, EntityTs.cell_giant.get(), MobSpawnType.TRIGGERED, p_222881_, new BlockPos((int) event.getEntity().getX(), (int) event.getEntity().getY(), (int) event.getEntity().getZ()), 10, 2, 3, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER,Items.giant.get(), 600);
                                    }
                                }


                                player.getCooldowns().addCooldown(Items.giant.get(), 1200);

                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.cell.get())){
                if (Mth.nextInt(RandomSource.create(),1, 2) ==1 ){
                    cell_zombie z = new cell_zombie(EntityTs.cell_zombie.get(), player.level());
                    z.teleportTo(event.getEntity().getX(),event.getEntity().getY(), event.getEntity().getZ());
                    z.setOwnerUUID(player.getUUID());

                    if (Handler.hascurio(player,Items.adrenaline.get())){
                        z.addTag(DamageCell);
                    }
                    if (Handler.hascurio(player,Items.cell_mummy.get())){
                        z.addTag(muMMY);
                    }
                    if (Handler.hascurio(player,Items.cell_boom.get())){
                        z.addTag(boom);
                    }
                    if (Handler.hascurio(player,Items.cell_calcification.get())){
                        z.addTag(calcification);
                    }
                    if (Handler.hascurio(player,Items.cell_blood.get())){
                        z.addTag(cb_blood);
                    }
                    player.level().addFreshEntity(z);
                }
            }
        }
    }

    @SubscribeEvent
    public void beacon(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.beacon.get())){
                Collection<MobEffectInstance> collection  = player.getActiveEffects();
                if (!collection.isEmpty()) {
                    if (event.getSource().getEntity() != null) {
                        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(event.getSource().getEntity().level(), event.getSource().getEntity().getX(), event.getSource().getEntity().getY(), event.getSource().getEntity().getZ());
                        areaeffectcloud.setRadius(2.5F);
                        areaeffectcloud.setRadiusOnUse(-0.5F);
                        areaeffectcloud.setWaitTime(10);
                        areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
                        areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());
                        for(MobEffectInstance mobeffectinstance : collection) {
                            areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
                        }
                        event.getSource().getEntity().level().addFreshEntity(areaeffectcloud);
                        player.removeAllEffects();
                    }
                }
            }
        }
    }



    @SubscribeEvent
    public void brainLHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.brain.get())){
                String name = event.getEntity().getName().getString();
                player.getPersistentData().putInt(name, player.getPersistentData().getInt(name) +1);
                if (player.getPersistentData().getInt(name)>= Config.SERVER.m_brain_many.get()){
                    event.setAmount((float) (event.getAmount() * Config.SERVER.m_brain_critical.get()));
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.NEUTRAL, 4.5F, 4.1F);
                    player.getPersistentData().remove(name);
                }
            }
        }

    }
    @SubscribeEvent
    public void evil(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.evilcandle.get())){
                if (event.getSource().is(DamageTypes.IN_FIRE)||
                        event.getSource().is(DamageTypes.ON_FIRE)||
                        event.getSource().is(DamageTypes.LAVA))
                {
                    event.setAmount(event.getAmount() * 0.2f);
                }
            }
        }
    }
    @SubscribeEvent
    public void suddenrainLLivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomswoud.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.doomswoud) ){
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(doomswoud.canFlySword)){
                                        if (!player.getCooldowns().isOnCooldown(Items.doomswoud.get())){
                                            float speed = player.getSpeed();
                                            speed/=0.1f;
                                            int a = (int) (speed - 1) * 5;
                                            a += 2;
                                            for (int p = 0;p<a;p++) {
                                                float s  = (float) Math.sin(p);
                                                if (s <= 0){
                                                    s = 0.12f;
                                                }
                                                suddenrain item = new suddenrain(EntityTs.suddenrain.get(),player.level());
                                                item.teleportTo(player.getX()+Mth.nextFloat(RandomSource.create(), -s,s),player.getY()+2+s,player.getZ()+Mth.nextFloat(RandomSource.create(), -s,s));
                                                item.setOwner(player);
                                                item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),s/1.5f,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                                                player.level().addFreshEntity(item);
                                                player.getCooldowns().addCooldown(Items.doomswoud.get(), 20);
                                            }
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
    public void suddenrainLivingDeathEvent(LivingDeathEvent event){
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomswoud.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.doomswoud) ){
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(doomswoud.canFlySword)){

                                        for (int p = 0; p < 4; p++) {
                                            float s = (float) Math.sin(p);
                                            if (s <= 0) {
                                                s = 0.12f;
                                            }
                                            suddenrain item = new suddenrain(EntityTs.suddenrain.get(), player.level());
                                            item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                                            item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f), s / 1.5f, Mth.nextFloat(RandomSource.create(), -s / 1.5f, s / 1.5f));
                                            item.setOwner(player);
                                            player.level().addFreshEntity(item);
                                            player.getCooldowns().addCooldown(Items.doomswoud.get(), 50);
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
    public void doomeyeLivingKnockBackEvent(LivingKnockBackEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.doomeye.get())){
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.doomeye) ){
                                if (stack.get(DataReg.tag) != null) {
                                    if (!stack.get(DataReg.tag).getBoolean(doomeye.canFlySword)){
                                        if (!player.getCooldowns().isOnCooldown(Items.doomeye.get())){
                                            for (int p = 0 ;p < 7 ;p++){
                                                float s  = (float) Math.sin(p);
                                                if (s <= 0){
                                                    s = 0.12f;
                                                }
                                                flysword item = new flysword(EntityTs.flysword.get(),player.level());
                                                item.teleportTo(player.getX()+Mth.nextFloat(RandomSource.create(), -s,s),player.getY()+2+s,player.getZ()+Mth.nextFloat(RandomSource.create(), -s,s));
                                                item.setDeltaMovement(Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f),s/1.5f,Mth.nextFloat(RandomSource.create(), -s/1.5f,s/1.5f));
                                                item.setNoGravity(true);
                                                item.setOwner(player);
                                                item.addTag(FlySword);
                                                player.level().addFreshEntity(item);
                                                player.getCooldowns().addCooldown(Items.doomeye.get(), 40);
                                            }
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
    public void thefruitLivingTickEvent(EntityTickEvent.Post event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent((handler)->{
                for (AttributeModifier attributeModifier : handler.getModifiers().values()){
                    if (attributeModifier.equals(thefruit.attributeModifier())){
                        player.getPersistentData().putBoolean(thefruit.thefruit,true);
                    }
                }
            });

            if (player.getPersistentData().getBoolean(thefruit.thefruit)){
                if (player.hasEffect(MobEffects.DARKNESS)) {
                    player.removeEffect(MobEffects.DARKNESS);
                }
            }

        }
    }
    @SubscribeEvent
    public void badgeofthedead(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.badgeofthedead.get())){
                if (event.getEntity()  instanceof Mob mob){
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount()*1.25f);
                    }
                }
            }
        }

    }
    @SubscribeEvent
    public void necora(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.necora.get())){
                if (Mth.nextInt(RandomSource.create(), 1, 3) == 1){
                    event.getDrops().add(new ItemEntity(event.getEntity().level() , event.getEntity().getX(), event.getEntity().getY(),event.getEntity().getZ(), new ItemStack(net.minecraft.world.item.Items.ROTTEN_FLESH)));
                }
            }
        }
    }

    @SubscribeEvent
    public void necora(LivingIncomingDamageEvent event) {
        if (event.getSource()!= null&&event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.necora.get())) {
                event.setAmount(event.getAmount() * 0.9f);

                if (event.getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount() * 0.8f);
                    }
                }

            }
        }
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.necora.get())) {
                if (event.getSource() != null && event.getSource().getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()){
                        event.setAmount(event.getAmount() / 2);
                    }
                }

                if (event.getSource().is(DamageTypes.MAGIC)) {
                    event.setAmount(event.getAmount() * 0.3f);
                }
            }
        }
    }

    @SubscribeEvent
    public void necora(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        if (Handler.hascurio(player, Items.necora.get())){
            if (event.getItemStack().is(net.minecraft.world.item.Items.ROTTEN_FLESH)){
                player.startUsingItem(event.getHand());
            }
        }
    }

    @SubscribeEvent
    public void necora(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.polyphagia.get())){
                if (event.getItem().getUseAnimation() == UseAnim.EAT){
                    player.heal(player.getMaxHealth() / 15);
                }
            }

            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()){
                            if (stack.is(Items.necora.get())&&event.getItem().getUseAnimation() == UseAnim.EAT){
                                if (event.getItem().is(net.minecraft.world.item.Items.ROTTEN_FLESH)){
                                    player.heal(player.getMaxHealth() / 20);
                                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));
                                }
                                if (Handler.hascurio(player, Items.putrefactive.get())) {
                                    player.heal(player.getMaxHealth() / 10);
                                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                                }
                            }
                        }
                    }
                }
            });

        }
    }

    @SubscribeEvent
    public void batskill(LivingIncomingDamageEvent event) {
        if (event.getSource()!= null&&event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is( Items.bloodvirus.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag.getBoolean(batskill.batskill)){
                                        Bat b1 = new Bat(EntityType.BAT, player.level());
                                        b1.setPos(event.getEntity().position());
                                        Bat b12 = new Bat(EntityType.BAT, player.level());
                                        b12.setPos(event.getEntity().position());
                                        Bat b13 = new Bat(EntityType.BAT, player.level());
                                        b13.setPos(event.getEntity().position());
                                        if (Mth.nextInt(RandomSource.create(), 1, 7) == 1){
                                            player.level().addFreshEntity(b1);
                                            player.level().addFreshEntity(b12);
                                            player.level().addFreshEntity(b13);
                                        }
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is( Items.bloodvirus.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag.getBoolean(batskill.batskill)){
                                        Vec3 playerPos = player.position();
                                        int range = 12;
                                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                                        int integers = 0;
                                        for (LivingEntity living : entities) {
                                            if (living instanceof Bat){
                                                integers++;
                                            }
                                        }
                                        float integer = integers;
                                        integer /= 20;
                                        if (integer > 0.8){
                                            integer = 0.8f;
                                        }
                                        event.setAmount(event.getAmount()*(1-integer));
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public void bloodvirusLiving(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.bloodvirus.get())){
                event.setStrength(event.getStrength() * 2);
            }
        }

    }
    @SubscribeEvent
    public void bloodvirusLiving(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.bloodvirus.get())){
                event.setAmount(event.getAmount() * 0.7f);
            }
        }

    }
    @SubscribeEvent
    public void bloodvirusLiving(LivingIncomingDamageEvent event) {
        if (event.getSource()!= null) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (Handler.hascurio(player, Items.bloodvirus.get())) {
                    if (player.level().isDay()) {
                        if (player.level().canSeeSkyFromBelowWater(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()))) {
                            event.setAmount(event.getAmount() * 0.25f);
                        }
                    }


                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 2, 0.33, 0.33, 0.33, 0);
                        serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, event.getEntity().getX(), event.getEntity().getY()+1, event.getEntity().getZ(), 2, 0.33, 0.33, 0.33, 0);
                    }
                    if (Handler.hascurio(player,  Items.bloodvirus.get())) {
                        float j = event.getAmount() * 0.1f;
                        if (j>5){
                            j=5;
                        }
                        player.heal(j);
                        if (event.getEntity() instanceof Mob mob) {
                            if (!mob.isInvertedHealAndHarm()) {
                                event.setAmount(event.getAmount() * 1.25f);
                            }
                        }
                        if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                            float s = event.getAmount() * 0.2f;
                            if (s>5){
                                s=5;
                            }
                            player.heal(s);
                            event.getEntity().hurt(event.getSource(), 8);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void blueamout(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.blueamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.BLUE_WOOL.defaultBlockState()));
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.blueamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.BLUE_WOOL.defaultBlockState()));
                }
            }
        }

    }
    @SubscribeEvent
    public void redamout(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.redamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    event.getEntity().knockback(0.2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.RED_WOOL.defaultBlockState()));

                }
            }
        }
    }
    @SubscribeEvent
    public void greedamout(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.greedamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));

                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.greedamout.get())) {
                if (Mth.nextInt(RandomSource.create(), 1, 8) == 1) {
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.GREEN_WOOL.defaultBlockState()));

                    player.heal(4);
                }
            }
        }

    }

    @SubscribeEvent
    public void maxamout(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.maxamout.get())) {
                if (event.getSource().getEntity() != null) {
                    if (event.getSource().getEntity() instanceof LivingEntity living) {
                        if (event.getSource().getEntity() != null) {
                            float s  = 0.2f;
                            if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                                s*=5f;
                            }

                            living.hurt(living.damageSources().magic(), event.getAmount() * s);
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL, 1F, 1F);

                        }
                    }
                }

                event.setAmount(event.getAmount() * 0.85f);
                float s  = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    s*=2;
                }

                if (Mth.nextInt(RandomSource.create(),1, (int) (5/s)) == 1){
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20, (int) (2+s)));
                }
            }
        }
        if (event.getSource().getDirectEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.maxamout.get())) {
                float w  = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    w*=2;
                }
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, (int) (0+w)));
                float s =event.getAmount() / 20;
                if (s>5){
                    s=5;
                }
                player.heal(s);
                float ss  = 1;
                if (Handler.hascurio(player, Items.nightmare_base_stone_meet.get())) {
                    ss*=2;
                }
                if (Mth.nextInt(RandomSource.create(), 1, (int) (12/ss)) == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, (int) (0+ss)));
                    event.getEntity().knockback(0.2, Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float) Math.PI / 180F)));
                    event.getEntity().level().levelEvent(2001, new BlockPos((int) event.getEntity().getX(), (int) (event.getEntity().getY() + 1), (int) event.getEntity().getZ()), Block.getId(Blocks.YELLOW_WOOL.defaultBlockState()));
                }
            }
        }
    }

    @SubscribeEvent
    public  void reanimation(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.reanimation.get())){
                if (!player.getCooldowns().isOnCooldown(Items.reanimation.get())) {
                    if (event.getAmount() > player.getHealth()) {
                        player.heal(player.getMaxHealth() / 2);
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_DEATH, SoundSource.NEUTRAL, 0.8F, 0.8F);

                        player.getCooldowns().addCooldown(Items.reanimation.get(), 3000);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public  void masticatory(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.masticatory.get())){
                if (event.getItem().getUseAnimation() == UseAnim.EAT){
                    event.setDuration(event.getDuration() / 2);
                }
            }
        }

    }
    @SubscribeEvent
    public void calcification(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.calcification.get())){
                event.setAmount(event.getAmount() * 0.89f);
            }
        }

    }
    @SubscribeEvent
    public  void die_KILL(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    if (event.getEntity() instanceof EnderDragon) {
                                        if (tag.getBoolean(blood_eat) &&
                                                tag.getBoolean(blood_hurt) &&
                                                tag.getBoolean(blood_jump) &&
                                                tag.getBoolean(blood_spawn) &&
                                                tag.getBoolean(blood_enchant)) {
                                            player.addItem(new ItemStack(Items.catalyzer.get()));
                                        }
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    public static String jump_size = "jump_size";
    public static String hurt_size ="hurt_size";
    public static String apple = "apple";

    public static String spawn= "spawn";
    public static String enchant= "enchant";

    public static final String blood_hurt = "blood_hurt";
    public static  final String blood_jump = "blood_jump";
    public static  final String blood_eat = "blood_eat";
    public static  final String blood_spawn = "blood_spawn";
    public static  final String blood_enchant = "blood_enchant";

    @SubscribeEvent
    public  void die(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        if (stack.is(Items.medicinebox.get())) {
                            CompoundTag tag = stack.get(DataReg.tag);
                            if (tag != null) {
                                if (!tag.getBoolean(spawn)) {
                                    player.addItem(new ItemStack(Items.reanimation.get()));
                                    tag.putBoolean(spawn, true);
                                    tag.putBoolean(blood_spawn, true);
                                }
                            }else {
                                stack.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                }
            }
        });

    }
    @SubscribeEvent
    public  void apple(LivingEntityUseItemEvent.Finish event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                ItemStack a = event.getItem();
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (a.is(net.minecraft.world.item.Items.GOLDEN_APPLE)) {

                                        if (tag.getInt(apple) < 9) {
                                            tag.putInt(apple, tag.getInt(apple) + 1);
                                        }
                                        if (tag.getInt(apple) == 8) {
                                            player.addItem(new ItemStack(Items.masticatory.get()));
                                            tag.putBoolean(blood_eat, true);
                                        }

                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public  void medicinebox(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag != null && tag.getInt(hurt_size) < 351) {
                                        tag.putInt(hurt_size, tag.getInt(hurt_size) + 1);
                                    }
                                    if (tag != null && tag.getInt(hurt_size) == 350) {
                                        player.addItem(new ItemStack(Items.calcification.get()));
                                        tag.putBoolean(blood_hurt, true);
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void medicinebox(LivingEvent.LivingJumpEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag != null && tag.getInt(jump_size) < 501) {
                                        tag.putInt(jump_size, tag.getInt(jump_size) + 1);

                                    }
                                    if (tag != null && tag.getInt(jump_size) == 500) {
                                        player.addItem(new ItemStack(Items.quadriceps.get()));
                                        tag.putBoolean(blood_jump, true);

                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void medicinebox(LivingEntityUseItemEvent.Finish event) {

        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.medicinebox.get())) {
                                ItemStack a = event.getItem();
                                if (a.is(net.minecraft.world.item.Items.ENCHANTED_GOLDEN_APPLE)) {
                                    CompoundTag tag = stack.get(DataReg.tag);
                                    if (tag != null) {
                                        ;
                                        if (!tag.getBoolean(enchant)) {
                                            player.addItem(new ItemStack(Items.polyphagia.get()));
                                            tag.putBoolean(enchant, true);
                                            tag.putBoolean(blood_enchant, true);

                                        }
                                    }else {
                                        stack.set(DataReg.tag,new CompoundTag());
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void hurt(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.ragegene.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    if (player.getAttackStrengthScale(1) == 1) {
                                        if (tag != null && tag.getInt(rage) < 100) {
                                            tag.putFloat(rage, tag.getInt(rage) + 5);
                                        }
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.ragegene.get())) {

                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    tag.putFloat(rage,0);
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void bloodgene(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.bloodgene.get())) {
                                event.setAmount(event.getAmount() * 1.1f);
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag != null && tag.getInt(blood) < 150) {
                                        tag.putFloat(blood, tag.getInt(blood) + 5);
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void bloodgene(LivingHealEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            if (stack.is(Items.bloodgene.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    if (tag != null && tag.getInt(blood) > 0) {
                                        tag.putFloat(blood, tag.getInt(blood) - 1);
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void virus(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.getItem() instanceof com.moonstone.moonstonemod.item.plague.ALL.virus) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    String name = event.getEntity().getEncodeId();
                                    if (name != null) {
                                        int size = tag.getInt(name);
                                        float Do = (float) size / 400;
                                        event.setAmount(event.getAmount() * (1 + Do));
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }

                        }
                    }
                });
            }

        }
    }
    @SubscribeEvent
    public  void virus(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.virus.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.virus.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                    String name = event.getEntity().getEncodeId();
                                    if (name != null) {
                                        if (tag.getInt(name) < 400) {
                                            tag.putInt(name, tag.getInt(name) + 1);

                                            if (!player.level().isClientSide) {
                                                player.displayClientMessage(Component.translatable("allevent.moonstone.virus").append(name).withStyle(ChatFormatting.RED), true);
                                            }
                                        }
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }

                        }
                    }
                });
            }

        }

    }
    @SubscribeEvent
    public  void parasite(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.parasite.get())) {
                            CompoundTag tag = stack.get(DataReg.tag);
                            if (tag != null) {
                                ;
                                if (Handler.hascurio(player, Items.parasite.get())) {
                                    if (Handler.hascurio(player, Items.parasite.get())) {
                                        if (event.getItem().getUseAnimation() == UseAnim.EAT) {
                                            if (event.getItem().getFoodProperties(player) != null) {
                                                int siz = (int) (event.getItem().getFoodProperties(player).nutrition() + event.getItem().getFoodProperties(player).saturation());
                                                if (tag.getInt(lvl_parasite) <= 1) {
                                                    siz /= 2;
                                                    tag.putInt(sizeLevel, tag.getInt(sizeLevel) + siz);

                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                                                    }
                                                }
                                                if (tag.getInt(lvl_parasite) <= 2 && tag.getInt(lvl_parasite) > 1) {
                                                    siz /= 3;
                                                    tag.putInt(sizeLevel, tag.getInt(sizeLevel) + siz);
                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 1);
                                                    }
                                                }
                                                if (tag.getInt(lvl_parasite) <= 3 && tag.getInt(lvl_parasite) > 2) {
                                                    siz /= 4;
                                                    tag.putInt(sizeLevel, tag.getInt(sizeLevel) + siz);
                                                    if (player.getFoodData().getFoodLevel() < 19) {
                                                        player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                                                        player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 1);
                                                    }
                                                }
                                                if (!player.level().isClientSide) {
                                                    player.displayClientMessage(Component.translatable("寄生虫增长了" + (siz) + "克").withStyle(ChatFormatting.RED), true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }else {
                                stack.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void germ(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        if (Handler.hascurio(player, Items.germ.get())) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        {
                            CompoundTag tag = stack.get(DataReg.tag);
                            if (tag != null) {
                                ;

                                int s = 250;
                                tag.putInt(lvlSize,  tag.getInt(lvlSize)+1);
                                if (tag.getInt(lvlSize) > s && tag.getInt(lvlSize) < s*2){
                                    if (tag.getInt(lvl)!= 1) {
                                        tag.putInt(lvl, 1);
                                    }
                                }
                                if (tag.getInt(lvlSize) >  s*2 && tag.getInt(lvlSize) <  s*3){
                                    if (tag.getInt(lvl)!= 2) {
                                        tag.putInt(lvl, 2);
                                    }
                                }
                                if (tag.getInt(lvlSize) >  s*3){
                                    if (tag.getInt(lvl)!= 3) {
                                        tag.putInt(lvl, 3);
                                    }
                                }
                            }else {
                                stack.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void fungus(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fungus.get())){
                Vec3 playerPos = player.position().add(0, 0.75, 0);
                int range = 10;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                int integers = 0;
                for (LivingEntity living : entities) {
                    if (fungus_boolean(living,player)){
                        integers++;
                    }
                }
                float integer = integers;
                integer /= 10;
                integer *= 2.5f;
                if (integer > 1.5f){
                    integer = 1.5f;
                }
                aFloat = integer;
                event.setAmount(event.getAmount() *(1+integer));

            }
        }
    }
    @SubscribeEvent
    public  void fungus(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.fungus.get())){
                LivingEntity living =event.getEntity();
                BlockState state = living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY() - 1, living.getBlockZ()));
                if (state.is(Blocks.GRASS_BLOCK)) {
                    living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY() - 1, living.getBlockZ()), Blocks.MYCELIUM.defaultBlockState(), 3);
                }
                BlockState MYC = living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()));


                if (!player.getCooldowns().isOnCooldown(Items.fungus.get())) {
                    player.getCooldowns().addCooldown(Items.fungus.get(),200);
                }

                if (MYC.is(Blocks.AIR)) {
                    if (living.level().getBlockState(new BlockPos(living.getBlockX(), living.getBlockY()-1, living.getBlockZ())).is(Blocks.MYCELIUM)) {
                        if (Mth.nextInt(RandomSource.create(), 1, 2) == 1) {
                            living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()), Blocks.RED_MUSHROOM.defaultBlockState(), 3);
                        } else {
                            living.level().setBlock(new BlockPos(living.getBlockX(), living.getBlockY(), living.getBlockZ()), Blocks.BROWN_MUSHROOM.defaultBlockState(), 3);
                        }
                    }
                }

            }
        }

    }

    @SubscribeEvent
    public void dna(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,  Items.dna.get())) {
                if (event.getEntity() instanceof MushroomCow) {
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof dna) {
                                    if (player.hasEffect(MobEffects.WEAKNESS)) {
                                        CompoundTag tag = stack.get(DataReg.tag);
                                        if (tag != null) {
                                            ;
                                            if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                                                if (tag != null && !tag.getBoolean(fungus)) {
                                                    player.addItem(new ItemStack(Items.fungus.get()));
                                                    tag.putBoolean(fungus, true);
                                                }
                                            }
                                        }else {
                                            stack.set(DataReg.tag,new CompoundTag());
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
    public void LivingEntityUseItemEvent(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.dna.get())&&!Handler.hascurio(player, Items.parasite.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);

                            CompoundTag tag = stack.get(DataReg.tag);
                            if (tag != null) {
                                ;
                                if (tag.getBoolean(cod)&&
                                        tag.getBoolean(SALMON)&&
                                        tag.getBoolean(CHICKEN)&&
                                        tag.getBoolean(BEEF)&&
                                        tag.getBoolean(RABBIT)&&
                                        tag.getBoolean(MUTTON)&&
                                        tag.getBoolean(PORKCHOP)&&
                                        tag.getBoolean(TROPICAL_FISH))
                                {
                                    if (!tag.getBoolean(give)){
                                        player.addItem(new ItemStack(Items.parasite.get()));
                                        tag.putBoolean(give, true);
                                    }
                                }
                                if (tag.getBoolean(give)){
                                    return;
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.COD)){
                                    tag.putBoolean(cod, true);
                                }

                                if (event.getItem().is(net.minecraft.world.item.Items.SALMON)){
                                    tag.putBoolean(SALMON, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.CHICKEN)){
                                    tag.putBoolean(CHICKEN, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.BEEF)){
                                    tag.putBoolean(BEEF, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.RABBIT)){
                                    tag.putBoolean(RABBIT, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.MUTTON)){
                                    tag.putBoolean(MUTTON, true);
                                }

                                if (event.getItem().is(net.minecraft.world.item.Items.PORKCHOP)){
                                    tag.putBoolean(PORKCHOP, true);
                                }
                                if (event.getItem().is(net.minecraft.world.item.Items.TROPICAL_FISH)){
                                    tag.putBoolean(TROPICAL_FISH, true);
                                }
                            }else {
                                stack.set(DataReg.tag,new CompoundTag());
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public  void LivingIncomingDamageEvent_DNA(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (event.getSource()!= null && event.getSource().getEntity() instanceof WitherSkeleton){
                if (Handler.hascurio(player, Items.dna.get())){
                    CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                        Map<String, ICurioStacksHandler> curios = handler.getCurios();
                        for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                            ICurioStacksHandler stacksHandler = entry.getValue();
                            IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                ItemStack stack = stackHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof dna) {
                                    if (player.hasEffect(MobEffects.WEAKNESS)) {
                                        if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
                                            CompoundTag tag = stack.get(DataReg.tag);
                                            if (tag != null) {
                                                ;
                                                if (tag != null && tag.getString(virus).isEmpty()) {
                                                    player.addItem(new ItemStack(Items.virus.get()));
                                                    tag.putString(virus, virus);
                                                }
                                            }else {
                                                stack.set(DataReg.tag,new CompoundTag());
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
    }
    @SubscribeEvent
    public void evilmug(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            ItemStack stack = player.getItemInHand(InteractionHand.OFF_HAND);
            if (stack.is(Items.evilmug.get())){
                CompoundTag tag = stack.get(DataReg.tag);
                if (tag != null) {
                    ;
                    if (tag.getInt(evilmug.blood) < 100) {
                        int s = (int) event.getAmount();
                        if (s > 100) {
                            s = 100;
                        }
                        if (tag != null) {
                            tag.putInt(evilmug.blood, tag.getInt(evilmug.blood) + s);
                        }
                    }
                }else {
                    stack.set(DataReg.tag,new CompoundTag());
                }
            }
        }
    }

    @SubscribeEvent
    public   void evilcandle(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.evilcandle.get())){
                if (event.getSource().is(DamageTypes.IN_FIRE)&&event.getSource().is(DamageTypes.ON_FIRE)&&event.getSource().is(DamageTypes.LAVA)){
                    event.setAmount(event.getAmount() * 0.2f);
                }
            }
        }
    }

    @SubscribeEvent
    public  void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.getTags().contains("welcome_to_moonstone")) {
            int a = Mth.nextInt(RandomSource.create(), 1, 5);
            if (a == 1) {
                player.addItem(Items.run_dna.get().getDefaultInstance());
            }
            if (a == 2) {
                player.addItem(Items.health_dna.get().getDefaultInstance());
            }
            if (a == 3) {
                player.addItem(Items.neuron_dna.get().getDefaultInstance());
            }
            if (a == 4) {
                player.addItem(Items.eye_dna.get().getDefaultInstance());
            }
            if (a == 5) {
                player.addItem(Items.skin_dna.get().getDefaultInstance());
            }
            player.addItem(Items.deceased_contract.get().getDefaultInstance());
            player.addTag("welcome_to_moonstone");
        }
        if (Config.SERVER.giveBook.get()) {
            if (!player.getTags().contains("give_moonstone_item_book")) {
                player.addItem(Items.book.get().getDefaultInstance());
                player.addTag("give_moonstone_item_book");
            }
        }
        if (!player.getTags().contains("nightmare")) {
            player.addItem(Items.nightmare_base.get().getDefaultInstance());
            player.addTag("nightmare");
        }
    }
    @SubscribeEvent
    public void KnockBack_nightmarestone(LivingKnockBackEvent  event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setStrength(event.getStrength() * (1 - EffectInstance(player) / 14));
            }

            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    CompoundTag tag = stack.get(DataReg.tag);
                                    if (tag != null) {
                                        ;
                                        float dam =tag.getInt("kok");
                                        dam /= 100;
                                        event.setStrength(event.getStrength() * (1 - dam));
                                    }else {
                                        stack.set(DataReg.tag,new CompoundTag());
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
    public void Heal_nightmarestone(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setAmount(event.getAmount() * (1 + EffectInstance(player) / 14));
            }
        }

    }

    @SubscribeEvent
    public void exp_nightmarestone(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setDroppedExperience((int) (event.getDroppedExperience() * (1 + EffectInstance(player) / 10)));
            }
        }
    }
    @SubscribeEvent
    public void BreakSpeed_nightmarestone(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() != null){
            Player player = event.getEntity();
            if (Handler.hascurio(player, Items.nightmarestone.get())){
                if (Handler.hascurio(player, Items.nightmareeye.get()))
                    event.setNewSpeed(event.getNewSpeed() * (1 + EffectInstance(player) / 10));
            }
        }
    }
    @SubscribeEvent
    public void nightmareeye(LivingIncomingDamageEvent event) {

        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 1.33f);
            }

        }
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player,Items.nightmareeye.get())) {
                event.setAmount(event.getAmount() * 0.75f);
            }
        }

    }

    @SubscribeEvent
    public void nightmareanchor(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (stack.is(Items.nightmareanchor.get())) {
                                CompoundTag tag = stack.get(DataReg.tag);
                                if (tag != null) {
                                    ;
                                   {

                                        double playerX = player.getX();
                                        double playerY = player.getY();
                                        double playerZ = player.getZ();

                                        tag.putDouble("x", playerX);
                                        tag.putDouble("y", playerY);
                                        tag.putDouble("z", playerZ);


                                        tag.putString("level", player.level().dimension().toString());
                                    }
                                }else {
                                    stack.set(DataReg.tag,new CompoundTag());
                                }
                            }
                        }
                    }
                });

            }
        }
    }
    @SubscribeEvent
    public void LivingKnockBackEvent(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.mring.get())){
                event.setStrength(event.getStrength() * 2);
            }
        }
    }

    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (Handler.hascurio(player, Items.morb.get())){
            event.setDroppedExperience(((int) ((event.getDroppedExperience()*1.5))) + 1);
        }
    }
    @SubscribeEvent
    public void nightmaretreasure_LivingDropsEvent(LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (Handler.hascurio(player, Items.nightmareeye.get())) {
                if (Handler.hascurio(player,  Items.nightmaretreasure.get())) {
                    Random random = new Random();
                    int s = 33;
                    s += (int) player.getLuck()*3;

                    if (s > 99){
                        s = 99;
                    }
                    if(
                            random.nextInt(100) <= s
                    ) {
                        Collection<ItemEntity> drop = event.getDrops();
                        for (ItemEntity entity : drop) {
                            ItemStack stack = entity.getItem();
                            if (stack.getMaxStackSize() != 1) {
                                stack.setCount(stack.getCount() * 3);
                                entity.setItem(stack);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void nightmarestone(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmarestone.get())) {
                int s = Mth.nextInt(RandomSource.create(), 1, 6);
                if (s == 1)
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
                if (s == 2)
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                if (s == 3)
                    player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 0));
                if (s == 4)
                    player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                if (s == 5)
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2));
                if (s == 6)
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
            }
        }
    }
    @SubscribeEvent
    public void LivingIncomingDamageEvent(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.thedoomstone.get())) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DARKNESS ,120 ,1));

                Vec3 playerPos = event.getEntity().position();
                int range = 12;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));


                for (LivingEntity livingentity : entities) {
                    if (livingentity.hasEffect(MobEffects.DARKNESS )) {
                        if (!livingentity.is(player)) {
                            {
                                if (clientSideAttackTime < 80) {
                                    ++clientSideAttackTime;
                                }
                                double d5 = this.getAttackAnimationScale(0.0F);
                                double d0 = livingentity.getX() - event.getEntity().getX();
                                double d1 = livingentity.getY(0.5D) - event.getEntity().getEyeY();
                                double d2 = livingentity.getZ() - event.getEntity().getZ();
                                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                                d0 /= d3;
                                d1 /= d3;
                                d2 /= d3;
                                double d4 = event.getEntity().getRandom().nextDouble();
                                while (d4 < d3) {
                                    d4 += 1.8D - d5 + event.getEntity().getRandom().nextDouble() * (1.7D - d5);

                                    if (event.getEntity().level() instanceof ServerLevel serverLevel) {
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                        serverLevel.sendParticles(ParticleTypes.UNDERWATER, event.getEntity().getX() + d0 * d4, event.getEntity().getEyeY() + d1 * d4, event.getEntity().getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
                                    }
                                }
                                livingentity.level().playSound(null, livingentity.getX(), livingentity.getY(), livingentity.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.NEUTRAL, 0.22f, 0.22f);
                                livingentity.hurt(livingentity.damageSources().magic(), 10);
                                livingentity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                                livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));

                            }
                        }
                    }
                }
            }

            if (Handler.hascurio(player, Items.magiceye.get())) {
                Vec3 playerPos = event.getEntity().position().add(0, 0.75, 0);
                int range = 4;
                List<LivingEntity> entities = event.getEntity().level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (!(living instanceof Player)) {
                        if (!living.is(event.getEntity())) {
                            living.hurt(living.damageSources().magic(), event.getAmount() / 2);
                        }
                    }
                }
            }

            if (Handler.hascurio(player, Items.mbattery.get())) {
                if (event.getEntity() instanceof Zombie) {
                    event.setAmount(event.getAmount() * 1.5f);
                }
            }
            if (Handler.hascurio(player, Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    CompoundTag tag = stack.get(DataReg.tag);
                                    if (tag != null) {
                                        ;
                                        float dam = tag.getInt("damage");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }else {
                                        stack.set(DataReg.tag,new CompoundTag());
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }


















        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.mkidney.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.mkidney.get())) {
                    if (Mth.nextInt(RandomSource.create(), 0, 100) < Kidney) {
                        Kidney /= 2;
                        event.setAmount(0);
                    } else {
                        Kidney = 100;

                        event.setAmount(event.getAmount() + player.getMaxHealth() / 3);
                         player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_DEACTIVATE, SoundSource.NEUTRAL, 1, 1);
                        player.getCooldowns().addCooldown(Items.mkidney.get(), 200);
                    }
                }
            }



            if (Handler.hascurio(player,Items.magicstone.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                if (stack.is(Items.magicstone.get())) {
                                    CompoundTag tag = stack.get(DataReg.tag);
                                    if (tag != null) {
                                        ;
                                        float dam =tag.getInt("regs");
                                        dam /= 100;
                                        event.setAmount(event.getAmount() * (1 + dam));
                                    }else {
                                        stack.set(DataReg.tag,new CompoundTag());
                                    }
                                }
                            }
                        }
                    }
                });
            }
            if (Handler.hascurio(player, Items.mshell.get())) {
                if (event.getSource().getEntity() instanceof Mob mob) {
                    if (mob.isInvertedHealAndHarm()) {
                        event.setAmount(event.getAmount() * 0.75f);
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmapple.get())) {
                if (event.getSource() != null) {
                    Entity source = event.getSource().getEntity();
                    if (source instanceof LivingEntity) {
                        Vec3 playerPos = player.position();
                        int range = 8;
                        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(
                                playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range));
                        for (LivingEntity livingEntity : entities) {
                            if (!livingEntity.is(player)) {
                                livingEntity.hurt(livingEntity.damageSources().magic(), livingEntity.getMaxHealth() / 50);
                            }
                        }
                    }
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmhorseshoe.get())) {
                if (event.getSource().is(DamageTypes.FALL)) {
                    event.setAmount(event.getAmount() / 10);
                }
            }
            if (Handler.hascurio(player, Items.ectoplasmshild.get())) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setAmount(event.getAmount() * 0.7F);
                }
                shield += 1;
                if (shield >= 5) {
                    event.setAmount(0);
                    shield = 0;
                }
            }
        }
    }
    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Tick event){
        if (event.getEntity() instanceof Player player){
            ItemStack stack = event.getItem();
            CompoundTag tag = stack.get(DataReg.tag);
            if (tag != null) {
                ;
                if (tag.getBoolean(wind_and_rain.wind)) {
                    float s = (float) Math.sin(player.tickCount);
                    if (s <= 0) {
                        s = 0.125f;
                    }

                    int b = Mth.nextInt(RandomSource.create(), 1, 50);
                    if (player.tickCount % 5 == 0) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }


                        suddenrain item = new suddenrain(EntityTs.suddenrain.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        item.setOwner(player);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);

                            stack.hurtAndBreak(3, player, event.getEntity().getEquipmentSlotForItem(stack));
                        }

                    } else if (player.tickCount % 5 == 1) {
                        if (b == 1) {
                            player.getCooldowns().addCooldown(stack.getItem(), 160);
                        }
                        flysword item = new flysword(EntityTs.flysword.get(), player.level());
                        item.teleportTo(player.getX() + Mth.nextFloat(RandomSource.create(), -s, s), player.getY() + 2 + s, player.getZ() + Mth.nextFloat(RandomSource.create(), -s, s));
                        item.setDeltaMovement(0, s / 1.5f, 0);
                        item.setOwner(player);
                        item.addTag(AllEvent.FlySword);
                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                            player.level().addFreshEntity(item);
                            stack.hurtAndBreak(3, player, event.getEntity().getEquipmentSlotForItem(stack));
                        }
                    }

                }

            }
        }
    }
    @SubscribeEvent
    public void SwordEventLivingEntityUseItemEvent(ItemTooltipEvent event){


        ItemStack stack = event.getItemStack();
        CompoundTag tag = stack.get(DataReg.tag);

        if (tag != null) {
            if (tag.getBoolean(wind_and_rain.wind)) {
                event.getToolTip().add(Component.translatable("item.moonstone.wind_and_rain").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();
        if (stack.getItem() instanceof IEctoplasm) {
            tooltipEvent.setBorderStart(0xFF87CEFA);
            tooltipEvent.setBorderEnd(0xFFF8F8FF);
        }
        if (stack.getItem() instanceof MLS) {
            tooltipEvent.setBorderStart(0xFF006400);
            tooltipEvent.setBorderEnd(0xFF006400);
        }
        if (stack.getItem() instanceof INightmare) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0xFF800080);

            tooltipEvent.setBackgroundStart(0x00000000);
            tooltipEvent.setBackgroundEnd(0x00000000);
        }
        if (stack.getItem() instanceof IDoom ||stack.getItem() instanceof Perhaps) {
            tooltipEvent.setBorderStart(0xFF83DEFC);
            tooltipEvent.setBorderEnd(0xFF0296FE);

            tooltipEvent.setBackgroundStart(0x00000000);
            tooltipEvent.setBackgroundEnd(0x00000000);

        }

        if (stack.getItem() instanceof Die) {
            tooltipEvent.setBorderStart(0x0ff9C9C9C);
            tooltipEvent.setBorderEnd(0x0ff9C9C9C);
            tooltipEvent.setBackgroundStart(0xFF000000);
            tooltipEvent.setBackgroundEnd(0xFF000000);
        }

        if (stack.getItem() instanceof RAGE) {
            tooltipEvent.setBorderStart(0xFFFFDEAD);
            tooltipEvent.setBorderEnd(0xFFB22222);
            tooltipEvent.setBackgroundStart(0xFF000000);
            tooltipEvent.setBackgroundEnd(0xFF000000);
        }
        if (stack.getItem() instanceof the_heart) {
            tooltipEvent.setBorderStart(0xFFFF8C00);
            tooltipEvent.setBorderEnd(0xFFFFD700);
        }

        if (stack.getItem() instanceof ManDNA) {
            tooltipEvent.setBorderStart(0xFFafeeee);
            tooltipEvent.setBorderEnd(0xFFafeeee);

            tooltipEvent.setBackgroundStart(0x59000000);
            tooltipEvent.setBackgroundEnd(0x59000000);
        }

        if (stack.getItem() instanceof Iplague ||stack.getItem() instanceof Blood) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0x0ff800000);
            tooltipEvent.setBackgroundStart(0x00000000);
            tooltipEvent.setBackgroundEnd(0x00000000);
        }
    }
    public static boolean fungus_boolean(LivingEntity living,Player player){
        if (!living.is(player)) {
            if (living.isAlliedTo(player)) {
                return true;
            }
        }
        if (living instanceof OwnableEntity ownableEntity){
            if (ownableEntity.getOwner()!= null&&ownableEntity.getOwner().is(player)){
                return true;
            }
        }
        return false;
    }

    public static float EffectInstance(Player player) {
        float size = 0;
        List<Integer> Int = Lists.newArrayList();
        Collection<MobEffectInstance> collection = player.getActiveEffects();
        for (MobEffectInstance mobEffectInstance : collection){
            MobEffect mobEffect = mobEffectInstance.getEffect().value();
            if (!mobEffect.isBeneficial()){
                Int.add(1);
            }
        }
        for (Integer i : Int){
            size += 1;
        }
        return size;
    }
    public float getAttackAnimationScale(float p_32813_) {
        return (clientSideAttackTime + p_32813_) / (float)80;
    }
}

