package com.moonstone.moonstonemod.item.blood.magic;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.entity.blood;
import com.moonstone.moonstonemod.entity.owner_blood;
import com.moonstone.moonstonemod.init.DataReg;
import com.moonstone.moonstonemod.init.EntityTs;
import com.moonstone.moonstonemod.init.Items;
import com.moonstone.moonstonemod.moonstoneitem.Blood;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class blood_candle extends Item implements ICurioItem, Blood {

    public blood_candle() {
        super(new Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).rarity(Rarity.UNCOMMON));
    }
    //
//    @Override
//    public boolean overrideOtherStackedOnMe(
//            ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess
//    ) {
//        if (pStack.getCount() != 1) return false;
//        if (pAction == ClickAction.SECONDARY && pSlot.allowModification(pPlayer)) {
//            BundleContents bundlecontents = pStack.get(DataComponents.BUNDLE_CONTENTS);
//            if (bundlecontents == null) {
//                return false;
//            } else {
//                BundleContents.Mutable bundlecontents$mutable = new BundleContents.Mutable(bundlecontents);
//                if (pOther.isEmpty()) {
//                    ItemStack itemstack = bundlecontents$mutable.removeOne();
//                    if (itemstack != null) {
//                        this.playRemoveOneSound(pPlayer);
//                        pAccess.set(itemstack);
//                    }
//                } else {
//                    int i = bundlecontents$mutable.tryInsert(pOther);
//                    if (i > 0) {
//                        this.playInsertSound(pPlayer);
//                    }
//                }
//
//                pStack.set(DataComponents.BUNDLE_CONTENTS, bundlecontents$mutable.toImmutable());
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//    private void playRemoveOneSound(Entity p_186343_) {
//        p_186343_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 0.8F, 0.8F + p_186343_.level().getRandom().nextFloat() * 0.4F);
//    }
//
//    private void playInsertSound(Entity p_186352_) {
//        p_186352_.playSound(SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), 0.8F, 0.8F + p_186352_.level().getRandom().nextFloat() * 0.4F);
//    }
//
    /*
    public static void SwordEventLivingEntityUseItemEvent(LivingEntityUseItemEvent.Stop event) {
        if (event.getEntity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(Items.blood_candle.get())) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    Map<String, ICurioStacksHandler> curios = handler.getCurios();
                    for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                        ICurioStacksHandler stacksHandler = entry.getValue();
                        IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            ItemStack stack = stackHandler.getStackInSlot(i);


                            if (stack.is(Items.blood_candle.get())&&Handler.hascurio(player,Items.blood_candle.get())) {
                                BundleContents bundlecontents = stack.get(DataComponents.BUNDLE_CONTENTS);
                                if (bundlecontents != null && !bundlecontents.isEmpty()) {
                                    bundlecontents.items().forEach((itemStack) -> {
                                        if (!itemStack.isEmpty()) {
                                            if (itemStack.getCount() >= 9) {
                                                if (Handler.hascurio(player, Items.blood_candle.get())) {
                                                    if (getPlayerLookTarget(player.level(), player) instanceof LivingEntity living) {
                                                        Vec3 targetPos = living.position().add(0, 0.5, 0);
                                                        Vec3 currentPos = player.position();
                                                        Vec3 direction = targetPos.subtract(currentPos).normalize();
                                                        player.setDeltaMovement(direction.x * 5f, direction.y * 5f, direction.z * 5f);

                                                        player.getCooldowns().addCooldown(Items.blood_candle.get(), 6);

                                                        if (player.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                                                            living.hurt(living.damageSources().dryOut(), (float) (player.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 4));

                                                            if (player instanceof ServerPlayer) {
                                                                dropContents(stack);
                                                            }
                                                        }
                                                        blood blood = new blood(EntityTs.blood.get(), player.level());
                                                        blood.setPos(player.position());
                                                        blood.setOwner(player);
                                                        blood.addTag("Blood");
                                                        WARDEN_SONIC_BOOM(player);
                                                        player.level().addFreshEntity(blood);
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }
        }
    }


     */
    /*
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

    private static void dropContents(ItemStack stack, Player player) {
        BundleContents bundlecontents = stack.get(DataComponents.BUNDLE_CONTENTS);
        if (bundlecontents != null && !bundlecontents.isEmpty()) {
            bundlecontents.items().forEach((itemStack) -> {
                if (player instanceof ServerPlayer) {
                    if (!itemStack.isEmpty()) {
                        if (itemStack.getCount() >= 9) {
                            itemStack.shrink(8);
                        }
                    }
                }
            });
        }
    }
    */

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        stack.set(DataReg.tag,new CompoundTag());
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (stack.get(DataReg.tag) != null) {
                if (!stack.get(DataReg.tag).getBoolean("HasBlood")) {
                    owner_blood owner_blood = new owner_blood(EntityTs.owner_blood.get(),player.level());
                    owner_blood.setOwnerUUID(player.getUUID());
                    owner_blood.setPos(player.position());
                    player.level().addFreshEntity(owner_blood);
                    stack.get(DataReg.tag).putBoolean("HasBlood",true);
                }
            } else stack.set(DataReg.tag, new CompoundTag());
        }    }



    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("item.blood_candle.tool.string").withStyle(ChatFormatting.RED));

        } else {
            pTooltipComponents.add(Component.literal("Shift").withStyle(ChatFormatting.DARK_RED));
        }
    }
}

