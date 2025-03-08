package com.moonstone.moonstonemod.event.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Config;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.event.AdvancementEvt;
import com.moonstone.moonstonemod.event.BookEvt;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.items.BookItems;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.Enchants;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.LootReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class DungeonLoot extends LootModifier {
    public static final Supplier<MapCodec<DungeonLoot>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, DungeonLoot::new)));

    protected DungeonLoot(LootItemCondition[] conditionsIn) {
        super(conditionsIn);

    }
    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return LootReg.TD.get();
    }
    private void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                         Random random ,
                         Item mustHas ,
                         Entity entity ,
                         List<Item> itemList,
                         int gLvl){
        if (entity instanceof Player player ){
            if (Handler.hascurio(player,mustHas)){
                int i = random.nextInt(itemList.size());
                if (gLvl >= 100){
                    gLvl = 100;
                }
                if (Mth.nextInt(net.minecraft.util.RandomSource.create(), 1, 100) <= gLvl) {
                    generatedLoot.add(new ItemStack(itemList.get(i)));
                }
            }
        }
    }
    private void addLootHasB(ObjectArrayList<ItemStack> generatedLoot,
                             Random random ,
                             boolean a,
                             List<Item> itemList,
                             int gLvl) {
        if (a) {
            int i = random.nextInt(itemList.size());
            if (gLvl >= 100) {
                gLvl = 100;
            }
            if (Mth.nextInt(net.minecraft.util.RandomSource.create(), 1, 100) <= gLvl) {
                generatedLoot.add(new ItemStack(itemList.get(i)));
            }
        }
    }
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation s = context.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        Random random = new Random();

        if (idSting.contains("chests/")) {
            if (idSting.contains("ancient")) {
                AdvancementEvt.addLoot(generatedLoot,entity,5);
                AdvancementEvt.nightmare_base_reversal_mysteriousLOOT(generatedLoot,entity);


                addLoot(generatedLoot, random, Items.blood_candle.get(), entity, List.of(
                        Items.owner_blood_eye.get(),
                        Items.owner_blood_attack_eye.get(),
                        Items.owner_blood_speed_eye.get(),
                        Items.owner_blood_effect_eye.get(),
                        Items.owner_blood_boom_eye.get(),
                        Items.owner_blood_vex.get(),
                        Items.owner_blood_earth.get()
                ), 15);
            }
        }


        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                addLoot(generatedLoot, random, Items.bat_cell.get(), entity, List.of(
                        Items.cell_blood_attack.get(),
                        Items.cell_desecrate.get(),
                        Items.cell_doctor.get(),
                        Items.cell_fear.get(),
                        Items.cell_harvest.get(),
                        Items.cell_immortal.get(),
                        Items.cell_not_do.get(),
                        Items.cell_rage.get(),
                        Items.cell_scientist.get()
                ), Config.SERVER.bat.get());
            }
            if (idSting.contains("dungeon")) {
                AdvancementEvt.nightmare_base_start_pod(generatedLoot, entity);
            }
            if (idSting.contains("dungeon") || idSting.contains("mineshaft") || idSting.contains("city")||idSting.contains("treasure")) {
                addLoot(generatedLoot, random, Items.nightmareeye.get(), entity, List.of(
                        Items.nightmare_heart.get(),
                        Items.nightmare_orb.get(),
                        Items.nightmareanchor.get(),
                        Items.nightmarecharm.get(),
                        Items.nightmareeye.get(),
                        Items.nightmaremoai.get(),
                        Items.nightmarerotten.get(),
                        Items.nightmarestone.get(),
                        Items.nightmaretreasure.get(),
                        Items.nightmarewater.get()
                ),  Config.SERVER.night.get());

                addLoot(generatedLoot, random, Items.deceased_contract.get(), entity, List.of(

                        BookItems.bone_structure.get(),
                        BookItems.tumour.get(),
                        BookItems.organizational_regeneration.get(),
                        BookItems.mummification.get(),
                        BookItems.blood_stasis.get(),
                        BookItems.weak.get(),
                        BookItems.spore_outbreak.get(),
                        BookItems.plague_book.get(),
                        BookItems.exercise_reinforcement.get(),
                        BookItems.detect.get(),
                        BookItems.bloodstain.get()

                        ),  Config.SERVER.necora.get());

                addLoot(generatedLoot, random, Items.bloodvirus.get(), entity, List.of(
                        Items.batgene.get(),
                        Items.batskill.get(),
                        Items.bloodgene.get(),
                        Items.botton.get(),
                        Items.catalyzer.get(),
                        Items.flygene.get(),
                        Items.heathgene.get(),
                        Items.ragegene.get(),
                        Items.sleepgene.get()
                ), Config.SERVER.bat.get());

                addLoot(generatedLoot, random, Items.necora.get(), entity, List.of(
                        Items.ambush.get(),
                        Items.atpoverdose.get(),
                        Items.autolytic.get(),
                        Items.fermentation.get(),
                        Items.putrefactive.get(),
                        Items.regenerative.get(),
                        Items.air.get(),
                        Items.motor.get(),
                        Items.watergen.get()

                ), Config.SERVER.necora.get());


                if (entity instanceof Player player) {
                    boolean wind = Handler.hascurio(player, Items.doomeye.get())
                            && Handler.hascurio(player, Items.doomswoud.get());
                    addLootHasB(generatedLoot, random, wind, List.of(
                            Items.wind_and_rain.get()
                    ), 5);
                }
            }
        }
        if (idSting.contains("chests/")) {
            if (entity instanceof Player player) {
                if (idSting.contains("treasure")) {

                    if (!player.getTags().contains("treasureRageEye")){
                        generatedLoot.add(new ItemStack(Items.rage_eye.get()));
                        player.addTag("treasureRageEye");
                    }


                    boolean ab = !Handler.hascurio(player, Items.cell.get())
                            && !Handler.hascurio(player, Items.giant.get())
                            && Handler.hascurio(player, Items.necora.get());

                    addLootHasB(generatedLoot, random, ab, List.of(
                            Items.cell.get()
                    ), 100);


                    boolean cellBat = !Handler.hascurio(player,Items.bat_cell.get())
                            && Handler.hascurio(player, Items.bloodvirus.get());

                    addLootHasB(generatedLoot, random, cellBat, List.of(
                            Items.bat_cell.get()
                    ), 100);

                    if (Handler.hascurio(player, Items.necora.get())) {

                        boolean cellGiant = Handler.hascurio(player, Items.giant.get());

                        addLootHasB(generatedLoot, random, cellGiant, List.of(
                                Items.bone_cell.get(),
                                Items.parasitic_cell.get(),
                                Items.mother_cell.get(),
                                Items.disgusting_cells.get()
                        ), Config.SERVER.necora.get());

                        boolean cellGiantNig = Handler.hascurio(player, Items.giant_nightmare.get());

                        addLootHasB(generatedLoot, random, cellGiantNig, List.of(
                                Items.giant_boom_cell.get(),
                                Items.anaerobic_cell.get(),
                                Items.subspace_cell.get()
                        ), Config.SERVER.necora.get());

                        boolean cell = Handler.hascurio(player, Items.cell.get())
                                && !Handler.hascurio(player,Items.giant.get());

                        addLootHasB(generatedLoot, random, cell, List.of(
                                Items.adrenaline.get(),
                                Items.cell_mummy.get(),
                                Items.cell_boom.get(),
                                Items.cell_calcification.get(),
                                Items.cell_blood.get()
                        ), Config.SERVER.necora.get());
                    }
                }
            }
        }

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.bloodvirus.get())){
                        if (!Handler.hascurio(player,Items.bat_cell.get())) {
                            generatedLoot.add(new ItemStack(Items.bat_cell.get()));
                        }
                    }
                    if (Handler.hascurio(player, Items.necora.get())){
                        if (!Handler.hascurio(player,Items.giant.get())) {
                            if (Mth.nextInt(RandomSource.create(),1,10) == 1) {
                                generatedLoot.add(new ItemStack(Items.giant.get()));
                            }
                        }
                    }
                }
            }
        }



        for (ItemStack itemStack : generatedLoot){
            ServerLevel serverLevel= context.getLevel();

            if (entity instanceof Player player) {
                int lv = Mth.nextInt(RandomSource.create(),1,100);
                if (Handler.hascurio(player,Items.body_stone.get())
                        && !itemStack.isEmpty()
                        && itemStack.getItem() instanceof ICurioItem)
                {
                    int Terror_size = Mth.nextInt(RandomSource.create(), 1, 6);
                    int malice_size = Mth.nextInt(RandomSource.create(), 1, 6);
                    int threat_size = Mth.nextInt(RandomSource.create(), 1, 6);

                    if (Handler.hascurio(player,Items.probability.get())){
                        Terror_size++;
                        malice_size++;
                        threat_size++;
                    }

                    if (lv<=50){
                        Holder.Reference<Enchantment> Terror = Enchants.getEnchantHolder(player,Enchants.Terror);
                        itemStack.enchant(Terror, Terror_size);
                        player.level().playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.AMBIENT,1,1);
                    }
                    if (lv<=40){
                        Holder.Reference<Enchantment> malice = Enchants.getEnchantHolder(player,Enchants.malice);
                        itemStack.enchant(malice, malice_size);
                        player.level().playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.AMBIENT,1,1);
                    }
                    if (lv<=30){
                        Holder.Reference<Enchantment> threat = Enchants.getEnchantHolder(player,Enchants.threat);
                        itemStack.enchant(threat, threat_size);
                        player.level().playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.AMBIENT,1,1);
                    }
                }
            }


            if (itemStack.getItem() instanceof Iplague){
                if (itemStack.get(DataReg.tag) == null) {
                    itemStack.set(DataReg.tag, new CompoundTag());
                }
                if (serverLevel.getDifficulty()==(Difficulty.PEACEFUL)) {
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.PEACEFUL.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.EASY)) {
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.EASY.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.NORMAL)) {
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.NORMAL.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.HARD)) {
                    int lv = Mth.nextInt(RandomSource.create(),1,2);

                    if (lv == 1) {
                        if (itemStack.get(DataReg.tag) != null) {
                            itemStack.get(DataReg.tag).putBoolean(Difficulty.HARD.getKey(), true);
                        }
                    } else if (lv == 2){
                        if (itemStack.get(DataReg.tag) != null) {
                            itemStack.get(DataReg.tag).putBoolean(NewEvent.lootTable, true);
                        }
                    }
                }
            }
        }
        for (ItemStack itemStack : generatedLoot) {
            BookEvt.addLvl(itemStack,Mth.nextInt(RandomSource.create(),1,3000),Mth.nextInt(RandomSource.create(),0,3000));
        }
        return generatedLoot;
    }

    public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            float h = stack.get(DataReg.tag).getFloat(NewEvent.doctor);//20
                            if (h!=0) {
                                event.setAmount(event.getAmount() + h);
                            }
                        }
                    }
                }
            });
        }
    }
    public static void heal(PlayerEvent.BreakSpeed event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            float h = stack.get(DataReg.tag).getFloat(NewEvent.chromosome);
                            if (h!=0) {
                                event.setNewSpeed(event.getNewSpeed()  + h);
                            }
                        }
                    }
                }
            });
        }
    }
    public static void cit(CriticalHitEvent event){
        if (event.getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            float h = stack.get(DataReg.tag).getFloat(NewEvent.cell_cell);//20
                            if (h!=0) {
                                event.setDamageMultiplier(event.getDamageMultiplier()+ h);
                            }
                        }
                    }
                }
            });
        }
    }

    public static void attack(LivingIncomingDamageEvent event){
        if (event.getSource().getEntity() instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.get(DataReg.tag)!=null){
                            float h = stack.get(DataReg.tag).getFloat(NewEvent.die);//5
                            if (h!=0) {
                                event.setAmount(event.getAmount() + h);
                            }
                        }
                    }
                }
            });
        }
    }

}
