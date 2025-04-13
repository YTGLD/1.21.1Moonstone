package com.moonstone.moonstonemod.event.old;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.book;
import com.moonstone.moonstonemod.entity.zombie.sword_soul;
import com.moonstone.moonstonemod.event.loot.DungeonLoot;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.*;
import com.moonstone.moonstonemod.init.moonstoneitem.i.IBattery;
import com.moonstone.moonstonemod.item.blood.*;
import com.moonstone.moonstonemod.item.blood.magic.blood_magic_box;
import com.moonstone.moonstonemod.item.blood.magic.blood_sun;
import com.moonstone.moonstonemod.item.blood.magic.rage_blood_head;
import com.moonstone.moonstonemod.item.blood.magic.undead_blood_charm;
import com.moonstone.moonstonemod.item.coffin;
import com.moonstone.moonstonemod.item.decorated.deceased_contract;
import com.moonstone.moonstonemod.item.man.*;
import com.moonstone.moonstonemod.item.maxitem.book.nine_sword_book;
import com.moonstone.moonstonemod.item.maxitem.*;
import com.moonstone.moonstonemod.item.maxitem.rage.*;
import com.moonstone.moonstonemod.item.nanodoom.as_amout;
import com.moonstone.moonstonemod.item.nanodoom.million;
import com.moonstone.moonstonemod.item.nightmare.*;
import com.moonstone.moonstonemod.item.nightmare.super_nightmare.*;
import com.moonstone.moonstonemod.item.plague.ALL.dna;
import com.moonstone.moonstonemod.item.plague.BloodVirus.dna.bat_cell;
import com.moonstone.moonstonemod.item.plague.TheNecora.bnabush.giant_boom_cell;
import com.moonstone.moonstonemod.item.plague.TheNecora.god.GodAmbush;
import com.moonstone.moonstonemod.item.plague.TheNecora.god.GodPutrefactive;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void LivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        GodAmbush.LivingIncomingDamageEvent(event);
    }
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
                            if (stack.get(DataReg.tag).getBoolean(EquippedEvt.isGod)){
                                event.setAmount(event.getAmount()+0.33f);
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public  void FinishLivingEntityUseItemEvent(LivingEntityUseItemEvent.Finish event){
        GodPutrefactive.eat(event);
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
        nightmare_base_reversal_orb.LivingHealEvent(event);
        nightmare_orb.nightmare_orb_heal(event);
         nightmare_head.LivingHealEvent(event);
        Enchants.threatHeal(event);
        DungeonLoot.heal(event);
        nightmare_base_black_eye_heart.heal(event);
        nightmare_axe.heals(event);
        undead_blood_charm.LivingHealEvent(event);
        rage_lock.LivingHealEvent(event);
        pain_ring.Heal(event);
        pain_candle.Heal(event);
        health_dna.lymphatic(event);
        copy_dna.formative_cell(event);
        health_dna.stem_cell_proliferation(event);
        if (event.getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.heal)!=null){
                float attack = (float) living.getAttribute(AttReg.heal).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }
    }
    @SubscribeEvent
    public void pick(ItemEntityPickupEvent.Post event){
        rage_magnet.pick(event);
    }
    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_heart.Nig(event);
        nightmare_base_reversal.LivingDeathEvent(event);
        nightmare_head.LivingDeathEvent(event);
        max_eye.Die(event);
        blood_snake.Die(event);
        max_sword.die(event);
        the_prison_of_sin.LivingDeathEvent(event);
        blood_magic_box.Did(event);
        deceased_contract.Did(event);
        blood_sun.Did(event);
        dna.dieD(event);
        rage_blood_head.Did(event);
        sword_soul.evil(event);
        nightmare_base_black_eye_red.kill(event);
        nightmare_base_insight_insane.LivingDeathEvents(event);
        nightmare_axe.Nig(event);
        immortal.livDead(event);
        coffin.coffins(event);
        rage_charm.die(event);
        rage_lock.LivingDeathEvent(event);
        neuron_dna.memory(event);
        the_owner_blood_stone.did(event);
    }
    public void addV(ItemStack stack,Item Dhis,ItemTooltipEvent event,String string){
        if (stack.is(Dhis)) {
            event.getToolTip().add(1,Component.translatable(string).withStyle(ChatFormatting.RED));
        }
    }
    @SubscribeEvent
    public   void neuron_dna_main(LivingDropsEvent event){
        neuron_dna.neuron_dna_main(event);
    }
    @SubscribeEvent
    public void useBow(LivingEntityUseItemEvent.Start event){
        rage_head.useBow(event);
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
        probability.att(event);
        rage_blood_head.hurt(event);
        as_amout.hurt(event);
        Enchants.LivingHurtEvent(event);
        Enchants.maliceAttack(event);
        moon_stone.LivingIncomingDamageEvent(event);
        million.hurt(event);
        nine_sword_book.att(event);
        book.hurt(event);
        nightmare_base_stone_virus.h(event);
        nightmare_base_black_eye_eye.attLook(event);
        nightmare_base_black_eye_heart.hurt(event);
        nightmare_base_stone.LivingHurtEvent(event);
        nightmare_base_stone_brain.hurts(event);
        nightmare_base_redemption_deception.LivingIncomingDamageEvent(event);
        nightmare_base_fool_bone.attLook(event);
        nightmare_base_insight_insane.damage(event);
        nightmare_base_start.damage(event);
        nightmare_base_start_pod.damage(event);
        nightmare_axe.att(event);
        immortal.hEvt(event);
        undead_blood_charm.LivingIncomingDamageEvent(event);
        rage_lock.LivingIncomingDamageEvent(event);
        pain_candle.Hurt(event);
        pain_ring.Hurt(event);
        run_dna.phosphorylationDamage(event);
        run_dna.cp_energy(event);
        run_dna.hydrolysisDamage(event);
        run_dna.run(event);
        copy_dna.connective_tissue(event);
        attack_dna.abnormal_muscles(event);
        attack_dna.reverse_correction(event);
        skin_dna.hurt_of_skin_dna(event);
        bone_dna.hollow(event);
        bone_dna.bone_spur(event);
        if (event.getEntity().hasEffect(Effects.dead) && event.getEntity().getEffect(Effects.dead)!=null){
            float lvl = event.getEntity().getEffect(Effects.dead).getAmplifier();
            lvl *= 0.2f;
            event.setAmount(event.getAmount()*(1+lvl));

        }
        if (event.getAmount()>Integer.MAX_VALUE){
            event.setAmount(Integer.MAX_VALUE);
        }
        CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MoonStoneMod.MODID)){

                        float s = event.getAmount();
                        if (s>Integer.MAX_VALUE){
                            event.setAmount(Integer.MAX_VALUE);
                        }
                    }
                }
            }
        });
        if (event.getEntity().hasEffect(Effects.fear)&&event.getEntity().getEffect(Effects.fear)!=null){
            event.setAmount(event.getAmount()*(1+(event.getEntity().getEffect(Effects.fear).getAmplifier()*0.33f)));
        }
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.alL_attack)!=null){
                float attack = (float) living.getAttribute(AttReg.alL_attack).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void RenderTooltipEven4t(RenderTooltipEvent.Color tooltipEvent){
         ItemStack stack =  tooltipEvent.getItemStack();
         if (stack.is(Items.ectoplasmstar)){
             if (stack.get(DataReg.tag)!=null){
                 if (stack.get(DataReg.tag).getBoolean(nightmare_base_stone_meet.curse)){
                     tooltipEvent.setBorderEnd(0xFFff70b2);
                     tooltipEvent.setBorderStart(0xFFff70b2);

                     tooltipEvent.setBackgroundEnd(0xFF230613);
                     tooltipEvent.setBackgroundStart(0xFF230613);
                 }
             }
         }
        if (stack.is(Items.mayhemcrystal)){
            if (stack.get(DataReg.tag)!=null){
                if (stack.get(DataReg.tag).getBoolean(nightmare_base_stone_meet.curse)){
                    tooltipEvent.setBorderEnd(0xFFff70b2);
                    tooltipEvent.setBorderStart(0xFFff70b2);

                    tooltipEvent.setBackgroundEnd(0xFF230613);
                    tooltipEvent.setBackgroundStart(0xFF230613); }
            }
        }
        if (stack.is(Items.maxamout)){
            if (stack.get(DataReg.tag)!=null){
                if (stack.get(DataReg.tag).getBoolean(nightmare_base_stone_meet.curse)){
                    tooltipEvent.setBorderEnd(0xFFff70b2);
                    tooltipEvent.setBorderStart(0xFFff70b2);

                    tooltipEvent.setBackgroundEnd(0xFF230613);
                    tooltipEvent.setBackgroundStart(0xFF230613);  }
            }
        }
    }
    @SubscribeEvent
    public void Night(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (stack.getItem() instanceof SuperNightmare){
            if (!Handler.hascurio(player,Items.nightmare_base.get())) {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name").withStyle(ChatFormatting.DARK_RED));
            }else {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.super_nightmare.name").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
            }
        }
        if (stack.getItem() instanceof Nightmare){
            if (!Handler.hascurio(player,Items.nightmareeye.get())) {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.nightmare.name").withStyle(ChatFormatting.DARK_RED));
            }else {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("moonstone.nightmare.name").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
            }
        }

        if (Handler.hascurio(player,Items.nightmare_base_stone_meet.get())){
            if (stack.is(Items.ectoplasmstar)){
                event.getToolTip().add(Component.translatable("item.moonstone.ectoplasmstar.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
            }
            if (stack.is(Items.mayhemcrystal)){
                event.getToolTip().add(Component.translatable("item.moonstone.ectoplasmstar.mayhemcrystal").withStyle(ChatFormatting.DARK_RED));

            }
            if (stack.is(Items.maxamout)){
                event.getToolTip().add(Component.translatable("item.moonstone.ectoplasmstar.maxamout").withStyle(ChatFormatting.DARK_RED));
                event.getToolTip().add(Component.translatable("item.moonstone.ectoplasmstar.maxamout.1").withStyle(ChatFormatting.DARK_RED));
                event.getToolTip().add(Component.translatable("item.moonstone.ectoplasmstar.maxamout.2").withStyle(ChatFormatting.DARK_RED));
            }
        }

    }
    @SubscribeEvent
    public void PlayerInteractEvent(PlayerInteractEvent.EntityInteract event) {
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.motor.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.watergen.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.air.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant.get(),"ncrdna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bat_cell.get(),"ncrdna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.adrenaline.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_blood.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_boom.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_calcification.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.cell_mummy.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_nightmare.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.bone_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.disgusting_cells.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.mother_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.parasitic_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.anaerobic_cell.get(),"dna");

        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.giant_boom_cell.get(),"dna");
        PlayerInteractZombie(event.getEntity(),event.getTarget(), Items.subspace_cell.get(),"dna");


    }
    public void PlayerInteractZombie(Player player, Entity target, Item doItem, String slot) {
        if (target instanceof sword_soul smallZombie){
            if (player.getMainHandItem().is(doItem)&&!player.isShiftKeyDown()){

                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            if (!Handler.hascurio(smallZombie,doItem)
                                    && stacksHandler.getIdentifier().contains(slot)) {
                                ItemStack present = stackHandler.getStackInSlot(i);
                                if (present.isEmpty()) {
                                    if (stackHandler.getStackInSlot(i).isEmpty()) {
                                        stackHandler.setStackInSlot(i, new ItemStack(doItem));
                                        player.getMainHandItem().shrink(1);
                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.NEUTRAL, 1F, 1F);
                                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.NEUTRAL, 1F, 1F);


                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
            } else if (player.getMainHandItem().isEmpty()&&player.isShiftKeyDown()) {
                CuriosApi.getCuriosInventory(smallZombie).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            if (!stackHandler.getStackInSlot(i).isEmpty()
                                    && stackHandler.getStackInSlot(i).is(doItem)
                                    && stacksHandler.getIdentifier().contains(slot))
                            {

                                player.addItem(new ItemStack(doItem));

                                stackHandler.getStackInSlot(i).shrink(1);


                                break;
                            }
                        }
                    }
                });
            }
        }
    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
        DungeonLoot.cit(event);
        attack_dna.self_correction(event);

       if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.cit)!=null){
                float attack = (float) living.getAttribute(AttReg.cit).getValue();
                event.setDamageMultiplier(event.getDamageMultiplier()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void soulbattery(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.dig)!=null){

                float dig = (float) living.getAttribute(AttReg.dig).getValue();

                event.setNewSpeed(event.getNewSpeed()*(dig));
            }
        }
    }
    @SubscribeEvent
    public void LivingExperienceDropEvent(LivingExperienceDropEvent event) {
        rage_bottle.expPickup(event);
    }
    @SubscribeEvent
    public void hurt(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.hurt)!=null){
                float hurt = (float) living.getAttribute(AttReg.hurt).getValue();
                event.setAmount(event.getAmount()*(hurt));
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
    public void heal(PlayerEvent.BreakSpeed event){
        DungeonLoot.heal(event);
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
    @SubscribeEvent
    public void Book(EntityTickEvent.Post event){
        if (event.getEntity() instanceof LivingEntity living){
            if (!Handler.hascurio(living,Items.immortal.get())) {
                if (getPlayerLookTarget(living.level(), living) != null && getPlayerLookTarget(living.level(), living).getType() == EntityTs.ytgld.get()) {
                    if (living.tickCount % 20 == 0) {

                        living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2, false, false));
                        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2, false, false));
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2, false, false));
                        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2, false, false));

                        living.addEffect(new MobEffectInstance(MobEffects.CONFUSION,100,1,true,true));
                        living.addEffect(new MobEffectInstance(Effects.dead, 100, 9, true, true));
                    }
                }
            }
        }
    }

    public static Entity getPlayerLookTarget(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
    @SubscribeEvent
    public void Book(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (player!=null) {
            if (stack.is(Items.nine_sword_book)) {
                if (!Handler.hascurio(player, Items.nine_sword_book.get())) {
                    event.getToolTip().add(1, Component.translatable("item.book.tool.string.nine_sword.not").withStyle(ChatFormatting.GOLD));
                }
            }
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack soulbook = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.nine_sword_book)) {
                            if (soulbook.get(DataReg.tag) != null && stack.get(DataReg.tag) != null) {
                                if (soulbook.get(DataReg.tag).getInt(book.nineSword) <= 300) {
                                } else {
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.14").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal(""));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.13").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.12").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal(""));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.11").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.10").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.9").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal(""));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.8").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.7").withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal(""));
                                    event.getToolTip().add(1, Component.literal("+").append(String.valueOf(1)).append(Component.translatable("item.nine_sword_book.tool.string.6")).withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 1f * (1f + stack.get(DataReg.tag).getInt(nine_sword_book.lvl) / 10f))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.5")).withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 3f * (1f + stack.get(DataReg.tag).getInt(nine_sword_book.lvl) / 10f))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.4")).withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 5f * (1f + stack.get(DataReg.tag).getInt(nine_sword_book.lvl) / 10f))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.3")).withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.literal("+").append(String.format("%.2f", 10f * (1f + stack.get(DataReg.tag).getInt(nine_sword_book.lvl) / 10f))).append("%").append(Component.translatable("item.nine_sword_book.tool.string.2")).withStyle(ChatFormatting.GOLD));
                                    event.getToolTip().add(1, Component.translatable("item.nine_sword_book.tool.string.1").withStyle(ChatFormatting.GOLD));
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    @SubscribeEvent
    public void BatteryName(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        addV(stack,DNAItems.cell_disorder.get(),event,"item.moonstone.cell_disorder.tool");
        addV(stack,DNAItems.cell_god.get(),event,"item.moonstone.cell_god.tool");
        addV(stack, DNAItems.cell_inheritance.get(),event,"item.moonstone.cell_inheritance.tool");
        addV(stack,DNAItems.cell_big_boom.get(),event,"item.moonstone.cell_big_boom.tool");
        addV(stack,DNAItems.cell_darwin.get(),event,"item.moonstone.cell_darwin.tool");
        addV(stack,DNAItems.speed_metabolism.get(),event,"item.moonstone.speed_metabolism.tool");
        addV(stack,DNAItems.cell_acid.get(),event,"item.moonstone.cell_acid.tool");
        addV(stack,DNAItems.cell_eyes.get(),event,"item.moonstone.cell_eyes.tool");
        addV(stack,DNAItems.cell_digestion.get(),event,"item.moonstone.cell_digestion.tool");
        addV(stack,DNAItems.cell_cranial.get(),event,"item.moonstone.cell_cranial.tool");
        addV(stack,DNAItems.cell_compress.get(),event,"item.moonstone.cell_compress.tool");
        addV(stack,DNAItems.cell_flu.get(),event,"item.moonstone.cell_flu.tool");
        addV(stack,DNAItems.cell_constant.get(),event,"item.moonstone.cell_constant.tool");




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

        }

    }
}
