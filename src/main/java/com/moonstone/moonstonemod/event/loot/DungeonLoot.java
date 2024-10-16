package com.moonstone.moonstonemod.event.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.event.NewEvent;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.init.LootReg;
import com.moonstone.moonstonemod.init.moonstoneitem.extend.TheNecoraIC;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;
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

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


        ResourceLocation s = context.getQueriedLootTableId();
        String idSting = String.valueOf(s);
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        int W = Mth.nextInt(RandomSource.create(), 1, 20);

        int N = Mth.nextInt(RandomSource.create(), 1, 60);
        int a = Mth.nextInt(RandomSource.create(), 1, 35);
        int ne = Mth.nextInt(RandomSource.create(), 1, 40);
        int T = Mth.nextInt(RandomSource.create(), 1, 10);

        int cell = Mth.nextInt(RandomSource.create(), 1, 50);
        int giant = Mth.nextInt(RandomSource.create(), 1, 10);
        int bat = Mth.nextInt(RandomSource.create(), 1, 18);
        int giant_p = Mth.nextInt(RandomSource.create(), 1, 10);

        if (idSting.contains("chests/")) {
            if (idSting.contains("treasure")){
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player,Items.bloodvirus.get())) {
                        if (!Handler.hascurio(player, Items.bat_cell.get())) {
                            generatedLoot.add(new ItemStack(Items.bat_cell.get()));
                        }
                        if (Handler.hascurio(player, Items.bat_cell.get())) {
                            if (bat == 1) {
                                generatedLoot.add(new ItemStack(Items.cell_blood_attack.get()));

                            }
                            if (bat == 2) {
                                generatedLoot.add(new ItemStack(Items.cell_desecrate.get()));

                            }
                            if (bat == 3) {
                                generatedLoot.add(new ItemStack(Items.cell_doctor.get()));

                            }
                            if (bat == 4) {
                                generatedLoot.add(new ItemStack(Items.cell_fear.get()));

                            }
                            if (bat == 5) {
                                generatedLoot.add(new ItemStack(Items.cell_harvest.get()));

                            }
                            if (bat == 6) {

                                generatedLoot.add(new ItemStack(Items.cell_immortal.get()));
                            }
                            if (bat == 7) {

                                generatedLoot.add(new ItemStack(Items.cell_not_do.get()));
                            }
                            if (bat == 8) {

                                generatedLoot.add(new ItemStack(Items.cell_rage.get()));
                            }
                            if (bat == 9) {

                                generatedLoot.add(new ItemStack(Items.cell_scientist.get()));
                            }
                        }
                    }
                    if (Handler.hascurio(player, Items.necora.get())){
                        if (Handler.hascurio(player, Items.giant_nightmare.get())) {
                            if (giant == 2) {
                                generatedLoot.add(new ItemStack(Items.giant_boom_cell.get()));
                            }

                            if (giant == 4) {
                                generatedLoot.add(new ItemStack(Items.anaerobic_cell.get()));
                            }
                            if (giant == 5) {
                                generatedLoot.add(new ItemStack(Items.subspace_cell.get()));
                            }
                        }
                        if (!Handler.hascurio(player,Items.giant.get())){
                            if (giant == 1) {
                                generatedLoot.add(new ItemStack(Items.giant.get()));
                            }
                            if (giant_p  == 2) {
                                generatedLoot.add(new ItemStack(Items.bone_cell.get()));
                            }
                            if (giant_p == 3) {
                                generatedLoot.add(new ItemStack(Items.parasitic_cell.get()));
                            }
                            if (giant_p == 4) {
                                generatedLoot.add(new ItemStack(Items.mother_cell.get()));
                            }
                            if (giant_p == 5) {
                                generatedLoot.add(new ItemStack(Items.disgusting_cells.get()));
                            }
                        }

                        if (!Handler.hascurio(player, Items.cell.get()) && !Handler.hascurio(player,Items.giant.get())){
                            generatedLoot.add(new ItemStack(Items.cell.get()));
                        }
                        if (Handler.hascurio(player, Items.cell.get()) && !Handler.hascurio(player,Items.giant.get())) {
                            if (cell == 2) {
                                generatedLoot.add(new ItemStack(Items.adrenaline.get()));
                            }
                            if (cell == 3) {
                                generatedLoot.add(new ItemStack(Items.cell_mummy.get()));
                            }
                            if (cell == 4) {
                                generatedLoot.add(new ItemStack(Items.cell_boom.get()));
                            }
                            if (cell == 5) {
                                generatedLoot.add(new ItemStack(Items.cell_calcification.get()));
                            }
                            if (cell == 6) {
                                generatedLoot.add(new ItemStack(Items.cell_blood.get()));
                            }
                        }

                        if (cell == 7) {
                            generatedLoot.add(new ItemStack(Items.motor.get()));
                        }
                        if (cell == 8) {
                            generatedLoot.add(new ItemStack(Items.air.get()));
                        }
                        if (cell == 9) {
                            generatedLoot.add(new ItemStack(Items.watergen.get()));
                        }
                    }

                    if (Handler.hascurio(player, Items.doomswoud.get()) && Handler.hascurio(player, Items.doomeye.get())) {
                        if (T == 1) {
                            generatedLoot.add(new ItemStack(Items.wind_and_rain.get()));
                        }
                    }


                }


            }
            if (idSting.contains("dungeon") || idSting.contains("mineshaft")) {
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.necora.get())) {
                        if (ne == 1) {
                            generatedLoot.add(new ItemStack(Items.ambush.get()));
                        }
                        if (ne == 2) {
                            generatedLoot.add(new ItemStack(Items.atpoverdose.get()));
                        }
                        if (ne == 3) {
                            generatedLoot.add(new ItemStack(Items.autolytic.get()));
                        }
                        if (ne == 4) {
                            generatedLoot.add(new ItemStack(Items.fermentation.get()));
                        }
                        if (ne == 5) {
                            generatedLoot.add(new ItemStack(Items.putrefactive.get()));
                        }
                        if (ne == 6) {
                            generatedLoot.add(new ItemStack(Items.regenerative.get()));
                        }
                    }

                    if (Handler.hascurio(player, Items.nightmareeye.get())) {
                        if (N == 1) {
                            generatedLoot.add(new ItemStack(Items.nightmaremoai.get()));
                        }
                        if (N == 2) {
                            generatedLoot.add(new ItemStack(Items.nightmarewater.get()));
                        }
                        if (N == 3) {
                            generatedLoot.add(new ItemStack(Items.nightmarestone.get()));
                        }
                        if (N == 4) {
                            generatedLoot.add(new ItemStack(Items.nightmareanchor.get()));
                        }
                        if (N == 5) {
                            generatedLoot.add(new ItemStack(Items.nightmaretreasure.get()));
                        }
                        if (N == 6) {
                            generatedLoot.add(new ItemStack(Items.nightmarecharm.get()));
                        }
                        if (N == 7) {
                            generatedLoot.add(new ItemStack(Items.nightmarerotten.get()));
                        }
                        if (N == 8) {
                            generatedLoot.add(new ItemStack(Items.nightmare_orb.get()));
                        }
                        if (N == 9) {
                            generatedLoot.add(new ItemStack(Items.nightmare_heart.get()));
                        }
                    }
                }

            }

            if (idSting.contains("city")) {
                if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player) {
                    if (Handler.hascurio(player,Items.dna.get())){
                        if (a == 7) {
                            generatedLoot.add(new ItemStack(Items.germ.get()));
                        }
                    }

                    if (Handler.hascurio(player, Items.bloodvirus.get())) {
                        if (a == 1) {
                            generatedLoot.add(new ItemStack(Items.bloodgene.get()));
                        }
                        if (a == 2) {
                            generatedLoot.add(new ItemStack(Items.ragegene.get()));
                        }
                        if (a == 3) {
                            generatedLoot.add(new ItemStack(Items.flygene.get()));
                        }
                        if (a == 4) {
                            generatedLoot.add(new ItemStack(Items.heathgene.get()));
                        }
                        if (a == 5) {
                            generatedLoot.add(new ItemStack(Items.sleepgene.get()));
                        }
                        if (a == 6) {
                            generatedLoot.add(new ItemStack(Items.batgene.get()));
                        }
                    }
                }
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player, Items.doomswoud.get()) && Handler.hascurio(player, Items.doomeye.get())) {
                        if (W == 1) {
                            generatedLoot.add(new ItemStack(Items.wind_and_rain.get()));
                        }
                    }
                }


            }
        }

        for (ItemStack itemStack : generatedLoot){
            ServerLevel serverLevel= context.getLevel();
            if (itemStack.getItem() instanceof ICurioItem) {
                if (entity instanceof Player player) {
                    if (Handler.hascurio(player,Items.body_stone.get())) {
                        if (itemStack.get(DataReg.tag) == null) {
                            itemStack.set(DataReg.tag, new CompoundTag());
                        }
                        float meet = Mth.nextFloat(RandomSource.create(), -0.5f, 0.5f);
                        //health

                        float die = Mth.nextFloat(RandomSource.create(), -0.2f, 0.2f);
                        //伤害

                        float doctor = Mth.nextFloat(RandomSource.create(), -0.01f, 0.01f);
                        //治疗

                        float cell_cell = Mth.nextFloat(RandomSource.create(), -0.5f, 0.5f);
                        //暴击

                        float chromosome = Mth.nextFloat(RandomSource.create(), -1, 1);
                        //挖掘

                        float bone = Mth.nextFloat(RandomSource.create(), -0.005f, 0.005f);
                        //速度

                        float die_body = Mth.nextFloat(RandomSource.create(), -1, 1);
                        //护甲


                        int t = Mth.nextInt(RandomSource.create(),1,7);
                        if (t==1) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.meet, meet);
                            }
                        }else if (t==2) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.die, die);
                            }
                        }else if (t==3) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.doctor, doctor);
                            }
                        } else  if (t==4) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.cell_cell, cell_cell);
                            }
                        } else if (t==5) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.chromosome, chromosome);
                            }
                        }else  if (t==6) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.bone, bone);
                            }
                        }else if (t==7) {
                            if (itemStack.get(DataReg.tag) != null) {
                                itemStack.get(DataReg.tag).putFloat(NewEvent.die_body, die_body);
                            }
                        }
                    }
                }
            }



            if (itemStack.getItem() instanceof Iplague){
                if (serverLevel.getDifficulty()==(Difficulty.PEACEFUL)) {
                    itemStack.set(DataReg.tag, new CompoundTag());
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.PEACEFUL.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.EASY)) {
                    itemStack.set(DataReg.tag, new CompoundTag());
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.EASY.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.NORMAL)) {
                    itemStack.set(DataReg.tag, new CompoundTag());
                    if (itemStack.get(DataReg.tag) != null) {
                        itemStack.get(DataReg.tag).putBoolean(Difficulty.NORMAL.getKey(), true);
                    }
                }
                if (serverLevel.getDifficulty()==(Difficulty.HARD)) {
                    itemStack.set(DataReg.tag, new CompoundTag());
                    if (Mth.nextInt(RandomSource.create(),1,2) ==1) {
                        if (itemStack.get(DataReg.tag) != null) {
                            itemStack.get(DataReg.tag).putBoolean(Difficulty.HARD.getKey(), true);
                        }
                    }else {
                        if (itemStack.get(DataReg.tag) != null) {
                            itemStack.get(DataReg.tag).putBoolean(NewEvent.lootTable, true);
                        }
                    }
                }

            }
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
