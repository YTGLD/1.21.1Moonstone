package com.moonstone.moonstonemod.item.plague;

import com.moonstone.moonstonemod.contents.BundleContentsDNA;
import com.moonstone.moonstonemod.init.items.DNAItems;
import com.moonstone.moonstonemod.init.items.Items;
import com.moonstone.moonstonemod.init.moonstoneitem.DataReg;
import com.moonstone.moonstonemod.init.moonstoneitem.i.Iplague;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class crafting_box extends Item implements Iplague {
    public crafting_box() {
        super(new Item.Properties().stacksTo(1).component(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY).rarity(Rarity.UNCOMMON));
    }
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (stack.getCount() != 1) {
            return false;
        } else if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            BundleContentsDNA bundlecontents = stack.get(DataReg.SUPER_BAG);
            if (bundlecontents == null) {
                return false;
            } else {
                BundleContentsDNA.Mutable bundlecontents$mutable = new BundleContentsDNA.Mutable(bundlecontents);
                if (other.isEmpty()) {
                    ItemStack itemstack = bundlecontents$mutable.removeOne();
                    if (itemstack != null) {
                        this.playRemoveOneSound(player);
                        access.set(itemstack);
                    }
                } else {
                    int i = bundlecontents$mutable.tryInsert(other);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataReg.SUPER_BAG, bundlecontents$mutable.toImmutable());
                return true;
            }
        } else {
            return false;
        }
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (player.isShiftKeyDown()) {
            if (dropContents(itemstack, player)) {
                this.playDropContentsSound(player);
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }else {
            player.startUsingItem(usedHand);
        }
        return super.use(level, player, usedHand);
    }
    private boolean checkCondition(BundleContentsDNA bundlecontents, DeferredHolder<Item, ?> itemType, int count) {
        for (ItemStack item : bundlecontents.items()) {
            if (item.is(itemType) && item.getCount() == count) {
                return true;
            }
        }
        return false;
    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }


    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            BundleContentsDNA bundlecontents = stack.get(DataReg.SUPER_BAG);
            if (bundlecontents!=null) {
                if (checkCondition(bundlecontents, DNAItems.atp_height, 48) &&
                        checkCondition(bundlecontents, DNAItems.cell_darwin, 32) &&
                        checkCondition(bundlecontents, DNAItems.cell_off_on, 32) &&
                        checkCondition(bundlecontents, DNAItems.cell_disorder, 20) &&
                        checkCondition(bundlecontents, DNAItems.cell_synthesis, 12) &&
                        checkCondition(bundlecontents, DNAItems.cell_dna_suppression, 24) &&
                        checkCondition(bundlecontents, DNAItems.cell_necrosis, 8) &&
                        checkCondition(bundlecontents, Items.ectoplasmprism, 1)) {
                    player.addItem(new ItemStack(Items.necora));
                    stack.set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
                }
            }
            if (bundlecontents!=null) {
                if (checkCondition(bundlecontents, DNAItems.atp_height, 20) &&
                        checkCondition(bundlecontents, DNAItems.cell_god, 16) &&
                        checkCondition(bundlecontents, DNAItems.speed_metabolism, 40) &&
                        checkCondition(bundlecontents, DNAItems.cell_disorder, 64) &&
                        checkCondition(bundlecontents, DNAItems.cell_necrosis, 24) &&

                        checkCondition(bundlecontents, Items.ectoplasmprism, 1) &&
                        checkCondition(bundlecontents, Items.ectoplasmball, 8)) {
                    player.addItem(new ItemStack(Items.cell));
                    stack.set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
                }
            }


            if (bundlecontents!=null) {
                if (checkCondition(bundlecontents, DNAItems.atp_height, 30) &&
                        checkCondition(bundlecontents, DNAItems.cell_god, 24) &&
                        checkCondition(bundlecontents, DNAItems.speed_metabolism, 60) &&
                        checkCondition(bundlecontents, DNAItems.cell_disorder, 64) &&
                        checkCondition(bundlecontents, DNAItems.cell_necrosis, 12) &&
                        checkCondition(bundlecontents, Items.ectoplasmprism, 1) &&
                        checkCondition(bundlecontents, Items.cell, 1)) {
                    player.addItem(new ItemStack(Items.giant));
                    stack.set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
                }
            }


            if (bundlecontents!=null) {
                if (checkCondition(bundlecontents, DNAItems.atp_height, 64) &&
                        checkCondition(bundlecontents, DNAItems.speed_metabolism, 16) &&
                        checkCondition(bundlecontents, DNAItems.cell_dna_suppression, 64) &&
                        checkCondition(bundlecontents, DNAItems.cell_synthesis, 64) &&
                        checkCondition(bundlecontents, DNAItems.cell_darwin, 64) &&
                        checkCondition(bundlecontents, DNAItems.cell_off_on, 64) &&

                        checkCondition(bundlecontents, Items.ectoplasmprism, 1) &&
                        checkCondition(bundlecontents, Items.giant, 1)) {
                    player.addItem(new ItemStack(Items.giant_nightmare));
                    stack.set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
                }
            }
        }
        return stack;
    }

    public boolean isBarVisible(ItemStack stack) {
        BundleContentsDNA bundlecontents = stack.getOrDefault(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
        return bundlecontents.weight().compareTo(Fraction.ZERO) > 0;
    }


    private static boolean dropContents(ItemStack stack, Player player) {
        BundleContentsDNA bundlecontents = stack.get(DataReg.SUPER_BAG);
        if (bundlecontents != null && !bundlecontents.isEmpty()) {
            stack.set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
            if (player instanceof ServerPlayer) {
                bundlecontents.itemsCopy().forEach((p_330078_) -> {
                    player.drop(p_330078_, true);
                });
            }

            return true;
        } else {
            return false;
        }
    }
    public void onDestroyed(ItemEntity itemEntity) {
        BundleContentsDNA bundlecontents = itemEntity.getItem().get(DataReg.SUPER_BAG);
        if (bundlecontents != null) {
            itemEntity.getItem().set(DataReg.SUPER_BAG, BundleContentsDNA.EMPTY);
            ItemUtils.onContainerDestroyed(itemEntity, bundlecontents.itemsCopy());
        }

    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (Screen.hasShiftDown()) {
            BundleContentsDNA bundlecontents = pStack.get(DataReg.SUPER_BAG);
            if (bundlecontents != null) {
                bundlecontents.items().forEach((itemStack -> {
                    pTooltipComponents.add(Component.translatable(itemStack.getDescriptionId()).append("："+ itemStack.getCount()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFEE6363))));
                }));
            }
        }else {
            pTooltipComponents.add(Component.translatable("key.keyboard.left.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFB22222))));
            pTooltipComponents.add(Component.literal(""));

        }
    }
}