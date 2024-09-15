package com.moonstone.moonstonemod.item.maxitem;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class the_heart extends BundleItem implements ICurioItem {
    public the_heart() {
        super(new Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS,BundleContents.EMPTY).rarity(Rarity.UNCOMMON));
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    @Override
    public boolean overrideOtherStackedOnMe(
            ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess
    ) {
        if (pStack.getCount() != 1) return false;
        if (pAction == ClickAction.SECONDARY && pSlot.allowModification(pPlayer)) {
            BundleContents bundlecontents = pStack.get(DataComponents.BUNDLE_CONTENTS);
            if (bundlecontents == null) {
                return false;
            } else {
                BundleContents.Mutable bundlecontents$mutable = new BundleContents.Mutable(bundlecontents);
                if (pOther.isEmpty()) {
                    ItemStack itemstack = bundlecontents$mutable.removeOne();
                    if (itemstack != null) {
                        this.playRemoveOneSound(pPlayer);
                        pAccess.set(itemstack);
                    }
                } else {
                    int i = bundlecontents$mutable.tryInsert(pOther);
                    if (i > 0) {
                        this.playInsertSound(pPlayer);
                    }
                }

                pStack.set(DataComponents.BUNDLE_CONTENTS, bundlecontents$mutable.toImmutable());
                return true;
            }
        } else {
            return false;
        }
    }
    @Override
    public int getBarWidth(ItemStack pStack) {
        BundleContents bundlecontents = pStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return 4;
    }
    public InteractionResultHolder<ItemStack> use(Level p_150760_, Player p_150761_, InteractionHand p_150762_) {
        ItemStack itemstack = p_150761_.getItemInHand(p_150762_);
        return InteractionResultHolder.sidedSuccess(itemstack, p_150760_.isClientSide());

    }
    @Override
    public int getBarColor(ItemStack p_150901_) {
        float stackMaxDamage = this.getBarWidth(p_150901_);
        float f = Math.max(0.0F, (stackMaxDamage - (float)64 / stackMaxDamage));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }




    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        pTooltipComponents.add(Component.translatable("item.the_heart.tool.string").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.the_heart.tool.string.1").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.the_heart.tool.string.2").withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable(""));
        pTooltipComponents.add(Component.translatable("item.the_heart.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable(""));
    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.IRON_GOLEM_REPAIR, 0.8F, 0.8F + p_186343_.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity p_186352_) {
        p_186352_.playSound(SoundEvents.IRON_GOLEM_REPAIR, 0.8F, 0.8F + p_186352_.level().getRandom().nextFloat() * 0.4F);
    }

}
