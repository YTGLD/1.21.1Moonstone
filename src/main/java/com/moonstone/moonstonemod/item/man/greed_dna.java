package com.moonstone.moonstonemod.item.man;

import com.moonstone.moonstonemod.contents.ManBundleContents;
import com.moonstone.moonstonemod.init.items.Drugs;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class greed_dna extends neuron_dna {

    /*
        贪欲基因体：（栏位：人类基因槽）
        每开取一个新的战利品箱
        都会增加战利品暴击的概率（3%）
        战利品暴击概率不超过30%
        每次触发战利品暴击，都会使箱子里的部分战利品数量翻倍
        触发战利品暴击后，概率清零
     */
    @Override
    public @Nullable List<Item> getDrug() {
        return List.of(
                Drugs.iris.get(),//虹膜异变：单个战利品箱给予概率增加4%,最大战利品暴击率体提高33%,减少3级时运和抢夺等级
                Drugs.brain_off.get(),//脑组织萎缩：单个战利品箱给予概率减少1%，增加4级时运和抢夺等级
                Drugs.brain_enhance.get(),//脑功能增强：战利品暴击率始终为10%
                Drugs.system_paralysis.get(),//神经系统瘫痪：抢夺等级减少1级,每级抢夺提供2%的掠夺暴击率
                Drugs.memory.get(),//记忆重构：杀死生物提供1%的掠夺暴击率，触发发掠夺暴击后概率清零
                Drugs.tissue_atrophy.get()//组织萎缩：增加3级时运和抢夺，战利品暴击率始终为0%
        );
    }
    public static final String greedLVL = "GreedLVL";
    public static void iris_brain_off_brain_enhance(
            ObjectArrayList<ItemStack> generatedLoot,
            Entity entity){
        if (entity instanceof Player player){
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stackHandler.getStackInSlot(i);
                        if (stack.is(Items.greed_dna.get())) {
                            if (stack.get(DataReg.tag) != null) {
                                int l = 3;
                                int max = 30;
                                if (stack.get(DataReg.tag).getBoolean("iris")) {
                                    max += 33;
                                    l += 4;
                                }
                                if (stack.get(DataReg.tag).getBoolean("brain_off")) {
                                    l -= 1;
                                }
                                if (stack.get(DataReg.tag).getBoolean("brain_enhance")) {
                                    max = 10;
                                }
                                if (stack.get(DataReg.tag).getInt(greedLVL) < max) {
                                    stack.get(DataReg.tag).putInt(greedLVL, stack.get(DataReg.tag).getInt(greedLVL) + l);
                                }
                                List<ItemStack> newLoot = new ArrayList<>();
                                for (ItemStack addLoot : generatedLoot) {
                                    ItemStack copiedLoot = addLoot.copy();
                                    newLoot.add(copiedLoot);
                                }
                                if (new Random().nextInt(100) < stack.get(DataReg.tag).getInt(greedLVL)) {
                                    generatedLoot.addAll(newLoot);
                                    player.displayClientMessage(Component.translatable("moonstone.greed_dna.event").withStyle(ChatFormatting.GOLD), false);
                                    stack.get(DataReg.tag).putInt(greedLVL, 0);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (stack.get(DataReg.tag)!=null) {
            ManBundleContents manBundleContents = stack.get(DataReg.man);
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.iris)) {
                        stack.get(DataReg.tag).putBoolean("iris", true);
                    }else {
                        stack.get(DataReg.tag).putBoolean("iris", false);
                    }
                }));
            }
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.brain_off)) {
                        stack.get(DataReg.tag).putBoolean("brain_off", true);
                    }else {
                        stack.get(DataReg.tag).putBoolean("brain_off", false);
                    }
                }));
            }
            if (manBundleContents != null) {
                manBundleContents.items().forEach((itemStack -> {
                    if (itemStack.is(Drugs.brain_enhance)) {
                        stack.get(DataReg.tag).putBoolean("brain_enhance", true);
                    }else {
                        stack.get(DataReg.tag).putBoolean("brain_enhance", false);
                    }
                }));
            }
        }
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, @Nullable LootContext lootContext, ItemStack stack) {
        int f = 0;
        if (stack.get(DataReg.tag)!=null) {
            if (stack.get(DataReg.tag).getBoolean("brain_off")) {
                f += 4;
            }
            if (stack.get(DataReg.tag).getBoolean("iris")) {
                f -= 3;
            }
        }
        return f;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        int f = 0;
        if (stack.get(DataReg.tag)!=null) {
            if (stack.get(DataReg.tag).getBoolean("brain_off")) {
                f += 4;
            }
            if (stack.get(DataReg.tag).getBoolean("iris")) {
                f -= 3;
            }
        }
        return f;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("item.greed_dna.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.greed_dna.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.greed_dna.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.greed_dna.tool.string.3").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.greed_dna.tool.string.4").withStyle(ChatFormatting.GOLD));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
